package com.xuequ.cmoc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.core.wechat.common.Constants;
import com.xuequ.cmoc.core.wechat.common.NewsTypeEnum;
import com.xuequ.cmoc.core.wechat.message.ArticleItem;
import com.xuequ.cmoc.core.wechat.utils.WechatGlobalMap;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.model.HollowManInfo;
import com.xuequ.cmoc.model.ImgGroup;
import com.xuequ.cmoc.model.WechatUserInfo;
import com.xuequ.cmoc.service.IHollowManService;
import com.xuequ.cmoc.utils.MimeTypeUtils;

@Controller
public class IndexController extends BaseController{
	
	@Autowired
	private IHollowManService hollowManService;

	@RequestMapping(value = {"", "/", "index"})
	public String index(Model model) {
		ImgGroup group = new ImgGroup();
		group.setPosition("2");
		group.setShelves(1);
		model.addAttribute("hotCourse", contentManageService.selectListByParam(group));
		group.setPosition("3");
		model.addAttribute("hotBack", contentManageService.selectListByParam(group));
		group.setPosition("1");
		group.setShelves(1);
		model.addAttribute("topBannerList",contentManageService.selectListByParam(group));
		return "index";
	}
	
	@RequestMapping("class")
	public String classz() {
		return "class";
	}
	
	@RequestMapping("my")
	public String my(HttpServletResponse response, Model model) {
		WechatUserInfo userInfo = getWechatUserInfo();
		model.addAttribute(Constants.WECHAT_USERINFO, userInfo);
		HollowManInfo hm = hollowManService.selectByOpenid(userInfo.getOpenid());
		if(hm != null) {
			model.addAttribute("hm", hm);
			return "hm/hmMy";
		}
		return "my";
	}
	
	@RequestMapping("about")
	public String about() {
		return "about";
	}
	/**
	 * 热门课程
	 * @return
	 */
	@RequestMapping("hotCourse")
	public @ResponseBody Object hotCourse() {
		ImgGroup group = new ImgGroup();
		group.setPosition("2");
		group.setShelves(1);
		return contentManageService.selectListByParam(group);
	}
	/**
	 * 头部banner
	 * @return
	 */
	@RequestMapping("topBannerList")
	public @ResponseBody Object topBannerList() {
		ImgGroup group = new ImgGroup();
		group.setPosition("1");
		group.setShelves(1);
		return contentManageService.selectListByParam(group);
	}
	
	@RequestMapping("xuequ/{url}")
	public void fileOut(HttpServletResponse response, @PathVariable String url) throws Exception {
		String extName = url.substring(0, url.indexOf(Const.REPLACE_SEPARATOR));
		url = url.substring(url.indexOf(Const.REPLACE_SEPARATOR)+2, url.length()) + Const.DOT + extName;
		url = url.replaceAll(Const.REPLACE_SEPARATOR, Const.SEPARATOR);
		File file = new File(Const.rootPath + Const.SEPARATOR + url);
		FileInputStream is = new FileInputStream(file);  
        int i = is.available(); // 得到文件大小  
        byte data[] = new byte[i];  
        is.read(data); // 读数据  
        is.close();  
        response.setContentType(MimeTypeUtils.getMimeType(extName)); // 设置返回的文件类型  
        OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象  
        toClient.write(data); // 输出数据  
        toClient.close();  
	}
	
	@RequestMapping("msg/refresh")
	@ResponseBody Object msgRefresh() {
		try {
			WechatGlobalMap.put(NewsTypeEnum.NEWS_INFO.getCode(), null);
			List<ArticleItem> list= WechatUtils.getMaterialMediaList(NewsTypeEnum.NEWS_INFO.getCode());
			return new RspResult(StatusEnum.SUCCESS, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RspResult(StatusEnum.FAIL);
	}
}
