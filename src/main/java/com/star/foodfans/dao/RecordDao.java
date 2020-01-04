package com.star.foodfans.dao;

import com.star.foodfans.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.QuotaAwareStore;
import java.util.List;

@Component
public class RecordDao implements RecordMapper {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Record record) {
        return 0;
    }

    @Override
    public Record selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<Record> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Record record) {
        return 0;
    }

    @Override
    public List<Record> selectByUserId(int id) {
        return recordMapper.selectByUserId(id);
    }
}
