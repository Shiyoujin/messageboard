package com.example.messageboard.controller;


import com.example.messageboard.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private MessageBoardService messageBoardService;

    //发表留言
    @PostMapping(value = "insert",produces = "application/json")
    public String insert (String username,String text,int pid){
        return messageBoardService.insert(username,text,pid);
    }

    //查找出 所有留言
    @GetMapping(value = "finAll",produces = "application/json")
    public String findAll(){
        return messageBoardService.findAllMessages();
    }
}
