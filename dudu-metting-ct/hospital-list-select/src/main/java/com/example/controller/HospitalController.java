package com.example.controller;

import com.example.entity.Hospital;
import com.example.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HospitalController {
    @Autowired
    HospitalService hospitalService;
    @RequestMapping("query")
    @ResponseBody
    public List<Hospital> queryAll(){
        List<Hospital> list = hospitalService.queryAll();

        return list;
    }
    @RequestMapping("toquery")
    public String to(){


        return "index";
    }

}
