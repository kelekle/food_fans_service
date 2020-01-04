package com.star.foodfans.service;

import com.star.foodfans.entity.Userinfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    String login(HttpServletResponse response, String email, String password);

//    String register(String username, String email, String password);

    String register(Userinfo userinfo, String code);

    String findPassword(String email, String code, String newPassword);

    String updateUserInfo(HttpServletRequest request, String username, String phone);

    String changePassword(HttpServletRequest request, String oldValue, String newVal);

    String changePasswordByEmail(String email, String newVal);

    String updateUserHeadPicture(HttpServletRequest request, MultipartFile headPicture);

}
