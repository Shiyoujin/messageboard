package com.example.messageboard.entity;


import com.google.gson.Gson;
import lombok.Data;
import java.util.Collections;
import java.util.List;

//留言板实体
@Data
public class Message {

    private int id;

    private int pid;

    private String username;

    private String text;

    private List<Message> childContent;

    //构造方法
    public Message(String username, String text, int pid) {
        this.username = username;
        this.text = text;
        this.pid = pid;
    }

    public Message(){

    }

    //测试 Gson 功能
    public static void main(String[] args) {
        Message messageA = new Message("a","b",0);
        Message messageB = new Message("aa","bb",0);
        messageA.setChildContent(Collections.singletonList(messageB));
        Gson gson = new Gson();
        System.out.println(gson.toJson(messageA));
    }

}
