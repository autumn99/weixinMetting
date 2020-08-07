package com.example.service.impl;

import com.example.dao.HospitalDao;
import com.example.entity.Hospital;
import com.example.service.HospitalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class HospitalServiceImpl implements HospitalService {
    @Resource
    HospitalDao hospitalDao;

    @Override
    public List<Hospital> queryAll() {
        return hospitalDao.queryAll();
    }
}
