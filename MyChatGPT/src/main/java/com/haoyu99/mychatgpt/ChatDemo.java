package com.haoyu99.mychatgpt;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.haoyu99.mychatgpt.dao.Answer;

import java.util.HashMap;
import java.util.Map;

public class ChatDemo {
  public static final String MyToken = "";
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
        message.set("content", "为什么1+1 = 2？");
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
    public static String chatApi(String question){
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/json;charset=UTF-8");

        JSONObject json = new JSONObject();
        //选择模型
        json.set("model",model);
        //添加我们需要输入的内容
        JSONObject message = new JSONObject();
        message.set("role", "user");
        message.set("content", question);
        json.append("messages", message);
        json.set("temperature", 0.7);

        long start = System.currentTimeMillis();
        HttpResponse response = HttpRequest.post("https://api.openai.com/v1/chat/completions")
                .headerMap(headers, false)
                .bearerAuth(MyToken)
                .body(String.valueOf(json))
                .execute();
        System.out.println(response.body());
        JSONObject entries = JSONUtil.parseObj(response.body());
        JSONObject choices = (JSONObject)((JSONArray)entries.get("choices")).get(0);
        JSONObject mess = (JSONObject)choices.get("message");
        String content = (String)mess.get("content");
        long end = System.currentTimeMillis();
        double l = (end - start) / 1000;
        System.out.println("响应时间" + l + " 秒");
        System.out.println(content);
        return content;
    }
}
