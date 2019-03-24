package com.example.messageboard.service;
import com.example.messageboard.entity.Message;
import com.example.messageboard.mapper.MessageBoardMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageBoardService {
    @Autowired
    private MessageBoardMapper messageBoardMapper;

//    @Resource       //---这里为什么要用 Resource
//    private Message message;

    //留言功能
    public List<Message> findContentChild(int pid) {

        List<Message> list = messageBoardMapper.findMessagesByPid(pid);

        for (Message message : list) {
            //递归找这条评论的节点 然后赋值
            List<Message> childList = findContentChild(message.getId());
            message.setChildContent(childList);
        }
        return list;

    }

    //查找所有留言
    public String findAllMessages(){

        //先找到pid为0的所有留言 即留言板上所有无父节点的留言
        List<Message> list = messageBoardMapper.findMessagesByPid(0);

        //然后找 pid !=0 的每条留言的评论 并赋值
        for (Message message : list){
            List<Message> childList = findContentChild(message.getId());
            message.setChildContent(childList);
        }

        Gson gson = new Gson();   //将对象转化为 json
        return gson.toJson(list);
    }

    public String insert(String username,String text,int pid){
        Message message = new Message();
        message.setUsername(username);
        message.setText(text);
        message.setPid(pid);
        if (messageBoardMapper.insert(message)){
            return "留言成功!";
        }else {
            return "留言失败!";
        }
    }

    public static void main(String[] args) {
        String a =new MessageBoardService().insert("a","b",3);
        System.out.println(a);
    }
}
