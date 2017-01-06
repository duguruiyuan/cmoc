package com.xuequ.cmoc;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.common.WechatConfigure;
import com.xuequ.cmoc.core.wechat.message.FirstButtonMenu;
import com.xuequ.cmoc.core.wechat.message.MenuList;
import com.xuequ.cmoc.core.wechat.message.SubButtonMenu;
import com.xuequ.cmoc.core.wechat.template.Data_Add;
import com.xuequ.cmoc.core.wechat.template.Data_Clazz;
import com.xuequ.cmoc.core.wechat.template.Data_First;
import com.xuequ.cmoc.core.wechat.template.Data_Keyword;
import com.xuequ.cmoc.core.wechat.template.Data_Remark;
import com.xuequ.cmoc.core.wechat.template.Data_Time;
import com.xuequ.cmoc.core.wechat.template.OutputTemateData;
import com.xuequ.cmoc.core.wechat.template.TemplateDate;
import com.xuequ.cmoc.core.wechat.utils.WechatModel;
import com.xuequ.cmoc.core.wechat.utils.WechatUtils;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.TextUtil;

public class TestService {

	public static void main(String[] args) {
//		String url = TextUtil.format(WechatConfigure.getInstance().getTemplateMsg(), "GMs0UihDEQIs9zMZ2JR3OvTdg-pJ_yzdQkCRN7TfYtYTiAaABJa2LGppaKzWej5XHBj9BDhRIl-C4S8rYr7Ce7fhTyoILd_KjtvFv4VOXHN86VkIwA1E-tXmXkOC_mmKBAHcABAGFM");
////		OutputTemateData outputData = regMsg();
//		OutputTemateData outputData = sign();
//		try {
//			String json = JSONObject.toJSONString(outputData);
//			json = json.replace("clazz", "class");
//			String result = HttpClientUtils.postStringJosn(url, json);
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		pushMenu();
	}
	
	public static void pushMenu() {
		MenuList menuList = new MenuList();
		List<FirstButtonMenu> firstButtonMenus = new ArrayList<>();
		
		List<SubButtonMenu> subButtonMenus = new ArrayList<>();
		FirstButtonMenu firstButtonMenu = new FirstButtonMenu();
		firstButtonMenu.setName("扫码");
		SubButtonMenu subButtonMenu = new SubButtonMenu();
		subButtonMenu.setType("scancode_waitmsg");
		subButtonMenu.setName("扫码带提示");
		subButtonMenu.setKey("rselfmenu_0_0");
		subButtonMenus.add(subButtonMenu);
		SubButtonMenu subButtonMenu1 = new SubButtonMenu();
		subButtonMenu1.setType("scancode_push");
		subButtonMenu1.setName("扫码推事件");
		subButtonMenu1.setKey("rselfmenu_0_1");
		subButtonMenus.add(subButtonMenu1);
		firstButtonMenu.setSub_button(subButtonMenus);
		firstButtonMenus.add(firstButtonMenu);
		
		FirstButtonMenu firstButtonMenu2 = new FirstButtonMenu();
		firstButtonMenu2.setName("发图");
		List<SubButtonMenu> subButtonMenus2 = new ArrayList<>();
		SubButtonMenu subButtonMenu3 = new SubButtonMenu();
		subButtonMenu3.setType("pic_sysphoto");
		subButtonMenu3.setName("系统拍照发图");
		subButtonMenu3.setKey("rselfmenu_1_0");
		subButtonMenus2.add(subButtonMenu3);
		SubButtonMenu subButtonMenu4 = new SubButtonMenu();
		subButtonMenu4.setType("pic_photo_or_album");
		subButtonMenu4.setName("拍照或者相册发图");
		subButtonMenu4.setKey("rselfmenu_1_1");
		subButtonMenus2.add(subButtonMenu4);
		firstButtonMenu2.setSub_button(subButtonMenus2);
//		firstButtonMenus.add(firstButtonMenu2);
		
		FirstButtonMenu firstButtonMenu3 = new FirstButtonMenu();
		firstButtonMenu3.setName("发送位置");
		firstButtonMenu3.setType("location_select");
		firstButtonMenu3.setKey("rselfmenu_2_0");
//		firstButtonMenus.add(firstButtonMenu3);
		
		FirstButtonMenu firstButtonMenu4 = new FirstButtonMenu();
		firstButtonMenu4.setName("图文消息");
		firstButtonMenu4.setType("view");
		firstButtonMenu4.setUrl("http://www.baidu.com");
		firstButtonMenus.add(firstButtonMenu4);
		
		FirstButtonMenu firstButtonMenu5 = new FirstButtonMenu();
		firstButtonMenu5.setName("📷直播");
		firstButtonMenu5.setType("view");
		firstButtonMenu5.setUrl("http://m.xue110.top/live");
		firstButtonMenus.add(firstButtonMenu5);
		
		menuList.setButton(firstButtonMenus);
		try {
			String acc = "y8gs2X9qzuPwVOqPVnoGSjltpHZ8p2wQ4XLwzgnz9EcepzMEQ6iVUkkDc-Hc_fq4En2w03VANxwwSzv1HJy-1ghs-CdgHNNGY5xNdbwLGG7D83THLJdpaFyj6Q4Mlv1_FFZaABAJJD";//WechatUtils.getWechatModel().getAccessToken();
//			System.out.println("------"+acc);
//			String url1 = TextUtil.format("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token={0}", acc);
//			String result1 = HttpClientUtils.doPost(url1);
//			System.out.println(result1);
			String json = JSONObject.toJSONString(menuList);
			String url = TextUtil.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token={0}", acc);
			String result = HttpClientUtils.postStringJosn(url, json);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static OutputTemateData sign() {
		OutputTemateData outputData = new OutputTemateData();
		outputData.setTouser("oqyqUwq_YY84qjFWUtn6Ti4XIROE");
		outputData.setTemplate_id("H_vbGJctM_HBwxTUnyaoWD6JWJdBdYBTi0Do_s9PSWo");
		TemplateDate templateDate = new TemplateDate();
		Data_First first = new Data_First();
		first.setValue("恭喜你报名成功\n欢迎你来参加创客大赛活动");
		Data_Clazz clazz  = new Data_Clazz();
		clazz.setValue("佛山创客大赛");
		Data_Time time = new Data_Time();
		time.setValue("12月1号-1月9号");
		Data_Add add = new Data_Add();
		add.setValue("佛山创客大厦一楼\n\n");
		outputData.setData(templateDate);
		Data_Remark remark = new Data_Remark();
		remark.setValue("欢迎使用陶学趣公众号");
		templateDate.setClazz(clazz);
		templateDate.setFirst(first);
		templateDate.setTime(time);
		templateDate.setRemark(remark);
		templateDate.setAdd(add);
		return outputData;
	}
	
	public static OutputTemateData regMsg() {
		OutputTemateData outputData = new OutputTemateData();
		outputData.setTouser("oqyqUwq_YY84qjFWUtn6Ti4XIROE");
		outputData.setTemplate_id("NglAMte28uh8R-4Bj9wZRK3xIYZPk8IKCjJgaGkwYoI");
		TemplateDate templateDate = new TemplateDate();
		Data_First first = new Data_First();
		first.setValue("尊敬的胡启萌,你好！恭喜你注册成功\n");
		Data_Keyword keyword1 = new Data_Keyword();
		keyword1.setValue("胡启萌\n");
		Data_Keyword keyword2 = new Data_Keyword();
		keyword2.setValue("13681984045\n\n");
		Data_Remark remark = new Data_Remark();
		remark.setValue("欢迎使用陶学趣公众号");
		templateDate.setFirst(first);
		templateDate.setKeyword1(keyword1);
		templateDate.setKeyword2(keyword2);
		templateDate.setRemark(remark);
		outputData.setData(templateDate);
		return outputData;
	}
}
