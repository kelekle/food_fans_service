package com.star.foodfans.dao;

import com.star.foodfans.entity.Videoinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoinfoDao implements VideoinfoMapper{

    @Autowired
    private VideoinfoMapper videoinfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer videoid) {
        return videoinfoMapper.deleteByPrimaryKey(videoid);
    }

    @Override
    public int insert(Videoinfo record) {
        return videoinfoMapper.insert(record);
    }

    @Override
    public Videoinfo selectByPrimaryKey(Integer videoid) {
        return videoinfoMapper.selectByPrimaryKey(videoid);
    }

    @Override
    public List<Videoinfo> selectAll() {
        return videoinfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Videoinfo record) {
        return videoinfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Videoinfo> selectVideoCollectionByUserid(int userid) {
        return videoinfoMapper.selectVideoCollectionByUserid(userid);
    }

    @Override
    public List<Videoinfo> selectVideoPublishByUserid(int userid) {
        return videoinfoMapper.selectVideoPublishByUserid(userid);
    }

    @Override
    public List<Videoinfo> selectVideoHistoryByUserid(int userid) {
        return videoinfoMapper.selectVideoHistoryByUserid(userid);
    }

    @Override
    public List<Videoinfo> selectByHot() {
        return videoinfoMapper.selectByHot();
    }

}
