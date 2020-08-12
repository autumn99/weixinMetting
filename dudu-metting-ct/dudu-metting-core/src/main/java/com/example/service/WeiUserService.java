package com.example.service;

import com.example.entity.po.Weiuser;
import org.apache.ibatis.annotations.Select;

public interface WeiUserService {

    /**
     * 添加用户信息
     * @param weiuser
     * @return
     */
    int insertSelective(Weiuser weiuser);

    /**
     * 根据用户openid查询微信用户
     * @param openid
     * @return
     */
    Weiuser seletWeiUserByOpenid(String openid);

    /**
     * 根据openid查询主键
     * @param openid
     * @return
     */

    Integer selectWeiuserIdOpenid(String openid);
}
