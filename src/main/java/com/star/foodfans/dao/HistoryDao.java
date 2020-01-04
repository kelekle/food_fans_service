package com.star.foodfans.dao;

import com.star.foodfans.entity.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryDao implements HistoryMapper {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public int deleteByPrimaryKey(Integer userid, String contentid) {
        return historyMapper.deleteByPrimaryKey(userid, contentid);
    }

    @Override
    public int insert(History record) {
        return historyMapper.insert(record);
    }

    @Override
    public History selectByPrimaryKey(Integer userid, String contentid) {
        return historyMapper.selectByPrimaryKey(userid, contentid);
    }

    @Override
    public List<History> selectAll() {
        return historyMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(History record) {
        return historyMapper.updateByPrimaryKey(record);
    }
}
