package com.star.foodfans.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.star.foodfans.dao.HistoryDao;
import com.star.foodfans.entity.History;
import com.star.foodfans.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service("HistoryService")
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryDao historyDao;

    @Override
    public String addCollection(HttpServletRequest request, String contentId) {
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
        History record = historyDao.selectByPrimaryKey(uid, contentId);
        JSONObject object = new JSONObject();
        if(record == null){
            History history = new History();
            history.setContentid(contentId);
            history.setIscollected(1);
            history.setUserid(uid);
            history.setCreatestamp(System.currentTimeMillis());
            if(historyDao.insert(history) > 0){
                object.put("result", "success");
            }else{
                object.put("result", "fail");
            }
        }else {
            record.setIscollected(1);
            if(historyDao.updateByPrimaryKey(record) > 0){
                object.put("result", "success");
            }else{
                object.put("result", "fail");
            }
        }
        return object.toJSONString();
    }

    @Override
    public String addHistory(HttpServletRequest request, String contentId) {
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
        History record = historyDao.selectByPrimaryKey(uid, contentId);
        if(record != null){
            record.setCreatestamp(System.currentTimeMillis());
            historyDao.updateByPrimaryKey(record);
        }else {
            History history = new History();
            history.setContentid(contentId);
            history.setIscollected(0);
            history.setUserid(uid);
            history.setCreatestamp(System.currentTimeMillis());
            historyDao.insert(history);
        }
        JSONObject object = new JSONObject();
        object.put("result", "success");
        return object.toJSONString();
    }

    @Override
    public String removeCollection(HttpServletRequest request, String contentId) {
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
        History record = historyDao.selectByPrimaryKey(uid, contentId);
        JSONObject object = new JSONObject();
        if(record != null){
            record.setIscollected(0);
            historyDao.updateByPrimaryKey(record);
        }else {
            object.put("result", "fail");
        }
        historyDao.deleteByPrimaryKey(uid, contentId);
        object.put("result", "success");
        return object.toJSONString();
    }

}
