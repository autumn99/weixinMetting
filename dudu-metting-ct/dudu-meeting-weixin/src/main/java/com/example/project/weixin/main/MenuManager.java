package com.example.project.weixin.main;

import com.example.project.weixin.pojo.*;
import com.example.project.weixin.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单管理器类
 * 
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
/***
 * 自定义菜单的创建步骤
	1、找到AppId和AppSecret。自定义菜单申请成功后，在“高级功能”-“开发模式”-“接口配置信息”的最后两项就是；
	2、根据AppId和AppSecret，以https get方式获取访问特殊接口所必须的凭证access_token；
	3、根据access_token，将json格式的菜单数据通过https post方式提交。

 */
	
	public final static String REAL_URL="http://k6hyij.natappfree.cc/"; //个人花生壳
	//public final static String REAL_URL = ""; //正式号服务器	
	
	public final static String appId="";
	public final static String appSecret = "";
	
	public static void resultMenu(){
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}
	
	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = MenuManager.appId;
		// 第三方用户唯一凭证密钥
		String appSecret = MenuManager.appSecret;
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(),at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {

		
		ViewButton btn10 = new ViewButton();
		btn10.setName("会议评价");
		btn10.setType("view");
		btn10.setUrl("https://m.baidu.com/sf/vsearch?pd=image_content&word=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&tn=vsearch&atn=page&sa=vs_ala_img&fr=alawise&cardserver=2&title=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&lid=11996303337073768294&referlid=11996303337073768294&ms=1&frsrcid=4&frorder=1");

		CommonButton btn11 = new CommonButton();
		btn11.setName("会议抢单");
		btn11.setType("click");
		btn11.setKey("11");

		ViewButton btn12 = new ViewButton();
		btn12.setName("会议发布");
		btn12.setType("view");
		btn12.setUrl("https://m.baidu.com/sf/vsearch?pd=image_content&word=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&tn=vsearch&atn=page&sa=vs_ala_img&fr=alawise&cardserver=2&title=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&lid=11996303337073768294&referlid=11996303337073768294&ms=1&frsrcid=4&frorder=1");

//-------------------------------------------------------
		CommonButton btn20 = new CommonButton();
		btn20.setName("每日签到");
		btn20.setType("click");
		btn20.setKey("20");
		
		ViewButton btn21 = new ViewButton();
		btn21.setName("抢单排行榜");
		btn21.setType("view");
		btn21.setUrl("https://m.baidu.com/sf/vsearch?pd=image_content&word=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&tn=vsearch&atn=page&sa=vs_ala_img&fr=alawise&cardserver=2&title=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&lid=11996303337073768294&referlid=11996303337073768294&ms=1&frsrcid=4&frorder=1");

		ViewButton btn22 = new ViewButton();
		btn22.setName("发单排行榜");
		btn22.setType("view");
		btn22.setUrl("https://m.baidu.com/sf/vsearch?pd=image_content&word=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&tn=vsearch&atn=page&sa=vs_ala_img&fr=alawise&cardserver=2&title=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&lid=11996303337073768294&referlid=11996303337073768294&ms=1&frsrcid=4&frorder=1");

//------------------------------------------------------------
		ViewButton btn30 = new ViewButton();
		btn30.setName("个人中心");
		btn30.setType("view");
		btn30.setUrl(REAL_URL+"weixinMenu/oauth");

		ViewButton btn31 = new ViewButton();
		btn31.setName("版本信息");
		btn31.setType("view");
		btn31.setUrl("https://m.baidu.com/sf/vsearch?pd=image_content&word=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&tn=vsearch&atn=page&sa=vs_ala_img&fr=alawise&cardserver=2&title=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&lid=11996303337073768294&referlid=11996303337073768294&ms=1&frsrcid=4&frorder=1");


		CommonButton btn32 = new CommonButton(); //返回图文消息
		btn32.setName("联系我们");
		btn32.setType("click");
		btn32.setKey("32");
		
		//###############################################一级子菜单
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("会议");
		mainBtn1.setSub_button(new Button[] { btn10,btn11,btn12});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("公告版");  //
		mainBtn2.setSub_button(new Button[] { btn20,btn21,btn22});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("系统消息");// btn31, btn32, btn33,
		mainBtn3.setSub_button(new Button[] {btn30,btn31,btn32});

		/**
		 * 这是公众号目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2 ,mainBtn3});

		return menu;
	}
}
