package com.star.foodfans.dao;

import com.star.foodfans.entity.Articleinfo;
import com.star.foodfans.entity.Videoinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ArticleinfoDao implements ArticleinfoMapper {

    @Autowired
    private ArticleinfoMapper articleinfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer articleid) {
        return articleinfoMapper.deleteByPrimaryKey(articleid);
    }

    @Override
    public int insert(Articleinfo record) {
        return articleinfoMapper.insert(record);
    }

    @Override
    public Articleinfo selectByPrimaryKey(Integer articleid) {
        return articleinfoMapper.selectByPrimaryKey(articleid);
    }

    @Override
    public List<Articleinfo> selectAll() {
        return articleinfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Articleinfo record) {
        return articleinfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Articleinfo> selectArticleCollectionByUserid(int userid) {
//        System.out.println("dao: "+articleinfoMapper.selectArticleCollectionByUserid(userid).get(0).getUrls());
        return articleinfoMapper.selectArticleCollectionByUserid(userid);
    }

    @Override
    public List<Articleinfo> selectArticlePublishByUserid(int userid) {
        return articleinfoMapper.selectArticlePublishByUserid(userid);
    }

    @Override
    public List<Articleinfo> selectArticleHistoryByUserid(int userid) {
        return articleinfoMapper.selectArticleHistoryByUserid(userid);
    }

    @Override
    public List<Articleinfo> getHotArticles() {
        return articleinfoMapper.getHotArticles();
    }

    @Override
    public List<Map> searchArticles(String dish, String cuisine) {
        System.out.println(dish);
        System.out.println(cuisine);
        for(Map o: articleinfoMapper.searchArticles(dish, cuisine)){
            for(Object key:o.keySet()){
                System.out.println(key + "  " + o.get(key));
            }
        }
        return articleinfoMapper.searchArticles(dish, cuisine);
//        ArrayList list = new ArrayList();
//        HashMap<String, Object> hashMap = new HashMap();
//        hashMap.put("key", "value");
//        list.add(hashMap);
//        return list;
    }

    @Override
    public List<Articleinfo> selectTen() {
        return articleinfoMapper.selectTen();
    }


}
