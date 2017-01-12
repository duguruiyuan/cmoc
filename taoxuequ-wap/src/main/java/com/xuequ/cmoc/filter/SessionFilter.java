package com.xuequ.cmoc.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.model.WechatUserInfo;
import com.xuequ.cmoc.utils.RequestUtil;
import com.xuequ.cmoc.utils.TextUtil;

public class SessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		if(StringUtils.isBlank(Constants.BASEPATH)) Constants.BASEPATH = RequestUtil.getBasePath(request);
		// 设置request和response的字符集，防止乱码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 请求的uri
        String uri = request.getRequestURI();
        FilterConfig config = getFilterConfig();
        
        //止后缀无需过滤，配置的资源文件
        String suffix = config.getInitParameter("suffix");
		String[] suffixList = suffix.split(";");
		if (isContains(uri, suffixList)) {
			filterChain.doFilter(request, response);
			return;
		}
        //止后缀无需过滤，为api,通知等链接
        String access = config.getInitParameter("noAccess");
		String[] accessList = access.split(";");
		if (!isContains(uri, accessList)) {
			filterChain.doFilter(request, response);
			return;
		}
		String openid = RequestUtil.getCookieValue(request, "openid"); //"oXDCCs3AjlFVcKWtJybY54gqP9hI"
		if(StringUtils.isNotBlank(openid)) {
			WechatUserInfo userInfo = (WechatUserInfo) request.getSession().getAttribute(Constants.WECHAT_USERINFO);
			if(userInfo == null) {
				userInfo = WechatUtils.getUserInfo(WechatUtils.getWechatModel().getAccessToken(), openid);
				if(userInfo.getSubscribe() == 0) {
					response.sendRedirect(getRedirectUrl(uri));
					return;
				}
				request.getSession().setAttribute(Constants.WECHAT_USERINFO, userInfo);
			}
			filterChain.doFilter(request, response);
			return;
		}else {
			response.sendRedirect(getRedirectUrl(uri));
            return;
		}
	}

	private static String getRedirectUrl(String requestUri) {
		WechatConfigure configure = WechatConfigure.getInstance();
		String selfUrl = Constants.BASEPATH + "/wechatauth?self=" + requestUri;
		String url = TextUtil.format(configure.getOauth2Userinfo(), 
				new String[]{configure.getAppid(), URLEncoder.encode(selfUrl)});
		return url;
	}
	public static boolean isContains(String container, String[] regx) {
		boolean result = false;
		for (int i = 0; i < regx.length; i++) {
			regx[i] = regx[i].trim();
			if (container.indexOf(regx[i]) != -1) {
				result = true;
			}
		}
		return result;
	}
	
}
