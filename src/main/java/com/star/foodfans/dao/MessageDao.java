package com.star.foodfans.dao;

import com.star.foodfans.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageDao implements MessageMapper {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int deleteByPrimaryKey(Integer msgid) {
        return messageMapper.deleteByPrimaryKey(msgid);
    }

    @Override
    public int insert(Message record) {
        return messageMapper.insert(record);
    }

    @Override
    public Message selectByPrimaryKey(Integer msgid) {
        return messageMapper.selectByPrimaryKey(msgid);
    }

    @Override
    public List<Message> selectAll() {
        return messageMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Message record) {
        return messageMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Message> selectOnesMessage(Integer uid) {
        return messageMapper.selectOnesMessage(uid);
    }

    @Override
    public void readMessage(Integer uid) {
       messageMapper.readMessage(uid);
    }
}
