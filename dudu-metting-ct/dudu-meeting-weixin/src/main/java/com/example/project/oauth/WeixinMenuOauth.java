package com.example.project.oauth;

import com.example.entity.po.Users;
import com.example.project.weixin.main.MenuManager;
import com.example.project.weixin.util.WeixinUtil;
import com.example.service.UsersService;
import com.example.service.WeiUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RequestMapping("weixinMenu")
@Controller
@Slf4j
public class WeixinMenuOauth {

    @Autowired
    private UsersService usersService;

    @Autowired
    private WeiUserService weiUserService;


    @GetMapping("oauth")
    private void oauth(HttpServletResponse response) throws IOException {

        String path= MenuManager.REAL_URL+"weixinMenu/oauthInvoke";

        try {
            path = URLEncoder.encode(path,"UTF-8");
            log.info("oauth授权=="+path);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }

        String url="https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + MenuManager.appId+
                "&redirect_uri=" + path +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=ct" +
                "#wechat_redirect";
        response.sendRedirect(url);
    }



    @GetMapping("oauthInvoke")
    public Object oauthInvoke(HttpServletRequest request){
        String code = request.getParameter("code");
        String state = request.getParameter("state");

        //3通过code换区网页授权access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=" +MenuManager.appId+
                "&secret=" + MenuManager.appSecret+
                "&code=" + code +
                "&grant_type=authorization_code";

        //4返回accessToken相关信息
        JSONObject jsonObject = WeixinUtil.httpRequest(url,"POST",null);
        log.info("得到accessToken" + jsonObject);
        String accessToken = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");

        //5根据openid和到weiuser_id
        Integer wid = weiUserService.selectWeiuserIdOpenid(openid);
        if (wid==null){
            log.error("用户信息未收集成功!");
        }
        //6判断当前是否完成绑定
        Users users = usersService.selectUserByWid(wid);
        log.info("-------------------------");
        if (users==null){
            //登录
            request.setAttribute("wid",wid);
            return "users/login";
        }else {
            //个人信息
            request.setAttribute("users",users);
            return "users/userInfo";
        }


    }
}

