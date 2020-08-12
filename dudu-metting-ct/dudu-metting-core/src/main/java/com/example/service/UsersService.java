package com.example.service;

import com.example.entity.po.Users;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface UsersService {
    /**
     * 根据weiuser主键id查询users对象是否存在
     */
    Users selectUserByWid(Integer wid);

    /**
     * 根据输入的邮箱，判断用户是否存在
     * @param email
     * @return
     */
    Users selectUserByEmail(String email);

    /**
     * 绑定流程 根据邮箱修改wid值
     * @param wid
     * @param email
     * @return
     */
    int updateWidByEmail(Integer wid,String email);

    /**
     * 根据id修改个人信息
     * @param user
     * @return
     */

    int updateByPrimaryKeySelective(Users user);


}
