package com.example.service.impl;

import com.example.entity.po.Weiuser;
import com.example.mapper.WeiuserMapper;
import com.example.service.WeiUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WeiUserServiceImpl implements WeiUserService {

    @Autowired(required = false)
    WeiuserMapper weiuserMapper;


    /**
     * 添加用户信息
     * @param weiUser
     * @return
     */
   @Override
    public int insertSelective(Weiuser weiUser) {
        log.info("收集用户信息=====>"+weiUser);
        return weiuserMapper.insertSelective(weiUser);
    }

    /**
     * 根据用户openid查询微信用户
     * @param openid
     * @return
     */
    @Override
    public Weiuser seletWeiUserByOpenid(String openid) {
        return weiuserMapper.seletWeiUserByOpenid(openid);
    }

    @Override
    public Integer selectWeiuserIdOpenid(String openid) {
        return weiuserMapper.selectWeiuserIdOpenid(openid);
    }
}
