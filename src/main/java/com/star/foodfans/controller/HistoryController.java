package com.star.foodfans.controller;

import com.star.foodfans.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @RequestMapping(value = "/add_collection_video", method = RequestMethod.POST)
    public String addCollection(HttpServletRequest request, String contentId){
        return historyService.addCollection(request, "V" + contentId);
    }

    @RequestMapping(value = "/add_history_video", method = RequestMethod.POST)
    public String addHistory(HttpServletRequest request, String contentId){
        return historyService.addCollection(request, "V" + contentId);
    }

    @RequestMapping(value = "/remove_collection_video", method = RequestMethod.POST)
    public String removeCollection(HttpServletRequest request, String contentId){
        return historyService.removeCollection(request, "V" + contentId);
    }

    @RequestMapping(value = "/add_collection_article", method = RequestMethod.POST)
    public String addCollectionArticle(HttpServletRequest request, String contentId){
        return historyService.addCollection(request, "A" + contentId);
    }

    @RequestMapping(value = "/add_history_article", method = RequestMethod.POST)
    public String addHistoryArticle(HttpServletRequest request, String contentId){
        return historyService.addCollection(request, "A" + contentId);
    }

    @RequestMapping(value = "/remove_collection_article", method = RequestMethod.POST)
    public String removeCollectionArticle(HttpServletRequest request, String contentId){
        return historyService.removeCollection(request, "A" + contentId);
    }

}
