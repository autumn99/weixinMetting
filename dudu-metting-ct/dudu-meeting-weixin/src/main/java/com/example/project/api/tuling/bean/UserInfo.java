package com.example.project.api.tuling.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname UserInfo
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/8/5 15:00
 * @Created by Administrator
 */
@Data
public class UserInfo  implements Serializable {
    /**机器人标识 */
    private String apiKey;
    /**用户唯一标识*/
    private String userId;
}
