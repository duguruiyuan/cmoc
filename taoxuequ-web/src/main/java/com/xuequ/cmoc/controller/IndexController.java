package com.xuequ.cmoc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.common.Configuration;
import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.utils.PropertiesUtil;

@Controller
public class IndexController extends BaseController{
	
	private String activityroot = PropertiesUtil.getProperty(Configuration.getInstance().getEnv() + "_attachment.activity");

	@RequestMapping(value = {"","/","index"})
	public String index() {
		return "index";
	}
	
	@RequestMapping("xuequ/{url}")
	public void fileOut(HttpServletResponse response, @PathVariable String url) {
		String extName = url.substring(0, url.indexOf(Const.REPLACE_SEPARATOR));
		url = url.substring(url.indexOf(Const.REPLACE_SEPARATOR)+2, url.length()) + Const.DOT + extName;
		url = url.replaceAll(Const.REPLACE_SEPARATOR, Const.SEPARATOR);
		File file = new File(activityroot + Const.SEPARATOR + url);
		FileInputStream is;  
        try {  
            is = new FileInputStream(file);  
            int i = is.available(); // 得到文件大小  
            byte data[] = new byte[i];  
            is.read(data); // 读数据  
            is.close();  
            response.setContentType("image/*"); // 设置返回的文件类型  
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
