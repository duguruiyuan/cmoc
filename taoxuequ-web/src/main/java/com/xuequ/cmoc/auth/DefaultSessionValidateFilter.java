package com.xuequ.cmoc.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class DefaultSessionValidateFilter extends GenericFilterBean {
	
	static final String FILTER_APPLIED = "__spring_security_session_mgmt_filter_applied";
	
	/**
     * 创建新版本.
     */
    private boolean createNewSession = true;
	
    private SecurityContextRepository securityContextRepository;

    private SessionAuthenticationStrategy sessionAuthenticationStrategy;
    
    private final AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();

    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
    
    /**
     * 构造器
     *
     * @param securityContextRepository securityContext仓库
     */
    public DefaultSessionValidateFilter(SecurityContextRepository securityContextRepository) {
        this(securityContextRepository, new SessionFixationProtectionStrategy());
    }

    /**
     * 默认构造器.
     */
    public DefaultSessionValidateFilter() {
        super();
    }

    /**
     * 构造器
     *
     * @param securityContextRepository securityContext仓库
     * @param sessionStrategy session策略
     */
    public DefaultSessionValidateFilter(SecurityContextRepository securityContextRepository,
            SessionAuthenticationStrategy sessionStrategy) {
        Assert.notNull(securityContextRepository, "SecurityContextRepository cannot be null");
        Assert.notNull(sessionStrategy, "SessionAuthenticationStrategy cannot be null");
        this.securityContextRepository = securityContextRepository;
        this.sessionAuthenticationStrategy = sessionStrategy;
    }
    
    public SecurityContextRepository getSecurityContextRepository() {
        return securityContextRepository;
    }

    /**
     * 设置SecurityContextRepository
     *
     * @param securityContextRepository SecurityContext仓库
     */
    public void setSecurityContextRepository(SecurityContextRepository securityContextRepository) {
        this.securityContextRepository = securityContextRepository;
    }

    public SessionAuthenticationStrategy getSessionAuthenticationStrategy() {
        return sessionAuthenticationStrategy;
    }
    
    public void setSessionAuthenticationStrategy(SessionAuthenticationStrategy sessionAuthenticationStrategy) {
        Assert.notNull(sessionAuthenticationStrategy, "authenticatedSessionStrategy must not be null");
        this.sessionAuthenticationStrategy = sessionAuthenticationStrategy;
    }
    
    /**
     * 设置认证失败处理器
     * 
     * @param failureHandler . The handler which will be invoked if the <tt>AuthenticatedSessionStrategy</tt> raises a
     *            <tt>SessionAuthenticationException</tt>, indicating that the user is not allowed to be authenticated
     *            for this session (typically because they already have too many sessions open).
     */
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        Assert.notNull(failureHandler, "failureHandler cannot be null");
        this.failureHandler = failureHandler;
    }
    
    
	/**
     * 执行过滤
     *
     * @param req 请求
     * @param res 响应
     * @param chain 过滤器链
     * @throws IOException IO异常
     * @throws ServletException Servlet异常
     */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        if (requiresAuthentication((HttpServletRequest)req, (HttpServletResponse)res)) {
            chain.doFilter(request, response);
            return;
        }
        if (request.getAttribute(FILTER_APPLIED) != null) {
            chain.doFilter(request, response);
            return;
        }
        request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
        if (!securityContextRepository.containsContext(request)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && !authenticationTrustResolver.isAnonymous(authentication)) {
                // The user has been authenticated during the current request, so call the session strategy
                try {
                    sessionAuthenticationStrategy.onAuthentication(authentication, request, response);
                }
                catch (SessionAuthenticationException e) {
                    // The session strategy can reject the authentication
                    logger.debug("SessionAuthenticationStrategy rejected the authentication object", e);
                    SecurityContextHolder.clearContext();
                    failureHandler.onAuthenticationFailure(request, response, e);

                    return;
                }
                // Eagerly save the security context to make it available for any possible re-entrant
                // requests which may occur before the current request completes. SEC-1396.
                securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);
            }
            else {
                // No security context or authentication present. Check for a session timeout
                // if (request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid()) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Requested session ID " + request.getRequestedSessionId() + " is invalid.");
                }
                onInvalidSessionDetected(request, response);
                return;
            }
        }

        chain.doFilter(request, response);
	}
	
	/**
     * 推测重定向url地址.
     * @param request HttpServletRequest请求
     * @param response   HttpServletResponse响应
     * @throws IOException Exception
     */
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String destinationUrl = "/auth/login";
        if (createNewSession) {
            request.getSession();
        }
        // 请求的url
        String url = request.getRequestURL().toString();
        // 请求的参数
        String para = request.getQueryString();
        // URL hash
        Cookie hash = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cooky : cookies) {
                if ("hash".equals(cooky.getName())) {
                    hash = cooky;
                    hash.setMaxAge(-1);
                    response.addCookie(hash);
                    break;
                }
            }
        }

        if (!StringUtils.isEmpty(url)) {
            destinationUrl = destinationUrl.concat("?url=").concat(url);
        }
        if (!StringUtils.isEmpty(url) && !StringUtils.isEmpty(para)) {
            destinationUrl = destinationUrl.concat("?").concat(para);
        }
        if (!StringUtils.isEmpty(url) && hash != null) {
            destinationUrl = destinationUrl + "#" + hash.getValue().replace("+", ",");
        }
        String redirectUrl = calculateRedirectUrl(request.getContextPath(), destinationUrl.replace("#", "%23"));
        redirectUrl = response.encodeRedirectURL(redirectUrl);

        response.sendRedirect(redirectUrl);
    }
	
    /**
     * 推测重定向url地址.
     * @param contextPath 路径
     * @param url   url地址
     * @return String
     */
    private String calculateRedirectUrl(String contextPath, String url) {
        if (!UrlUtils.isAbsoluteUrl(url)) {
            return contextPath + url;
        }

        // Calculate the relative URL from the fully qualified URL, minus the scheme and base context.
        url = url.substring(url.indexOf("://") + 3); // strip off scheme
        url = url.substring(url.indexOf(contextPath) + contextPath.length());

        if (url.length() > 1 && url.charAt(0) == '/') {
            url = url.substring(1);
        }

        return url;
    }
    
	/**
     * 请求认证
     *
     * @param request 请求
     * @param response 响应
     * @return boolean
     */
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        int pathParamIndex = uri.indexOf(';');

        if (pathParamIndex > 0) {
            uri = uri.substring(0, pathParamIndex);
        }

        if ("".equals(request.getContextPath())) {
            return uri.endsWith("/auth/login");
        }

        return uri.endsWith(request.getContextPath() + "/auth/login")
                || uri.contains(request.getContextPath() + "/auth/login?");
    }

}
