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
	
	public final static String REAL_URL="http://weixinguo.xicp.net/maven_weixin/"; //个人花生壳
	//public final static String REAL_URL = "http://wxmobsa.yidatec.com/weixin/"; //正式号服务器	
	
	public final static String appId="wxb31a69531cf161f1";
	public final static String appSecret = "0cfe00663801485effc10bccb53632e7";
	
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
		btn10.setName("清纯");
		btn10.setType("view");
		btn10.setUrl("https://m.baidu.com/sf/vsearch?pd=image_content&word=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&tn=vsearch&atn=page&sa=vs_ala_img&fr=alawise&cardserver=2&title=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&lid=11996303337073768294&referlid=11996303337073768294&ms=1&frsrcid=4&frorder=1");
		
		
//-------------------------------------------------------
		CommonButton btn20 = new CommonButton();
		btn20.setName("");
		btn20.setType("click");
		btn20.setKey("1");
		
		ViewButton btn21 = new ViewButton();
		btn21.setName("诱惑");
		btn21.setType("view");
		btn21.setUrl("https://m.baidu.com/sf/vsearch?pd=image_content&word=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&tn=vsearch&atn=page&sa=vs_ala_img&fr=alawise&cardserver=2&title=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&lid=11996303337073768294&referlid=11996303337073768294&ms=1&frsrcid=4&frorder=1");

//------------------------------------------------------------		
		CommonButton btn31 = new CommonButton(); //返回图文消息
		btn31.setName("");
		btn31.setType("click");
		btn31.setKey("");
		
		//###############################################一级子菜单
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("惊喜");
		mainBtn1.setSub_button(new Button[] { btn10});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("刺激");  //
		mainBtn2.setSub_button(new Button[] { btn21});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("e");// btn31, btn32, btn33,
		mainBtn3.setSub_button(new Button[] {btn31});

		/**
		 * 这是公众号目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2 });

		return menu;
	}
}