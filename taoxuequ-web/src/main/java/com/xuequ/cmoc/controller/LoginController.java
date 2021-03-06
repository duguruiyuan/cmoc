package com.xuequ.cmoc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.auth.AppUser;
import com.xuequ.cmoc.common.Constants;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.service.ISysUserService;

@RequestMapping("auth")
@Controller
public class LoginController extends BaseController{
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ISysUserService sysUserService;
	/**
     * 根据用户角色来决定默认的展现页面.
     * @return String
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request,Model model) {
    	try {
    		AppUser user = (AppUser) request.getSession().getAttribute(Constants.APP_USER);
        	if(user != null) {
        		return "redirect:/auth/main";
        	}
    	}catch (Exception e) {
			logger.error("loginPage exception : {}",e);
		}
    	return "login";
    }
    
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String forwardToDefaultPage(HttpServletRequest request, Model model) {
    	model.addAttribute(Constants.APP_USER, request.getSession().getAttribute(Constants.APP_USER));
        return "index";
    }

    /**
	 * 验证输入码的正确性
	 * author zhouhuiqun470
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="resultServlet/validateCode",method=RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response)         
            throws ServletException, IOException {         
        response.setContentType("text/html;charset=utf-8");         
        String validateC = (String) request.getSession().getAttribute("validateCode");         
        String veryCode = request.getParameter("c");         
        PrintWriter out = response.getWriter();                  
        if(validateC.equalsIgnoreCase(veryCode)){         
            out.println(StatusEnum.SUCCESS.getCode());         
        }else{         
            out.println(StatusEnum.FAIL.getCode());         
        }                  
        out.flush();         
        out.close();         
    }
	
	@RequestMapping("updatePwd")
	public @ResponseBody Object updatePwd(@RequestParam("userAccount")String userAccount, 
			@RequestParam("oldPwd")String oldPwd, 
			@RequestParam("newPwd")String newPwd) {
		try {
			return sysUserService.updatePwd(userAccount, oldPwd, newPwd);
		} catch (Exception e) {
			logger.error("--updatePwd, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
}
