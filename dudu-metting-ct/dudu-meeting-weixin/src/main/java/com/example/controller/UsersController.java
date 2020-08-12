package com.example.controller;

import com.example.entity.po.Users;
import com.example.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("users/weixin")
@Slf4j
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("login")//users/weixin/login
    @ResponseBody
    public String login(final String email,final Integer wid) {
        //用户输入邮箱，判断输入邮箱是否存在
        Users user = usersService.selectUserByEmail(email);
        if (user != null) {
            //邮箱存在
            if (user.getWid() != null) {
                //判断邮箱已被人绑定
                return "1";//该邮箱已被人绑定
            } else {
                //未被别人绑定 执行绑定业务逻辑
                int i = usersService.updateWidByEmail(wid, email);
                return "2";//绑定成功
            }
        } else {
            //邮箱不存在
            return "3";//输入的邮箱不存在
        }
    }
    @PostMapping("update")//users/weixin/update
    @ResponseBody
    public String update (Users user ){
        int i = usersService.updateByPrimaryKeySelective(user);
        log.info("修改个人信息"+user+"修改结果"+i);
        return i+"";

    }

}
