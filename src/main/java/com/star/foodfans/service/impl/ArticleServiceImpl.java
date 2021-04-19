package com.star.foodfans.service.impl;

import com.star.foodfans.dao.ArticleinfoDao;
import com.star.foodfans.entity.Articleinfo;
import com.star.foodfans.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.star.foodfans.util.Utils.resource_prefix;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleinfoDao articleinfoDao;

    @Override
    public List<Map> searchArticles(String dish, String cuisine) {
        if("".equals(dish)){
            dish = "###@@@***";
        }else {
            dish = "%" + dish + "%";
        }
        if("".equals(cuisine)){
            cuisine = "###@@@***";
        }else {
            cuisine = "%" + cuisine + "%";
        }
        List<Map> articleinfos = articleinfoDao.searchArticles(dish, cuisine);
        for(Map map:articleinfos){
            Articleinfo articleinfo = new Articleinfo();
            String url = resource_prefix + map.get("urls");
            map.put("urls", url);
        }
        return articleinfos;
    }

    @Override
    public boolean publishArticle(String dish, String cuisine, String content) {
        return false;
    }

    @Override
    public Map queryArticle(int id) {
        return null;
    }

    @Override
    public boolean praiseArticle(int id) {
        return false;
    }

    @Override
    public boolean commentArticle(int id, int score, String content, Date date) {
        return false;
    }

}
