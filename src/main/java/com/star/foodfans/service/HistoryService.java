package com.star.foodfans.service;

import javax.servlet.http.HttpServletRequest;

public interface HistoryService {

    String addCollection(HttpServletRequest request, String contentId);
    String addHistory(HttpServletRequest request, String contentId);
    String removeCollection(HttpServletRequest request, String contentId);

}
