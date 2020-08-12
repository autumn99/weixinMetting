package com.example.entity.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Weiuser implements Serializable {
    private Integer id;

    private String openid;

    private String nickname;

    private Short sex;

    private String city;

    private String country;

    private String province;

    private String headimgurl;

    private Integer subscribe;

    private String language;

    private String remark;

    //数据库里不存在的字段
    private int subscribe_time;
    private String unionid;
    private String groupid;
    private String[] tagid_list;
    private String subscribe_scene;
    private String qr_scene;
    private String qr_scene_str;
}