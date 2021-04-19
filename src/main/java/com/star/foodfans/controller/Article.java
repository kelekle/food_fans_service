package com.star.foodfans.controller;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.star.foodfans.dao.*;
import com.star.foodfans.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.star.foodfans.util.Utils.resource_prefix;

@RestController

public class Article {

    @Value("${articles.location}")
    private String articleFilePath;

    @Value("${videos.location}")
    private String videoFilePath;

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
    @Autowired
    HistoryDao historyDao;
    @Autowired
    FriendrangeDao friendrangeDao;
    @Autowired
    RewardDao rewardDao;
    @Autowired
    CommentinfoDao commentioninfoDao;

    @RequestMapping(method = RequestMethod.POST, path = "/queryArticle")
    public   String  query(HttpServletRequest httpServletRequest,@RequestParam int articleid){
        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();

        Articleinfo articleinfo=new Articleinfo();
        Userinfo userinfo=new Userinfo();
        articleinfo=articleinfoDao.selectByPrimaryKey(articleid);
        userinfo=userDao.selectByPrimaryKey(articleinfo.getUserid());
        articleinfo.setReadnum(articleinfo.getReadnum()+1);
        articleinfoDao.updateByPrimaryKey(articleinfo);
        History history=new History();
        history.setContentid("A"+articleid);
        history.setUserid(userid);
        long  timeNew =  System.currentTimeMillis(); // 13位数的时间戳
        history.setCreatestamp(timeNew);
        history.setIscollected(0);

        String commentid="A"+articleid;
        List<Commentinfo> commentinfoList=new ArrayList<>();
        commentinfoList=commentioninfoDao.selectByContentid(commentid);

        List<Userinfo> userinfoList=new ArrayList<>();
        for(int i=0;i<commentinfoList.size();i++){
            Userinfo commentuserinfo=new Userinfo();
            commentuserinfo=userDao.selectByPrimaryKey(commentinfoList.get(i).getUserid());
            userinfoList.add(commentuserinfo);
        }
        articleinfo.setUrls(resource_prefix + articleinfo.getUrls());
        JSONObject mapname = new JSONObject();
        mapname.put("author",userinfo);
        mapname.put("article",articleinfo);
        JSONArray commentList = new JSONArray();
        for(int i=0;i<commentinfoList.size();i++){
            Userinfo userinfo1 = userinfoList.get(i);
            if(userinfo1 == null)
                continue;
            JSONObject map = new JSONObject();
            map.put("comment", commentinfoList.get(i));
            userinfo1.setHeadpicture(resource_prefix + userinfo1.getHeadpicture());
            map.put("username", userinfo1);
            commentList.add(map);
        }
        mapname.put("commentList", commentList);
//        mapname.put("commentUserinfo",userinfoList);
        History record  = historyDao.selectByPrimaryKey(userid, "A" + articleid);
        if(record != null){
            record.setCreatestamp(System.currentTimeMillis());
            historyDao.updateByPrimaryKey(history);
        }else{
            historyDao.insert(history);
        }
        String str = JSONObject.toJSONString(mapname);
        return str;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/queryVideo")
    public   String  queryViedo(HttpServletRequest httpServletRequest,@RequestParam int videoid){
        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();

        Videoinfo videoinfo=new Videoinfo();
        Userinfo userinfo=new Userinfo();
        videoinfo=videoinfoDao.selectByPrimaryKey(videoid);
        userinfo=userDao.selectByPrimaryKey(videoinfo.getUserid());
        videoinfo.setReadnum(videoinfo.getReadnum()+1);
        videoinfoDao.updateByPrimaryKey(videoinfo);
        History history=new History();
        history.setContentid("V"+videoid);
        long timeNew = System.currentTimeMillis(); // 13位数的时间戳
        history.setCreatestamp(timeNew);
        history.setUserid(userid);
        history.setIscollected(0);

        String commentid="V"+videoid;
        List<Commentinfo> commentinfoList=new ArrayList<>();
        commentinfoList=commentioninfoDao.selectByContentid(commentid);
        List<Userinfo> userinfoList=new ArrayList<>();
        for(int i=0;i<commentinfoList.size();i++){
            Userinfo commentuserinfo=new Userinfo();
            commentuserinfo=userDao.selectByPrimaryKey(commentinfoList.get(i).getUserid());
            userinfoList.add(commentuserinfo);
        }
        videoinfo.setUrls(resource_prefix + videoinfo.getUrls());
        videoinfo.setPictureurl(resource_prefix + videoinfo.getPictureurl());
        Map mapname=new HashMap();
        mapname.put("author",userinfo);
        mapname.put("video",videoinfo);
        JSONArray commentList = new JSONArray();
        for(int i=0;i<commentinfoList.size();i++){
            JSONObject map = new JSONObject();
            map.put("comment", commentinfoList.get(i));
            map.put("username", userinfoList.get(i));
            commentList.add(map);
        }
        mapname.put("commentList", commentList);
        History record  = historyDao.selectByPrimaryKey(userid, "V" + videoid);
        if(record != null){
            record.setCreatestamp(System.currentTimeMillis());
            historyDao.updateByPrimaryKey(history);
        }else{
            historyDao.insert(history);
        }
        String str = JSONObject.toJSONString(mapname);
        return str;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/followAuthor")
    public   String  followAuthor(HttpServletRequest httpServletRequest,@RequestParam int articleid){
        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();
        JSONObject jsonObject = new JSONObject();
        Articleinfo articleinfo=new Articleinfo();
        articleinfo=articleinfoDao.selectByPrimaryKey(articleid);
        int authorid=articleinfo.getUserid();
        if(friendrangeDao.selectByPrimaryKey(userid, authorid) != null){
            jsonObject.put("result", "您已关注该作者！");
        }else {
            jsonObject.put("result", "success");
            Friendrange friendrange = new Friendrange();
            long timeNew = System.currentTimeMillis(); // 13位数的时间戳
            friendrange.setCreattimestamp(timeNew);
            friendrange.setFriendid(authorid);
            friendrange.setUserid(userid);
            friendrange.setStatus(1);
            friendrangeDao.insert(friendrange);
            articleinfo.setVisitnum(articleinfo.getVisitnum() + 1);
            articleinfoDao.updateByPrimaryKey(articleinfo);
        }
        return jsonObject.toJSONString();
    }


    @RequestMapping(method = RequestMethod.POST, path = "/followAuthorVideo")
    public   String  followAuthorViedo(HttpServletRequest httpServletRequest,@RequestParam int videoid){
        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();
        JSONObject jsonObject = new JSONObject();
        Videoinfo videoinfo=new Videoinfo();

        videoinfo=videoinfoDao.selectByPrimaryKey(videoid);
        int authorid=videoinfo.getUserid();
        if(friendrangeDao.selectByPrimaryKey(userid, authorid) != null){
            jsonObject.put("result", "您已关注该作者！");
        }else{
            jsonObject.put("result", "success");
            Friendrange friendrange=new Friendrange();
            long  timeNew =  System.currentTimeMillis(); // 13位数的时间戳
            friendrange.setCreattimestamp(timeNew);
            friendrange.setFriendid(authorid);
            friendrange.setUserid(userid);
            friendrange.setStatus(1);
            friendrangeDao.insert(friendrange);
        }
        videoinfo.setVisitnum(videoinfo.getVisitnum()+1);
        videoinfoDao.updateByPrimaryKey(videoinfo);
        return jsonObject.toJSONString();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/praiseArticle")
    public String  praiseArticle(HttpServletRequest httpServletRequest,@RequestParam int articleid){
//        int userid = JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();
        Articleinfo articleinfo = new Articleinfo();
        articleinfo = articleinfoDao.selectByPrimaryKey(articleid);
        articleinfo.setPraisenum(articleinfo.getPraisenum() + 1);
        articleinfoDao.updateByPrimaryKey(articleinfo);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/collectArticle")
    public String collectArticle(HttpServletRequest httpServletRequest,@RequestParam int articleid){
        int userid = JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();
//        Articleinfo articleinfo = new Articleinfo();
//        articleinfo = articleinfoDao.selectByPrimaryKey(articleid);
//        articleinfo.setPraisenum(articleinfo.getPraisenum() + 1);
//        articleinfoDao.updateByPrimaryKey(articleinfo);
        History record  = historyDao.selectByPrimaryKey(userid, "A" + articleid);
        if(record == null){
            History history=new History();
            history.setContentid("A"+articleid);
            history.setUserid(userid);
            long  timeNew =  System.currentTimeMillis(); // 13位数的时间戳
            history.setCreatestamp(timeNew);
            history.setIscollected(0);
            historyDao.insert(history);
        }
        History history1 = historyDao.selectByPrimaryKey(userid, String.valueOf(articleid));
        history1.setIscollected(1);
        historyDao.updateByPrimaryKey(history1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "success");
        return jsonObject.toJSONString();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/cancelCollectArticle")
    public String cancelCollectArticle(HttpServletRequest httpServletRequest,@RequestParam int articleid){
        int userid = JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();
//        Articleinfo articleinfo = new Articleinfo();
//        articleinfo = articleinfoDao.selectByPrimaryKey(articleid);
//        articleinfo.setPraisenum(articleinfo.getPraisenum() + 1);
//        articleinfoDao.updateByPrimaryKey(articleinfo);
        History history = historyDao.selectByPrimaryKey(userid, "A" + String.valueOf(articleid));
        history.setIscollected(0);
        historyDao.updateByPrimaryKey(history);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "success");
        System.out.println("6666666" +jsonObject.get("result"));
        return jsonObject.toJSONString();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/isCollectArticle")
    public String isCollectArticle(HttpServletRequest httpServletRequest,@RequestParam int articleid){
        int userid = JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();
//        Articleinfo articleinfo = new Articleinfo();
//        articleinfo = articleinfoDao.selectByPrimaryKey(articleid);
//        articleinfo.setPraisenum(articleinfo.getPraisenum() + 1);
//        articleinfoDao.updateByPrimaryKey(articleinfo);
        History record  = historyDao.selectByPrimaryKey(userid, "A" + articleid);
        if(record == null){
            History history=new History();
            history.setContentid("A"+articleid);
            history.setUserid(userid);
            long  timeNew =  System.currentTimeMillis(); // 13位数的时间戳
            history.setCreatestamp(timeNew);
            history.setIscollected(0);
            historyDao.insert(history);
        }
        History history = historyDao.selectByPrimaryKey(userid, "A" + String.valueOf(articleid));
        JSONObject jsonObject = new JSONObject();
        if(history.getIscollected() != 0){
            jsonObject.put("result", "true");
        }else {
            jsonObject.put("result", "false");
        }
        return jsonObject.toJSONString();
    }


    @RequestMapping(method = RequestMethod.POST, path = "/praiseVideo")
    public String  praiseViedo(HttpServletRequest httpServletRequest,@RequestParam int videoid){
//        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();
        Videoinfo videoinfo=new Videoinfo();
        videoinfo=videoinfoDao.selectByPrimaryKey(videoid);
        videoinfo.setPraisenum(videoinfo.getPraisenum()+1);
        videoinfoDao.updateByPrimaryKey(videoinfo);
        String str = JSONObject.toJSONString(videoinfo);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/dashangArticle")
    public   String  dashangArticle(HttpServletRequest httpServletRequest,@RequestParam int articleid,@RequestParam int size){
        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();

        Articleinfo articleinfo=new Articleinfo();
        articleinfo=articleinfoDao.selectByPrimaryKey(articleid);
        int articleuserid=articleinfo.getUserid();
        System.out.println("id: " + articleid + "   size: " + size);
        Userinfo userinfo=new Userinfo();
        userinfo=userDao.selectByPrimaryKey(userid);
        System.out.println(userinfo.getEmail());
        if(userinfo.getSocre()<size){
            return "余额不足";
        }
        userinfo.setSocre(userinfo.getSocre() - size);
        userDao.updateByPrimaryKey(userinfo);

        Userinfo auserinfo=new Userinfo();
        auserinfo= userDao.selectByPrimaryKey(articleinfo.getUserid());
        auserinfo.setSocre(auserinfo.getSocre()+size);
        userDao.updateByPrimaryKey(auserinfo);

        Reward reward=new Reward();
        reward.setSender(userid);
        reward.setReceiver(articleuserid);
        reward.setSize(size);
        long  timeNew =  System.currentTimeMillis(); // 13位数的时间戳
        reward.setTimestamp(timeNew);
        reward.setDescribe("这是一次打赏");
        rewardDao.insert(reward);
        return size+"";
    }


    @RequestMapping(method = RequestMethod.POST, path = "/dashangViedo")
    public   String  dashangViedo(HttpServletRequest httpServletRequest,@RequestParam int viedoid,@RequestParam int size){
        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();

        Videoinfo videoinfo=new Videoinfo();
        videoinfo=videoinfoDao.selectByPrimaryKey(viedoid);
        int viedouserid=videoinfo.getUserid();

        Userinfo userinfo=new Userinfo();
        userinfo=userDao.selectByPrimaryKey(userid);
        if(userinfo.getSocre()<size){
            return "余额不足";
        }
        userinfo.setSocre(userinfo.getSocre()-size);
        userDao.updateByPrimaryKey(userinfo);

        Userinfo vuserinfo=new Userinfo();
        vuserinfo= userDao.selectByPrimaryKey(videoinfo.getUserid());
        vuserinfo.setSocre(vuserinfo.getSocre()+size);
        userDao.updateByPrimaryKey(vuserinfo);

        Reward reward=new Reward();
        reward.setSender(userid);
        reward.setReceiver(viedouserid);
        reward.setSize(size);
        long  timeNew =  System.currentTimeMillis(); // 13位数的时间戳
        reward.setTimestamp(timeNew);
        reward.setDescribe("这是一次打赏");
        rewardDao.insert(reward);
        return size+"";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/putArticle")
    public   String  putArticle(HttpServletRequest httpServletRequest,@RequestParam String title,
                                @RequestParam MultipartFile picture,@RequestParam String tag,@RequestParam String content){

        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();
        JSONObject jsonObject = new JSONObject();
        Articleinfo articleinfo=new Articleinfo();
        articleinfo.setVisitnum(0);
        articleinfo.setReadnum(0);
        articleinfo.setPraisenum(0);
        articleinfo.setAvgscore(0);
        articleinfo.setCommentnum(0);
        long  timeNew =  System.currentTimeMillis(); // 13位数的时间戳
        articleinfo.setCreatstamp(timeNew);
        articleinfo.setIstop(0);
        articleinfo.setTitle(title);
        articleinfo.setTag(tag);
        articleinfo.setContent(content);
        articleinfo.setUrls("");
        articleinfo.setUserid(userid);
        int i = articleinfoDao.insert(articleinfo);
        File file = new File((articleFilePath + "/" + i));
        if(!file.exists()){
            file.mkdir();
        }
        if (!picture.isEmpty()) {
//            try {
//                BufferedOutputStream out = new BufferedOutputStream(
//                        new FileOutputStream(new File(articleFilePath + "/" + i + "/" +
//                                picture.getOriginalFilename())));
//                out.write(picture.getBytes());
//                out.flush();
//                out.close();
//                userDao.updateUserInfo(uid, username, "user/" + uid + "/" + headPicture.getOriginalFilename(), phone);
//                articleinfo = userDao.selectByPrimaryKey(i);
                articleinfo.setUrls("article/" + i + "/" + picture.getOriginalFilename());
                articleinfoDao.updateByPrimaryKey(articleinfo);
//            } catch (IOException e) {
//                e.printStackTrace();
//                jsonObject.put("result", "fail");
//                jsonObject.put("msg", "上传异常");
//                return jsonObject.toJSONString();
//            }
            jsonObject.put("result", "success");
            jsonObject.put("data", articleinfo);
            return jsonObject.toJSONString();
        } else {
            jsonObject.put("result", "fail");
            jsonObject.put("msg", "文件为空");
            return jsonObject.toJSONString();
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/putViedo")
    public   String  putViedo(HttpServletRequest httpServletRequest, @RequestParam String title, @RequestParam String tag, MultipartFile cover, @RequestParam MultipartFile video){
        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();
        JSONObject jsonObject = new JSONObject();
        Videoinfo articleinfo=new Videoinfo();
        articleinfo.setVisitnum(0);
        articleinfo.setReadnum(0);
        articleinfo.setPraisenum(0);
        articleinfo.setAvgscore((float) 0);
        articleinfo.setCommentnum(0);
        long  timeNew =  System.currentTimeMillis(); // 13位数的时间戳
        articleinfo.setCreatstamp(timeNew);
        articleinfo.setIstop(0);
        articleinfo.setTitle(title);
        articleinfo.setTag(tag);
        articleinfo.setPictureurl("");
        articleinfo.setUrls("");
        articleinfo.setUserid(userid);
        int i = videoinfoDao.insert(articleinfo);
        File file = new File((videoFilePath + "/" + i));
        if(!file.exists()){
            file.mkdir();
        }
        if (!cover.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(videoFilePath + "/" + i + "/" +
                                cover.getOriginalFilename())));
                out.write(cover.getBytes());
                out.flush();
                out.close();
                articleinfo.setPictureurl("video/" + i + "/" + cover.getOriginalFilename());
                jsonObject.put("result_cover", "success");
                jsonObject.put("data_cover", articleinfo);
            } catch (IOException e) {
                e.printStackTrace();
                jsonObject.put("result_cover", "fail");
                jsonObject.put("msg_cover", "上传异常");
            }
        } else {
            jsonObject.put("result_cover", "fail");
            jsonObject.put("msg_cover", "文件为空");
        }
        if (!video.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(videoFilePath + "/" + i + "/" +
                                video.getOriginalFilename())));
                out.write(video.getBytes());
                out.flush();
                out.close();
                articleinfo.setUrls("video/" + i + "/" + video.getOriginalFilename());
                jsonObject.put("result_video", "success");
                jsonObject.put("data_video", articleinfo);
            } catch (IOException e) {
                e.printStackTrace();
                jsonObject.put("result_video", "fail");
                jsonObject.put("msg_video", "上传异常");
            }
        } else {
            jsonObject.put("result_video", "fail");
            jsonObject.put("msg_video", "文件为空");
        }
        if(videoinfoDao.updateByPrimaryKey(articleinfo) > 0){
            jsonObject.put("result", "success");
        }else {
            jsonObject.put("result", "fail");
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/commentArticle")
    public   String  commentArticle(HttpServletRequest httpServletRequest,@RequestParam int score,
                                    @RequestParam String url,@RequestParam String content,@RequestParam int articleid){
        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();

        Commentinfo commentinfo=new Commentinfo();
        commentinfo.setContent(content);
        commentinfo.setUrl(url);
        long  timeNew =  System.currentTimeMillis(); // 13位数的时间戳
        commentinfo.setCreatstamp(timeNew);
        commentinfo.setContentid("A"+articleid);
        commentinfo.setUserid(userid);
        commentinfo.setScore(score);
        commentinfo.setStatus(1);
        commentioninfoDao.insert(commentinfo);

        Articleinfo articleinfo;
        articleinfo=articleinfoDao.selectByPrimaryKey(articleid);
        articleinfo.setAvgscore((articleinfo.getAvgscore()*articleinfo.getCommentnum()+commentinfo.getScore())/(articleinfo.getCommentnum()+1));
        articleinfo.setCommentnum(articleinfo.getCommentnum()+1);
        articleinfoDao.updateByPrimaryKey(articleinfo);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "success");
        return jsonObject.toJSONString();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/commentViedo")
    public   String  commentViedo(HttpServletRequest httpServletRequest,@RequestParam int score,
                                    @RequestParam String url,@RequestParam String content,@RequestParam int viedoid){

        int userid=JWT.decode(httpServletRequest.getHeader("token")).getClaim("uid").asInt();

        Commentinfo commentinfo=new Commentinfo();
        commentinfo.setContent(content);
        commentinfo.setUrl(url);
        long  timeNew =  System.currentTimeMillis(); // 13位数的时间戳
        commentinfo.setCreatstamp(timeNew);
        commentinfo.setContentid("V"+viedoid);
        commentinfo.setUserid(userid);
        commentinfo.setScore(score);
        commentinfo.setStatus(1);
        commentioninfoDao.insert(commentinfo);

        Videoinfo videoinfo;
        videoinfo=videoinfoDao.selectByPrimaryKey(viedoid);
        videoinfo.setAvgscore((videoinfo.getAvgscore()*videoinfo.getCommentnum()+commentinfo.getScore())/(videoinfo.getCommentnum()+1));
        videoinfo.setCommentnum(videoinfo.getCommentnum()+1);
        videoinfoDao.updateByPrimaryKey(videoinfo);
        return "ok";
    }

}
