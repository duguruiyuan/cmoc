package com.xuequ.cmoc;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.xuequ.cmoc.core.wechat.message.FirstButtonMenu;
import com.xuequ.cmoc.core.wechat.message.MenuList;
import com.xuequ.cmoc.core.wechat.message.SubButtonMenu;
import com.xuequ.cmoc.utils.HttpClientUtils;
import com.xuequ.cmoc.utils.TextUtil;

public class PushMenuService {
	
	public static void main(String[] args) {
		menu20170222();
	}
	
	public static void menu20160115() {
		MenuList menuList = new MenuList();
		List<FirstButtonMenu> firstButtonMenus = new ArrayList<>();
		
		List<SubButtonMenu> subButtonMenus = new ArrayList<>();
		FirstButtonMenu firstButtonMenu = new FirstButtonMenu();
		firstButtonMenu.setName("🌏介绍");
		SubButtonMenu subButtonMenu = new SubButtonMenu();
		subButtonMenu.setType("scancode_waitmsg");
		subButtonMenu.setName("扫码带提示");
		subButtonMenu.setKey("rselfmenu_0_0");
//		subButtonMenus.add(subButtonMenu);
		SubButtonMenu subButtonMenu1 = new SubButtonMenu();
		subButtonMenu1.setType("scancode_push");
		subButtonMenu1.setName("扫码推事件");
		subButtonMenu1.setKey("rselfmenu_0_1");
//		subButtonMenus.add(subButtonMenu1);
		SubButtonMenu subButtonMenu02 = new SubButtonMenu();
		subButtonMenu02.setType("view");
		subButtonMenu02.setName("校辅实践");
		subButtonMenu02.setUrl("http://mp.weixin.qq.com/s/_bjOQwWFy4M3UBgOyECzsw");
		subButtonMenus.add(subButtonMenu02);
		SubButtonMenu subButtonMenu03 = new SubButtonMenu();
		subButtonMenu03.setType("view");
		subButtonMenu03.setName("学科探究");
		subButtonMenu03.setUrl("http://mp.weixin.qq.com/s/IA8xTeOidtw89ilqOLBZrA");
		subButtonMenus.add(subButtonMenu03);
		SubButtonMenu subButtonMenu04 = new SubButtonMenu();
		subButtonMenu04.setType("view");
		subButtonMenu04.setName("STEAM创客实践");
		subButtonMenu04.setUrl("");
//		subButtonMenus.add(subButtonMenu04);
		SubButtonMenu subButtonMenu05 = new SubButtonMenu();
		subButtonMenu05.setType("view");
		subButtonMenu05.setName("营地教育");
		subButtonMenu05.setUrl("http://mp.weixin.qq.com/s/XrzT92mO3BgJSCMNquMO8Q");
		subButtonMenus.add(subButtonMenu05);
		SubButtonMenu subButtonMenu07 = new SubButtonMenu();
		subButtonMenu07.setType("view");
		subButtonMenu07.setName("STEAM创客");
		subButtonMenu07.setUrl("http://mp.weixin.qq.com/s/kcUP6k8YkP6MDpNEkMBE8A");
		subButtonMenus.add(subButtonMenu07);
		SubButtonMenu subButtonMenu06 = new SubButtonMenu();
		subButtonMenu06.setType("view");
		subButtonMenu06.setName("关于我们");
		subButtonMenu06.setUrl("http://mp.weixin.qq.com/s/F4eDKmVwmYXGCtubGVUrMw");
		subButtonMenus.add(subButtonMenu06);
		
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
		firstButtonMenu4.setName("📡图文消息");
		firstButtonMenu4.setType("click");
		firstButtonMenu4.setKey("rselfmenu_3_0");
		firstButtonMenus.add(firstButtonMenu4);
		
		FirstButtonMenu firstButtonMenu5 = new FirstButtonMenu();
		firstButtonMenu5.setName("📷直播");
		firstButtonMenu5.setType("view");
		firstButtonMenu5.setUrl("http://m.xue110.top/live");
		firstButtonMenus.add(firstButtonMenu5);
		
		menuList.setButton(firstButtonMenus);
		
		pushMenu(menuList);
	}
	
	public static void menu20170222() {
		MenuList menuList = new MenuList();
		List<FirstButtonMenu> firstButtonMenus = new ArrayList<>();
		
		FirstButtonMenu firstButtonMenu01 = new FirstButtonMenu();
		List<SubButtonMenu> subButtonMenus01 = new ArrayList<>();
		firstButtonMenu01.setName("🙇趣看看");
		
		SubButtonMenu subButtonMenu001 = new SubButtonMenu();
		subButtonMenu001.setName("图文消息");
		subButtonMenu001.setType("click");
		subButtonMenu001.setKey("rselfmenu_3_0");
		subButtonMenus01.add(subButtonMenu001);
		
		SubButtonMenu subButtonMenu002 = new SubButtonMenu();
		subButtonMenu002.setName("回顾活动");
		subButtonMenu002.setType("view");
		subButtonMenu002.setUrl("http://m.xue110.top/index");
		subButtonMenus01.add(subButtonMenu002);
		
		firstButtonMenu01.setSub_button(subButtonMenus01);
		firstButtonMenus.add(firstButtonMenu01);
		
		FirstButtonMenu firstButtonMenu5 = new FirstButtonMenu();
		firstButtonMenu5.setName("📚趣学学");
		firstButtonMenu5.setType("view");
		firstButtonMenu5.setUrl("http://m.xue110.top/course");
		firstButtonMenus.add(firstButtonMenu5);
		
		List<SubButtonMenu> subButtonMenus = new ArrayList<>();
		FirstButtonMenu firstButtonMenu = new FirstButtonMenu();
		firstButtonMenu.setName("🙇趣我的");
		SubButtonMenu subButtonMenu02 = new SubButtonMenu();
		subButtonMenu02.setType("view");
		subButtonMenu02.setName("直播");
		subButtonMenu02.setUrl("http://m.xue110.top/live");
		subButtonMenus.add(subButtonMenu02);
		SubButtonMenu subButtonMenu03 = new SubButtonMenu();
		subButtonMenu03.setType("view");
		subButtonMenu03.setName("我的资料");
		subButtonMenu03.setUrl("http://m.xue110.top/my");
		subButtonMenus.add(subButtonMenu03);
		SubButtonMenu subButtonMenu05 = new SubButtonMenu();
		subButtonMenu05.setType("view");
		subButtonMenu05.setName("我的关注");
		subButtonMenu05.setUrl("http://m.xue110.top/user/bind");
		subButtonMenus.add(subButtonMenu05);
		SubButtonMenu subButtonMenu06 = new SubButtonMenu();
		subButtonMenu06.setName("在线客服");
		subButtonMenu06.setType("click");
		subButtonMenu06.setKey("transfer_customer_service");
		subButtonMenus.add(subButtonMenu06);
		
		firstButtonMenu.setSub_button(subButtonMenus);
		firstButtonMenus.add(firstButtonMenu);
		
		menuList.setButton(firstButtonMenus);
		
		pushMenu(menuList);
	}
	
	private static void pushMenu(MenuList menuList) {
		try {
			String acc = "8cFOC_syXFW8M9-07f927S1fbH4JyFcMJXOp2EAwaxPYfIg01bPgk3SrD2_I0dzHop2lhVcDDuOCz7UyGhe7pmP_P4PUMvXf9wQu39vKKIImeKN4R68xrZKfRyP9qPMwCMGhAEAYIC";//WechatUtils.getWechatModel().getAccessToken();
			String json = JSONObject.toJSONString(menuList);
			String url = TextUtil.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token={0}", acc);
			String result = HttpClientUtils.postStringJosn(url, json);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
