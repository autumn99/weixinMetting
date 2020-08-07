package com.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Hospital implements Serializable {

    private String id;
    private String InstitutionID;
    private String InstitutionName;
    private String Province;
    private String City;
    private Integer status;
    private Date create_date;

}
