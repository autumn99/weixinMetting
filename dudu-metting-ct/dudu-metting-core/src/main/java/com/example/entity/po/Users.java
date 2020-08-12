package com.example.entity.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Users implements Serializable {
    private Integer id;

    private String name;

    private String email;

    private String telephone;

    private String zone;

    private Integer rid;

    private String province;

    private String city;

    private Short status;

    private Integer wid;


}