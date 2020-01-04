package com.star.foodfans.dao;

import com.star.foodfans.entity.Commentinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentinfoDao implements CommentinfoMapper {

    @Autowired
    private CommentinfoMapper commentinfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer commentid) {
        return commentinfoMapper.deleteByPrimaryKey(commentid);
    }

    @Override
    public int insert(Commentinfo record) {
        return commentinfoMapper.insert(record);
    }

    @Override
    public Commentinfo selectByPrimaryKey(Integer commentid) {
        return commentinfoMapper.selectByPrimaryKey(commentid);
    }

    @Override
    public List<Commentinfo> selectAll() {
        return commentinfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Commentinfo record) {
        return commentinfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Commentinfo>  selectByContentid(String contentid) {
        return commentinfoMapper.selectByContentid(contentid);
    }

}
