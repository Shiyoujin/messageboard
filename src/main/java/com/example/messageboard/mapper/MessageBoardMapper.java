package com.example.messageboard.mapper;

import com.example.messageboard.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MessageBoardMapper {

    //发表留言
    @Insert("insert into message_board (username,text,pid) values (#{username},#{text},#{pid})")
    boolean insert(Message message);

    @Select("select * from message_board where pid=#{pid}")
    List<Message> findMessagesByPid(int pid);



}
