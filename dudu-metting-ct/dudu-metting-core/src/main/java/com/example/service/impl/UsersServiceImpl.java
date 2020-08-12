package com.example.service.impl;

import com.example.entity.po.Users;
import com.example.mapper.UsersMapper;
import com.example.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {
    @Resource
    UsersMapper usersMapper;

    @Override
    public Users selectUserByWid(Integer wid) {
        return usersMapper.selectUserByWid(wid);
    }

    @Override
    public Users selectUserByEmail(String email) {
        return usersMapper.selectUserByEmail(email);
    }

    @Override
    public int updateWidByEmail(Integer wid, String email) {
        return usersMapper.updateWidByEmail(wid,email);
    }

    @Override
    public int updateByPrimaryKeySelective(Users user) {
        return usersMapper.updateByPrimaryKeySelective(user);
    }


}
