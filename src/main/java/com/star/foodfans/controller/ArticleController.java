package com.star.foodfans.controller;

import com.star.foodfans.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {

    @Autowired
    @Qualifier(value = "ArticleService")
    private ArticleService articleService;

    @RequestMapping(value = "/hot_articles", method= RequestMethod.GET)
    public void getHotArticles(){

    }

    @RequestMapping(value = "/articles_by_cuisine", method = RequestMethod.GET)
    public void getArticlesByCuisine(String cuisine){

    }

    @RequestMapping(value = "/search_articles", method = RequestMethod.POST)
    public List<Map> searchArticles(String dish, String cuisine){
        return articleService.searchArticles(dish, cuisine);
    }

}
