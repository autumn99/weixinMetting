package com.example.dao;

import com.example.entity.Hospital;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HospitalDao {
    @Select("select InstitutionID,InstitutionName from wei_hospital_institution")
    List<Hospital> queryAll();
}
