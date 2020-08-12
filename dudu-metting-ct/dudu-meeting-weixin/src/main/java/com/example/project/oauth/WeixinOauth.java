package com.example.project.oauth;

import com.example.project.weixin.main.MenuManager;
import com.example.project.weixin.util.WeixinUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RequestMapping("weixin")
@Slf4j
@Controller
public class WeixinOauth {
    @GetMapping("oauth")
    public void oauth(HttpServletResponse response)throws IOException{
        //http://hjxweb.natapp1.cc/weixin/oauthInvoke
        String path= MenuManager.REAL_URL+"weixin/oauthInvoke";

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
                "&state=java2001hjx" +
                "#wechat_redirect";
        response.sendRedirect(url);
    }

    @GetMapping("oauthInvoke")
    @ResponseBody
    public Object oauthInvoke(HttpServletRequest request){


        //用户同意授权 得到code
        String code = request.getParameter("code");
        String state = request.getParameter("state");

        //3通过code获取网页授权access_token

        String url=" https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=" +MenuManager.appId+
                "&secret=" + MenuManager.appSecret+
                "&code=" +code+
                "&grant_type=authorization_code";

        //4返回accessToken相关信息
        JSONObject jsonObject = WeixinUtil.httpRequest(url,"POST",null);
        log.info("得到accessToken" + jsonObject);
        String accessToken = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");

        //5通过accessToken请求资源服务器

        String url1="https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + accessToken +
                "&openid=" +openid+
                "&lang=zh_CN";

        //6获取资源信息
        JSONObject json1=WeixinUtil.httpRequest(url1,"GET",null);
        return json1;
    }

}
