package com.star.foodfans.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ArticleService {

    List<Map> searchArticles(String dish, String cuisine);

    boolean publishArticle(String dish, String cuisine, String content);

    Map queryArticle(int id);

    boolean praiseArticle(int id);

    boolean commentArticle(int id, int score, String content, Date date);

}
