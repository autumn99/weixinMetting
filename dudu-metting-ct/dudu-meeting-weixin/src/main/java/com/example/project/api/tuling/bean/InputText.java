package com.example.project.api.tuling.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname InputText
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/8/5 14:58
 * @Created by Administrator
 */
@Data
public class InputText implements Serializable {
    /**	1-128字符	直接输入文本*/
    private String text;
}
