package com.star.foodfans.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.star.foodfans.dao.*;
import com.star.foodfans.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.star.foodfans.util.Utils.resource_prefix;

@RestController
public class User {

    @Autowired
    UserDao userDao;
    @Autowired
    MessageDao messageDao;
    @Autowired
    ArticleinfoDao articleinfoDao;
    @Autowired
    VideoinfoDao videoinfoDao;
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    RecordDao recordDao;

    //基本上都是用到selectbyhot
    @RequestMapping(method = RequestMethod.GET, path = "/home")
    public String home(){
        List<Map> fourArticlelists=new ArrayList();
        List<Articleinfo> articlelists=new ArrayList();
        articlelists= articleinfoDao.selectTen();
        for(int i=5;i<9;i++){
            Map mapname=new HashMap();
            articlelists.get(i).setUrls(resource_prefix + articlelists.get(i).getUrls());
            mapname.put("name",articlelists.get(i));
            Userinfo one=new Userinfo();
            mapname.put("username",userDao.selectByPrimaryKey(articlelists.get(i).getUserid()).getUsername());
            float hot= (float) ((articlelists.get(i).getCommentnum()*0.2+articlelists.get(i).getVisitnum())*articlelists.get(i).getAvgscore());
            mapname.put("hot",hot);
            String tag=articlelists.get(i).getTag();
            String[] tags = tag.split("/");    // 分割成数组
            mapname.put("tags",tags);
            System.out.println("1"+tag);
            fourArticlelists.add(mapname);
        }
        //列出四篇视频
        List<Videoinfo> viedolists=new ArrayList();
        List<Map> fourviedolists=new ArrayList();
        viedolists=videoinfoDao.selectByHot();
        for(int i = 0;i < 4;i++){
            Map mapname=new HashMap();
            viedolists.get(i).setUrls(resource_prefix +viedolists.get(i).getUrls());
            viedolists.get(i).setPictureurl(resource_prefix +viedolists.get(i).getPictureurl());
            mapname.put("name",viedolists.get(i));
            mapname.put("username",userDao.selectByPrimaryKey(viedolists.get(i).getUserid()).getUsername());
            float hot= (float) ((viedolists.get(i).getCommentnum()*0.2+viedolists.get(i).getVisitnum())*viedolists.get(i).getAvgscore());
            mapname.put("hot",hot);
            String tag=viedolists.get(i).getTag();
            String[] tags = tag.split("/");    // 分割成数组
            mapname.put("tags",tags);
            System.out.println("2"+tag);
            fourviedolists.add(mapname);
        }

        //列出四篇商品
        List<Goods> goodslists=new ArrayList();
        List<Map> fourgoodlists=new ArrayList();
        goodslists=goodsDao.selectBytime();
        for(int i=0;i<4;i++){
            Map mapname=new HashMap();
            goodslists.get(i).setPictureurl(resource_prefix + goodslists.get(i).getPictureurl());
            mapname.put("name",goodslists.get(i));
            fourgoodlists.add(mapname);
        }
        Map mapTen=new HashMap();
        mapTen.put("fourArticle",fourArticlelists);
        mapTen.put("fourviedolists",fourviedolists);
        mapTen.put("fourgoodlists",fourgoodlists);
        String str = JSONObject.toJSONString(mapTen);
        return str;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/homehot")
    public String homehot(){

        List<Articleinfo> articlelists=new ArrayList();
        List<Map> fiveArticlelists=new ArrayList();
        Map mapTen=new HashMap();

        articlelists= articleinfoDao.selectTen();
        //展示5篇文章
        for(int i=0;i<5;i++){
            Map mapname=new HashMap();
            articlelists.get(i).setUrls(resource_prefix + articlelists.get(i).getUrls());
            mapname.put("name",articlelists.get(i));
            mapname.put("username",userDao.selectByPrimaryKey(articlelists.get(i).getUserid()).getUsername());
            float hot= (float) ((articlelists.get(i).getCommentnum()*0.2+articlelists.get(i).getVisitnum())*articlelists.get(i).getAvgscore());
            mapname.put("hot",hot);
            String tag=articlelists.get(i).getTag();
            String[] tags = tag.split("/");    // 分割成数组
            mapname.put("tags",tags);
            fiveArticlelists.add(mapname);
        }
        mapTen.put("fiveHot",fiveArticlelists);
        String str = JSONObject.toJSONString(mapTen);
        return str;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/allArticle")
    public String allArticle(){

        List<Articleinfo> articlelists;
        List<Map> fiveArticlelists=new ArrayList();
        Map mapTen=new HashMap();

        articlelists= articleinfoDao.selectTen();
        //展示5篇文章
        for(int i=0;i<articlelists.size();i++){
            Map mapname=new HashMap();
            articlelists.get(i).setUrls(resource_prefix + articlelists.get(i).getUrls());
            mapname.put("name",articlelists.get(i));
            mapname.put("username",userDao.selectByPrimaryKey(articlelists.get(i).getUserid()).getUsername());
            float hot= (float) ((articlelists.get(i).getCommentnum()*0.2+articlelists.get(i).getVisitnum())*articlelists.get(i).getAvgscore());
            mapname.put("hot",hot);
            String tag=articlelists.get(i).getTag();
            String[] tags = tag.split("/");    // 分割成数组
            mapname.put("tags",tags);
            fiveArticlelists.add(mapname);
        }
        mapTen.put("allArticle",fiveArticlelists);
        String str = JSONObject.toJSONString(mapTen);
        return str;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/allViedo")
    public String allViedo(){
        Map mapTen=new HashMap();
        //列出四篇视频
        List<Videoinfo> viedolists;
        List<Map> fourviedolists = new ArrayList();
        viedolists=videoinfoDao.selectByHot();
        for(int i=0;i<viedolists.size();i++){
            Map mapname=new HashMap();
            viedolists.get(i).setUrls(resource_prefix +viedolists.get(i).getUrls());
            viedolists.get(i).setPictureurl(resource_prefix + viedolists.get(i).getPictureurl());
            mapname.put("name",viedolists.get(i));
            mapname.put("username",userDao.selectByPrimaryKey(viedolists.get(i).getUserid()).getUsername());
            float hot= (float) ((viedolists.get(i).getCommentnum()*0.2+viedolists.get(i).getVisitnum())*viedolists.get(i).getAvgscore());
            mapname.put("hot",hot);
            String tag=viedolists.get(i).getTag();
            String[] tags = tag.split("/");    // 分割成数组
            mapname.put("tags",tags);
            fourviedolists.add(mapname);
        }

        mapTen.put("allViedo",fourviedolists);
        String str = JSONObject.toJSONString(mapTen);
        return str;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/allGoods")
    public String allGoods(){
        int type = 1;
        Map mapTen=new HashMap();
        //列出四篇商品
        List<Goods> goodslists=new ArrayList();
        List<Map> fourgoodlists=new ArrayList();
        if(type==1){
            goodslists=goodsDao.selectBytime();
        }else if(type==2){
            goodslists=goodsDao.selectByMoney();
        }else {
            goodslists=goodsDao.selectMoneyasc();
        }
        for(int i=0;i<4;i++){
            Map mapname=new HashMap();
            goodslists.get(i).setPictureurl(resource_prefix + goodslists.get(i).getPictureurl());
            mapname.put("name",goodslists.get(i));
            fourgoodlists.add(mapname);
        }
        mapTen.put("allGoods",fourgoodlists);
        String str = JSONObject.toJSONString(mapTen);
        return str;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/buy")
    public   String  buy(HttpServletRequest httpServletRequest ,@RequestParam int goodid){

        Goods goods=new Goods();
        Userinfo userinfo=new Userinfo();
        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();

        userinfo=userDao.selectByPrimaryKey(userid);
        goods=goodsDao.selectByPrimaryKey(goodid);
        float score = userinfo.getSocre();

        if(goods.getNumber()<1){
            return "商品数量不足";
        }
        goods.setNumber(goods.getNumber()-1);
        goodsDao.updateByPrimaryKey(goods);
        if (userinfo.getSocre() < goods.getPrice()){
            return "余额不足";
        }else {
            userinfo.setSocre(userinfo.getSocre() - (0.95f)*goods.getPrice());
        }
        userDao.updateByPrimaryKey(userinfo);
//        if(goodsDao.redeem(userid, goodid)){
//            return "余额不足";
//        }else{
            long  timeNew =  System.currentTimeMillis(); // 13位数的时间戳
            Record record=new Record();
            record.setUserid(userid);
            record.setGoodId(goodid);
            record.setIsFinish(1);
            record.setTimestamp(timeNew);
            record.setScorespent((int)((0.95f)*goods.getPrice()));
            recordDao.insert(record);
            String str = JSONObject.toJSONString(record);
            return str;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/query")
    public   String  query(HttpServletRequest httpServletRequest){
        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();
        Record record = new Record();
        List<Record> recordList = new ArrayList();
        recordList=recordDao.selectByUserId(userid);
        String str = JSONObject.toJSONString(recordList);
        return str;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/putsale")
    public   String  putsale(HttpServletRequest httpServletRequest, @RequestParam("name") String name,@RequestParam("pictureurl") String pictureurl,
                             @RequestParam("number") int number,@RequestParam("price") int price,@RequestParam("introduction")  String introduction){
        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();
        Goods goods=new Goods();
        goods.setNumber(number);
        goods.setIntroduction(introduction);
        goods.setName(name);
        goods.setPictureurl(pictureurl);
        goods.setPrice(price);
        long  timeNew =  System.currentTimeMillis(); // 13位数的时间戳
        goods.setTimestamp(timeNew);
        goodsDao.insert(goods);
        String str = JSONObject.toJSONString(goods);
        return str;
    }

}
