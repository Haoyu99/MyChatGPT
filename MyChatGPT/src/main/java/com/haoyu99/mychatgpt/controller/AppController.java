package com.haoyu99.mychatgpt.controller;

import cn.hutool.json.JSONObject;
import com.haoyu99.mychatgpt.ChatDemo;
import com.haoyu99.mychatgpt.dao.Question;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @PostMapping("question")
    public String test2(@RequestBody Question q){
        String question = q.getQuestion();
        System.out.println(question);
        String answer = ChatDemo.chatApi(question);
        return answer;

    }


}
