package com.xuequ.cmoc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.model.HollowManInfo;
import com.xuequ.cmoc.model.WechatSnsToken;
import com.xuequ.cmoc.service.IHollowManService;
import com.xuequ.cmoc.utils.MimeTypeUtils;

@Controller
public class IndexController extends BaseController{
	
	@Autowired
	private IHollowManService hollowManService;

	@RequestMapping(value = {"", "/"})
	public String index() {
		return "index";
	}
	
	@RequestMapping("class")
	public String classz() {
		return "class";
	}
	
	@RequestMapping("my")
	public String my(Model model) {
		String openid = "oqyqUwq_YY84qjFWUtn6Ti4XIROE1";
		model.addAttribute("openid", openid);
		return "hm/hmMy";
//		String page = "my";
//		String redir = wechatRedirect(model, page);
//		if(redir.equals(page)) {
//			WechatSnsToken token = (WechatSnsToken) model.asMap().get("snsToken");
//			String openid = request.getParameter("openid");
//			if(token != null || StringUtils.isNotBlank(openid)) {
//				if(token != null) openid = token.getOpenid();
//				HollowManInfo hm = hollowManService.selectByOpenid(token.getOpenid());
//				if(hm != null) {
//					model.addAttribute("hm", hm);
//					return "hm/hmMy";
//				}
//			}
//		}
//		return redir;
	}
	
	@RequestMapping("about")
	public String about() {
		return "about";
	}
	
	@RequestMapping("xuequ/{url}")
	public void fileOut(HttpServletResponse response, @PathVariable String url) {
		String extName = url.substring(0, url.indexOf(Const.REPLACE_SEPARATOR));
		url = url.substring(url.indexOf(Const.REPLACE_SEPARATOR)+2, url.length()) + Const.DOT + extName;
		url = url.replaceAll(Const.REPLACE_SEPARATOR, Const.SEPARATOR);
		File file = new File(Const.rootPath + Const.SEPARATOR + url);
		FileInputStream is;  
        try {  
            is = new FileInputStream(file);  
            int i = is.available(); // 得到文件大小  
            byte data[] = new byte[i];  
            is.read(data); // 读数据  
            is.close();  
            response.setContentType(MimeTypeUtils.getMimeType(extName)); // 设置返回的文件类型  
            OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象  
            toClient.write(data); // 输出数据  
            toClient.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
}
