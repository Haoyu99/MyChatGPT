package com.haoyu99.mychatgpt;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChatDemo {
  public static final String MyToken = "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";
  public static final String model = "gpt-3.5-turbo";
    public static void main(String[] args) {
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");

        JSONObject json = new JSONObject();
        //选择模型
        json.set("model",model);
        //添加我们需要输入的内容
        JSONObject message = new JSONObject();
        message.set("role", "user");
        message.set("content", "帮我使用java写一个快排的例子？");
        json.append("messages", message);
        json.set("temperature", 0.7);

        System.out.println(String.valueOf(json));

        HttpResponse response = HttpRequest.post("https://api.openai.com/v1/chat/completions")
                .headerMap(headers, false)
                .bearerAuth(MyToken)
                .body(String.valueOf(json))
                .timeout(5 * 60 * 1000)
                .execute();

        System.out.println(response.body());
    }
}
