package com.star.foodfans.service;

import com.star.foodfans.entity.Articleinfo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ArticleService {

    List<Map> searchArticles(String dish, String cuisine);

}
