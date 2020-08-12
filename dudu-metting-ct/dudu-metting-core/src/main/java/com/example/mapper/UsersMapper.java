package com.example.mapper;

import com.example.entity.po.Users;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);
    //根据id修改个人信息
    int updateByPrimaryKeySelective(Users user);

    int updateByPrimaryKey(Users record);

    //根据weiuser主键id查询users对象是否存在
    @Select("select * from users where wid=#{wid}")
    Users selectUserByWid(Integer wid);

    //根据输入的邮箱，判断用户是否存在
    @Select("select * from users where email=#{email}")
    Users selectUserByEmail(String email);

    //绑定流程 根据邮箱修改wid值
    @Update("update users set wid=#{wid} where email=#{email}")
    int updateWidByEmail(Integer wid,String email);















}