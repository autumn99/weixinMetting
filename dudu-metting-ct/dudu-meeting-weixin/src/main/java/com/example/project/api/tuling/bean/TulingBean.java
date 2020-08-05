package com.example.project.api.tuling.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname TulingBean
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/8/5 14:56
 * @Created by Administrator
 */
@Data
public class TulingBean  implements Serializable {
    /**输入类型:0-文本(默认)、1-图片、2-音频*/
    private int  reqType=0;
    /**注意：输入参数必须包含inputText或inputImage或inputMedia！*/
    private Perception perception;
    /**用户主体信息*/
    private UserInfo userInfo;
}
