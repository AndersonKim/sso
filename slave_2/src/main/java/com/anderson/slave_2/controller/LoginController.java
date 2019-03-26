package com.anderson.slave_2.controller;

import com.anderson.slave_2.bo.User;
import com.anderson.slave_2.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserDao userDao;

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("username")==null){
            return "login";
        }else{
            return "success";
        }

    }
    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request){
        HttpSession session = request.getSession();
        User dbUser=userDao.findByUserName(user.getUserName());
        if(dbUser.getPassWord().equals(user.getPassWord())){
            session.setAttribute("username",user.getUserName());
            return "success";
        }else{
            return "fail";
        }
    }
}
