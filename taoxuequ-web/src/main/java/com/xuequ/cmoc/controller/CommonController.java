package com.xuequ.cmoc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xuequ.cmoc.utils.FileUtils;
import com.xuequ.cmoc.utils.MimeTypeUtils;

@RequestMapping("common")
@Controller
public class CommonController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(CommonController.class);

	@RequestMapping("home")
	public String home() {
		return "home";
	}
	
	@RequestMapping("upload")
    public void fileDown(@RequestParam(value="fileName") String fileName, 
    		HttpServletRequest request, HttpServletResponse response) {
    	FileInputStream fis = null;
		OutputStream out = null;
    	try{
    		String tempPath = this.getClass().getClassLoader().getResource("/").getPath();
        	tempPath = tempPath.replace("/WEB-INF/classes", "/template");
        	fileName = URLDecoder.decode(fileName,"UTF-8");
            // 获取文件的扩展名
            String extName = FileUtils.getExtName(fileName);
            // 获取文件类型
            File file = new File(tempPath + fileName);
            if(!file.exists()) {
            	throw new FileNotFoundException();
            }
            //下载报表
			response.setContentType(MimeTypeUtils.getMimeType(extName));
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            String len = String.valueOf(file.length());
            response.setHeader("Content-Length", len);
            out = response.getOutputStream();
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int n;
            while((n=fis.read(b))!=-1){
                out.write(b, 0, n);
            }
            out.flush();
    	}catch(FileNotFoundException e) {
    		PrintWriter printout;
			try {
				printout = response.getWriter();
				printout.print("<!DOCTYPE html><meta charset=\"UTF-8\" /><script src=\"../js/jquery.min.js\"></script>"
						+"<script src=\"../js/plugin/artDialog/artDialog.source.js?skin=chrome\"></script>"
						+ "<script src=\"../js/common.js\"></script>"
						+ "<body style='font-size:12px'><script language='javascript'>"
						+ "diaAlert('error','抱歉,您选择下载的文档不存在！');setTimeout(function(){history.back();},3000)</script>"
						+ "</body>");
			} catch (IOException e1) {
				logger.error("method downloadReport error:{}",e1);
			}
    	}catch (Exception e) {
			logger.error("文件下载异常,{}", e);
		}finally {
			try {
				if (out != null) {
					out.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				logger.error("method downloadReport close Stream IOException:{}",e);
			}

		}
	}
}
