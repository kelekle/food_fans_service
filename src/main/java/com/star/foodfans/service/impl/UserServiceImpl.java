package com.star.foodfans.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.star.foodfans.dao.ArticleinfoDao;
import com.star.foodfans.dao.VideoinfoDao;
import com.star.foodfans.entity.Articleinfo;
import com.star.foodfans.entity.Model;
import com.star.foodfans.entity.Videoinfo;
import com.star.foodfans.service.MailService;
import com.star.foodfans.service.UserService;
import com.star.foodfans.service.impl.MailServiceImpl;
import com.star.foodfans.util.RedisUtils;
import com.star.foodfans.util.Utils;
import com.star.foodfans.dao.UserDao;
import com.star.foodfans.entity.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.star.foodfans.util.Utils.resource_prefix;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Value("${users.location}")
    private String filePath;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleinfoDao articleinfoDao;

    @Autowired
    private VideoinfoDao videoinfoDao;

    @Autowired
    private MailService mailService;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public String login(HttpServletResponse response, String email, String password) {
        Userinfo userinfo = userDao.selectByEmail(email);
//        Map<String, String> map = new HashMap<>();
        JSONObject map = new JSONObject();
        if(userinfo == null){
            map.put("result", "该用户不存在");
            return JSONObject.toJSONString(map);
        }
        if(userinfo.getPassword().equals(password)){
            String token = Utils.createJWT(userinfo.getUserid());
            response.setHeader("token", token);
            map.put("token", token);
            map.put("result", "success");
            userinfo.setHeadpicture(resource_prefix + userinfo.getHeadpicture());
            map.put("user", userinfo);
        }else {
            map.put("result", "密码不正确");
        }
        return map.toJSONString();
    }

    @Override
    public String register(Userinfo userinfo, String code) {
        Map<String, String> map = new HashMap<>();
        if(userDao.selectByEmail(userinfo.getEmail()) != null){
            map.put("result", "该邮箱已注册！");
            return JSONObject.toJSONString(map);
        }
        if(!redisUtils.has(userinfo.getEmail())) {
            map.put("result", "未发送验证码！");
            return JSONObject.toJSONString(map);
        }
        if(!redisUtils.get(userinfo.getEmail()).equals(code)){
            map.put("result", "验证码错误！");
            return JSONObject.toJSONString(map);
        }
        userinfo.setHeadpicture("user/1/av003.jpg");
        int uid = userDao.insert(userinfo);
//        System.out.println("uid: " + userinfo.getUserid());
        if(uid <= 0){
            map.put("result", "数据库异常，插入失败，请重新注册!");
        }else {
            File file = new File((filePath + "/" + userinfo.getUserid()));
            if(!file.exists()){
                file.mkdir();
            }
            map.put("result", "success");
        }
        return JSONObject.toJSONString(map);
    }

    @Override
    public String findPassword(String email, String code, String newPassword) {
        Map<String, String> map = new HashMap<>();
        if(userDao.selectByEmail(email) == null){
            map.put("result", "该邮箱尚未注册！");
            return JSONObject.toJSONString(map);
        }
        if(!redisUtils.get(email).equals(code)){
            map.put("result", "验证码错误！");
            return JSONObject.toJSONString(map);
        }
        changePasswordByEmail(email, newPassword);
        return null;
    }

    @Override
    public String updateUserInfo(HttpServletRequest request, String username, String phone) {
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
        Userinfo userinfo = userDao.selectByPrimaryKey(uid);
        userinfo.setUsername(username);
        userinfo.setPhone(phone);
        JSONObject jsonObject = new JSONObject();
        if(userDao.updateByPrimaryKey(userinfo) > 0){
            jsonObject.put("result", "success");
        }else {
            jsonObject.put("result", "fail");
        }
        return jsonObject.toJSONString();
    }

    @Override
    public String updateUserHeadPicture(HttpServletRequest request, MultipartFile headPicture) {
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
        JSONObject jsonObject = new JSONObject();
        Userinfo userinfo;
        if (!headPicture.isEmpty()) {
//            try {
                /*
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
                 * d:/files大家是否能实现呢？ 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
                 * 3、文件格式; 4、文件大小的限制;
                 */
//                BufferedOutputStream out = new BufferedOutputStream(
//                        new FileOutputStream(new File(filePath + "/" + uid + "/" +
//                                headPicture.getOriginalFilename())));
//                out.write(headPicture.getBytes());
//                out.flush();
//                out.close();
//                userDao.updateUserInfo(uid, username, "user/" + uid + "/" + headPicture.getOriginalFilename(), phone);
                userinfo = userDao.selectByPrimaryKey(uid);
                userinfo.setHeadpicture("user/" + uid + "/" + headPicture.getOriginalFilename());
                userDao.updateByPrimaryKey(userinfo);
                userinfo.setHeadpicture(resource_prefix + userinfo.getHeadpicture());
                jsonObject.put("result", "success");
                jsonObject.put("data", userinfo);
//            } catch (IOException e) {
//                e.printStackTrace();
//                jsonObject.put("result", "fail");
//                jsonObject.put("msg", "上传异常");
//                return jsonObject.toJSONString();
//            }
            return jsonObject.toJSONString();
        } else {
            jsonObject.put("result", "fail");
            jsonObject.put("msg", "文件为空");
            return jsonObject.toJSONString();
        }
    }

    @Override
    public String changePassword(HttpServletRequest request, String oldValue, String newVal) {
        System.out.println(request.getHeader("token"));
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
        Map<String, String> map = new HashMap<>();
        String password = userDao.selectPasswordByUserid(uid);
        if("".equals(password)){
            map.put("result", "该用户不存在！");
            return JSONObject.toJSONString(map);
        }
        if(password.equals(oldValue)){
            if(userDao.changePassword(uid, newVal) > 0){
                map.put("result", "success");
            }else {
                map.put("result", "数据库异常，更新失败，请重新修改!");
            }
        }else {
            map.put("result", "密码错误");
        }
        return JSONObject.toJSONString(map);
    }

    @Override
    public String changePasswordByEmail(String email, String newVal) {
        Map<String, String> map = new HashMap<>();
        if(userDao.changePasswordByEmail(email, newVal) > 0){
            map.put("result", "success");
        }else {
            map.put("result", "数据库异常，更新失败，请重新修改!");
        }
        return JSONObject.toJSONString(map);
    }

    @Override
    public String getPersonalInfo(HttpServletRequest request){
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
        Userinfo userinfo = userDao.selectByPrimaryKey(uid);
        userinfo.setHeadpicture(resource_prefix + userinfo.getHeadpicture());
        return JSONObject.toJSONString(userinfo);
    }

    @Override
    public String getCollectionInfo(HttpServletRequest request){
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
//        List<Videoinfo> videoList = videoinfoDao.selectVideoCollectionByUserid(uid);
        List<Articleinfo> articleList = articleinfoDao.selectArticleCollectionByUserid(uid);
//        return getString(videoList, articleList);
        for(Articleinfo articleinfo:articleList){
            articleinfo.setUrls(resource_prefix + articleinfo.getUrls());
        }
        return JSONObject.toJSONString(articleList);
    }

    @Override
    public String getPublishInfo(HttpServletRequest request){
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
        List<Videoinfo> videoList = videoinfoDao.selectVideoPublishByUserid(uid);
        List<Articleinfo> articleList = articleinfoDao.selectArticlePublishByUserid(uid);
        return getString(videoList, articleList);
    }

    @Override
    public String getHistoryInfo(HttpServletRequest request){
        int uid = JWT.decode(request.getHeader("token")).getClaim("uid").asInt();
//        List<Videoinfo> videoList = videoinfoDao.selectVideoHistoryByUserid(uid);
        List<Articleinfo> articleList = articleinfoDao.selectArticleHistoryByUserid(uid);
//        return getString(videoList, articleList);
        System.out.println("history  " + articleList.size());
        for(Articleinfo articleinfo:articleList){
            articleinfo.setUrls(resource_prefix + articleinfo.getUrls());
        }
        return JSONObject.toJSONString(articleList);
    }

    private String getString(List<Videoinfo> videoinfoList, List<Articleinfo> articleinfoList) {
        for (Videoinfo value : videoinfoList) {
            value.setUrls(resource_prefix + value.getUrls());
            value.setPictureurl(resource_prefix + value.getPictureurl());
        }
        for (Articleinfo value : articleinfoList) {
            value.setUrls(resource_prefix + value.getUrls());
        }
        List<Model> modelList = new ArrayList<>();
        for (Videoinfo videoinfo : videoinfoList) {
            modelList.add(new Model("video", videoinfo));
        }
        for (Articleinfo articleinfo : articleinfoList) {
            modelList.add(new Model("article", articleinfo));
        }
        return JSONObject.toJSONString(modelList);
    }

    @Override
    public String getEmailCode(String email){
        Map<String, String> map = new HashMap<>();
        String code = Utils.generateEmailCode();
        mailService.sendSimpleMail(email, "验证码", code);
        System.out.println(email);
        redisUtils.set(email, code);
        System.out.println(code);
        map.put("result", "验证码已发送");
        return JSONObject.toJSONString(map);
    }


}
