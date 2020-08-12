package com.example.project.api.accessToken;

import com.example.project.weixin.main.MenuManager;
import com.example.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class AccessTokenRedis {

    private static final String REDIS_KEY_ACCESS_TOKEN = "access_token";

    private static final String ACCESS_TOKEN_GET_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public String getAccessTokenValue(){

        if (redisTemplate.hasKey(REDIS_KEY_ACCESS_TOKEN)){
            return redisTemplate.opsForValue().get(REDIS_KEY_ACCESS_TOKEN);
        }else {
            String result = getAccessToken();
            redisTemplate.opsForValue().set(REDIS_KEY_ACCESS_TOKEN,result);
            redisTemplate.expire(REDIS_KEY_ACCESS_TOKEN,2,TimeUnit.HOURS);
            return result;
        }
    }


    public  String getAccessToken(){
        String url = ACCESS_TOKEN_GET_URL.replace("APPID", MenuManager.appId).replace("APPSECRET",MenuManager.appSecret);
        JSONObject json = WeixinUtil.httpRequest(url,"GET",null);

        return json.getString("access_token");
    }

}
