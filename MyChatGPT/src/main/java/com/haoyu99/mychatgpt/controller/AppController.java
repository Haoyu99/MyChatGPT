package com.haoyu99.mychatgpt.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.haoyu99.mychatgpt.ChatDemo;
import com.haoyu99.mychatgpt.dao.Answer;
import com.haoyu99.mychatgpt.dao.Question;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {

    @RequestMapping(value = "question" ,produces = "application/json;charset=utf-8")
    @ResponseBody
    @CrossOrigin(origins = "*",maxAge = 3600)
    public Answer test2(@RequestBody Question q){
        String question = q.getQuestion();
        System.out.println(question);
        String answerStr = ChatDemo.chatApi(question);
        Answer answer = new Answer(answerStr);
        return answer;
    }


}
