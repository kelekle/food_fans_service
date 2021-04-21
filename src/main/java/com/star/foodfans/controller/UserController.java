package com.star.foodfans.controller;

import com.star.foodfans.entity.Userinfo;
import com.star.foodfans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    @Qualifier(value = "UserService")
    private UserService userService;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(HttpServletResponse response, String email, String password) {
        System.out.println(email + "    " + password);
        return userService.login(response, email, password);
    }

    @GetMapping("/test")
    public String test(HttpServletResponse response){
        System.out.println(userService == null);
        return userService.login(response,"","");
    }
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String register(String username, String password, String email, String code){
//        Userinfo userinfo = new Userinfo();
//        userinfo.setUsername(username);
//        userinfo.setPassword(password);
//        userinfo.setEmail(email);
//        return userService.register(userinfo, code);
//    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String register(String password, String email, String code){
        Userinfo userinfo = new Userinfo();
        userinfo.setPassword(password);
        userinfo.setEmail(email);
        return userService.register(userinfo, code);
    }

    @RequestMapping(value = "/user/find_pwd", method = RequestMethod.POST)
    public String findPassword(String email, String code, String newPassword){
        return userService.findPassword(email, code, newPassword);
    }

    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public String changePassword(HttpServletRequest request, String oldVal, String newVal){
        return userService.changePassword(request, oldVal, newVal);
    }

    @RequestMapping(value = "/update_userHeadPicture", method = RequestMethod.POST)
    public String updateUserHeadPicture(HttpServletRequest request, MultipartFile headPicture){
        return userService.updateUserHeadPicture(request, headPicture);
    }

    @RequestMapping(value = "/update_userinfo", method = RequestMethod.POST)
    public String updateUserinfo(HttpServletRequest request, String username, String phone){
        return userService.updateUserInfo(request, username, phone);
    }

    @RequestMapping(value = "/user/get_code", method = RequestMethod.POST)
    public String getEmailCode(String email){
        return userService.getEmailCode(email);
    }

    @RequestMapping(value = "/get_personal_info", method = RequestMethod.GET)
    public String getPersonalInfo(HttpServletRequest request){
        return userService.getPersonalInfo(request);
    }

    @RequestMapping(value = "/get_personal_collection", method = RequestMethod.GET)
    public String getPersonalCollection(HttpServletRequest request){
        return userService.getCollectionInfo(request);
    }

    @RequestMapping(value = "/get_personal_publish", method = RequestMethod.GET)
    public String getPersonalPublish(HttpServletRequest request){
        return userService.getPublishInfo(request);
    }

    @RequestMapping(value = "/get_personal_history", method = RequestMethod.GET)
    public String getPersonalHistory(HttpServletRequest request){
        return userService.getHistoryInfo(request);
    }


}
