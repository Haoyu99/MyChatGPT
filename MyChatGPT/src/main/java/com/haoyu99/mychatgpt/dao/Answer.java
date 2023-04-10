package com.haoyu99.mychatgpt.dao;

import lombok.Data;

@Data
public class Answer {
    String answer;

    public Answer(String answer) {
        this.answer = answer;
    }
}
