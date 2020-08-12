package com.example.mapper;

import com.example.entity.po.Weiuser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WeiuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Weiuser record);

    int insertSelective(Weiuser record);

    Weiuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weiuser record);

    int updateByPrimaryKey(Weiuser record);

    //根据openid查询weiuser对象
    @Select("select * from weiusers where openid=#{openid} ")
    Weiuser seletWeiUserByOpenid(String openid);

    //根据openid查询主键
    @Select("select id from weiusers where openid=#{openid} ")
    Integer selectWeiuserIdOpenid(String openid);





}