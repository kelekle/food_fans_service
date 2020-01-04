package com.star.foodfans.service;

import com.auth0.jwt.JWT;
import com.star.foodfans.dao.ArticleinfoDao;
import com.star.foodfans.dao.ArticleinfoMapper;
import com.star.foodfans.entity.Articleinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

}
