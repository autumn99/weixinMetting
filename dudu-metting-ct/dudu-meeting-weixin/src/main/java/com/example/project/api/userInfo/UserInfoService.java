package com.example.project.api.userInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.entity.po.Weiuser;
import com.example.project.api.accessToken.AccessTokenRedis;
import com.example.project.weixin.util.WeixinUtil;
import com.example.service.WeiUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 收集微信用户信息
 */
@Slf4j
@Service
public class UserInfoService {

    @Autowired
    private AccessTokenRedis accessTokenRedis;

    @Autowired
    private WeiUserService weiUserService;


    public void weiUserInfo(String openid){

        Weiuser weiUser1 = weiUserService.seletWeiUserByOpenid(openid);
        if (weiUser1==null) {
            //1.发送微信API请求获得用户微信相关信息
            JSONObject jsonObject = this.getUserInfo(openid);
            //2.将JSON转化为Javabean对象,即weiuser对象
            Weiuser weiuser = this.convertJSONObjectToBean(jsonObject);
            //3.将JavaBean插入到数据库表中
            this.insertWeiUser(weiuser);
        }else {
            log.info("该用户已存在"+weiUser1);
        }
    }



    //http请求方式: GET https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
    private static final String WEIXIN_USER_INFO_GET_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    //1.发送微信API请求获得用户微信相关信息
    public JSONObject getUserInfo(String openid){
        String url = WEIXIN_USER_INFO_GET_URL.replace("ACCESS_TOKEN",accessTokenRedis.getAccessToken()).replace("OPENID",openid);
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);
        log.info("1.发送微信API请求获得用户微信相关信息"+jsonObject);
        return jsonObject;
    }

    //2.将JSON转化为Javabean对象,即weiuser对象
    public Weiuser convertJSONObjectToBean(JSONObject jsonObject){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Weiuser weiuser = objectMapper.readValue(jsonObject.toString(), Weiuser.class);
            log.info("第二步将JSON转化为Javabean对象,即weiuser对象"+weiuser);
            return weiuser;
        } catch (JsonProcessingException e) {
            log.error("json对象转为user发生错误,参数不匹配:"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    //3.将JavaBean插入到数据库表中
    public void insertWeiUser(Weiuser weiuser){
        int num = weiUserService.insertSelective(weiuser);
        log.info("第三步用户信息插入成功"+num);
    }

}
