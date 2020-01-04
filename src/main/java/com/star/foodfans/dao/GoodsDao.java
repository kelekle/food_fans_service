package com.star.foodfans.dao;

import com.star.foodfans.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsDao implements GoodsMapper {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Goods record) {
        return 0;
    }

    @Override
    public Goods selectByPrimaryKey(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Goods> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        return goodsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Goods> selectBytime() {
        return goodsMapper.selectBytime();
    }

    @Override
    public List<Goods> selectByMoney() {
        return goodsMapper.selectByMoney();
    }

    @Override
    public List<Goods> selectMoneyasc() {
        return goodsMapper.selectMoneyasc();
    }

    @Override
    public int redeem(int uid, int gid) {
        return goodsMapper.redeem(uid, gid);
    }

//    @Override
//    public void redeem(int uid, int gid){return goodsMapper.redeem(uid, gid);}

}
