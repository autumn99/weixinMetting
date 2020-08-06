package com.example.project.api.accessToken;

import com.example.project.weixin.main.MenuManager;
import com.example.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;

/**
 *access_token是公众号的全局唯一接口调用凭据，
 * 公众号调用各接口时都需使用access_token。开发者需要进行妥善保存。
 * access_token的存储至少要保留512个字符空间。
 * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。
 */
public class AccessTokenThread extends Thread{
    public static String ACCESS_TOKEN_VAL;

    @Override
    public void run() {

        while (true){
            ACCESS_TOKEN_VAL=this.getAccessToken();
            System.out.println("获取最新的token"+ACCESS_TOKEN_VAL);
            //微信服务器获取access token
            try {
                Thread.sleep(1000*60*60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //#######获取微信access token
    private static final String ACCESS_TOKEN_GET_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public String getAccessToken(){
        String url=ACCESS_TOKEN_GET_URL.replace("APPID", MenuManager.appId).replace("APPSECRET",MenuManager.appSecret);
        JSONObject json = WeixinUtil.httpRequest(url, "GET", null);
        System.out.println(json);
        return json.getString("access_token");
    }



}
