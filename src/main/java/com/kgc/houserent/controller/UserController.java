package com.kgc.houserent.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.houserent.entity.Users;
import com.kgc.houserent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/UsersList")
    @ResponseBody
    public Map<String,Object> selectAllUsers(Integer page,Integer rows){
        page=page==null?1:page;
        PageInfo<Users> PageInfo=userService.selectUsers(page,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",PageInfo.getTotal());
        map.put("rows",PageInfo.getList());
        map.put("pageSize",PageInfo.getPageSize());
        return map;
    }

    @RequestMapping("/checkUname")
    @ResponseBody
    public String checkUname(String userName){
        int i=userService.checkName(userName);
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("/access")
    public String addUser(Users users){
        int i=userService.addUser(users);
        if(i>0)
        return "page/login";
        else return "page/regs";
    }

    @RequestMapping("/login")
    public String checkUname(String name, String password, Model model, HttpSession session){
        Users users=userService.selectUser(name,password);
        if(users==null){
            model.addAttribute("msg","用户名或密码错误");
            return "page/login";
        }
        else{
            //session||cookie保存用户信息
            session.setAttribute("users",users);
            session.setMaxInactiveInterval(600);
            return "redirect:showUserHouse";
        }
    }
}
