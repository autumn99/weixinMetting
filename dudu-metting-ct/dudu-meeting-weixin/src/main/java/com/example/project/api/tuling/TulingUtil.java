package com.example.project.api.tuling;

import com.example.project.api.tuling.bean.InputText;
import com.example.project.api.tuling.bean.Perception;
import com.example.project.api.tuling.bean.TulingBean;
import com.example.project.api.tuling.bean.UserInfo;
import com.example.project.weixin.util.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @Classname TulingUtil
 * @Author guoweixin
 * @Description TODO  智能聊天机器人（图灵）
 * @Date 2020/8/5 14:54
 * @Created by Administrator
 */
@Component
public class TulingUtil {
    /**
     * 图灵官方API开发文档教程  HTTP POST
     * https://www.kancloud.cn/turing/www-tuling123-com/718227
     */
    private static final String TULING_URL_POST = "http://openapi.tuling123.com/openapi/api/v2";

    /**
     * 1 封装请求的JSON数据
     *
     * @param msg    输入消息
     * @param apiKey 机器人key
     * @return JSONObject对象
     */
    private JSONObject sendObject(String msg, String apiKey) {
        TulingBean tulingBean = new TulingBean();
        tulingBean.setReqType(0);

        Perception perception = new Perception();

        InputText inputText = new InputText();
        inputText.setText(msg);
        perception.setInputText(inputText);

        tulingBean.setPerception(perception);

        UserInfo userInfo = new UserInfo();
        userInfo.setApiKey(apiKey);  //3号机器人
        userInfo.setUserId("java2001");

        tulingBean.setUserInfo(userInfo);
        JSONObject obj = JSONObject.fromObject(tulingBean);
        return obj;
    }

    /**
     * 2 发送POST请求得到结果值
     *
     * @param obj 封装的JSONObject
     */
    private String getResult(JSONObject obj) {
        //2发送请求API
        JSONObject objResult = WeixinUtil.httpRequest(TULING_URL_POST, "POST", obj.toString());
        JSONArray a1 = (JSONArray) objResult.get("results");
        JSONObject obj1 = (JSONObject) a1.get(0);
        JSONObject obj2 = (JSONObject) obj1.get("values");
        String result = obj2.getString("text");
        return result;
    }

    /**
     * 根据用户输入的消息，智能回复聊天信息
     *
     * @param msg
     * @return
     */
    String[] apiKey = {"ce91f9899e8448ef80bdc3a4f5e08dfd", "910e4c41cf28417692810f881e10e44f", "0fb09b7f228a48d7aedc222b84a9971d","bca0c85a17574e5bbf7edbbacb142608"};
    int i = 0;
    public String sendMessage(String msg) {
        //String apiKey="ce91f9899e8448ef80bdc3a4f5e08dfd";

        JSONObject jsonObj = sendObject(msg, apiKey[i]);
        String result = getResult(jsonObj);

            if (result.equals("请求次数超限制!")) {
                i++;
                if (i>apiKey.length-1){
                    i=0;
                    result="机器人休息";
                    //i--;
                    return result;
                }
                return sendMessage(msg);
            }



        return result;
    }


    public static void main(String[] args) {
        TulingUtil tulingUtil = new TulingUtil();
        for (int i = 0; i < 1000; i++) {
            String str = tulingUtil.sendMessage("测试");
            System.out.println(i + "---->" + str);
        }
    }


}
