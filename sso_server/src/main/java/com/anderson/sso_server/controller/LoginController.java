package com.anderson.sso_server.controller;

import com.alibaba.fastjson.JSONObject;
import com.anderson.sso_server.bo.Token;
import com.anderson.sso_server.bo.User;
import com.anderson.sso_server.dao.TokenDao;
import com.anderson.sso_server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class LoginController {
    @Autowired
    UserDao userDao;
    @Autowired
    TokenDao tokenDao;


    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("user")==null){
            return "login";
        }else{
            return "success";
        }

    }
    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request){
        HttpSession session = request.getSession();
        User dbUser=userDao.findByName(user.getName());
        if(dbUser.getPass().equals(user.getPass())){
            session.setAttribute("user",dbUser);
            return "success";
        }else{
            return "fail";
        }
    }

    @RequestMapping("/toSlave_1")
    public String toSlave_1(HttpServletRequest request){
        //登录状态下才能显示跳转到slave的地址，并附加token的信息，触发业务系统获取用户信息
        if(request.getSession().getAttribute("user")!=null){
            Token newToken=new Token();
            User sessionUser = (User) request.getSession().getAttribute("user");
            newToken.setUserId(sessionUser.getUuid());
            newToken.setToken(UUID.randomUUID().toString().replace("-",""));
            tokenDao.save(newToken);
            return "redirect:http://localhost:8081/signInWithToken/"+newToken.getToken();
        }else{
            return "login";
        }
    }


    @RequestMapping("/ssoAction")
    public String ssoAction(String appId,HttpServletRequest request){
        //用于子系统之间跳转的页面
        //用户若已经登录就跳转到子系统（appId）指定的路由（8081/signInWithToken还是8081/signInWithToken）即可
        //否则先登录，然后跳转到指定的路由
        if(request.getSession().getAttribute("user")!=null){
            Token newToken=new Token();
            User sessionUser = (User) request.getSession().getAttribute("user");
            newToken.setUserId(sessionUser.getUuid());
            newToken.setToken(UUID.randomUUID().toString().replace("-",""));
            tokenDao.save(newToken);
            if(appId.equals("s1")){
                return "redirect:http://localhost:8081/signInWithToken/"+newToken.getToken();
            }else if(appId.equals("s2")){
                return "redirect:http://localhost:8082/signInWithToken/"+newToken.getToken();
            }else{
                return "redirect:http://localhost:8081/signInWithToken/"+newToken.getToken();
            }
        }else{
            return "login";
        }
    }




}
