package com.example.project.api.hitokoto;

import com.example.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;

/**
 * @Classname HitokotoUtil
 * @Author guoweixin
 * @Description TODO 一言API调用 https://v1.hitokoto.cn/
 * @Date 2020/8/5 11:31
 * @Created by Administrator
 */
public class HitokotoUtil {
    //一言 API请求（GET）
    private static final String HITOKOTO_URL="https://v1.hitokoto.cn/?c=j";


    public static String getMsg(){
      JSONObject jsonObject= WeixinUtil.httpRequest(HITOKOTO_URL,"GET",null);
      String result=jsonObject.getString("hitokoto");
      return result;
    }


}
