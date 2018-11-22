package com.assets.controller;
import java.io.IOException;  
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;  
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;

import com.assets.model.Role;
import com.assets.model.User;
import com.assets.service.RoleService;
import com.assets.service.UserService;
import com.assets.tool.Utils;  
@Controller  
@RequestMapping("/system")  
public class SystemController {  
    @Autowired  
    private UserService userService; 
    @RequestMapping("/desktop.do")  
    public String  desktop(HttpSession session,Model model, User user){
	    try {
	    	User record=(User) session.getAttribute("user");
	        model.addAttribute("user", record);    
	        return "/system/desktop";
		} catch (Exception e) {
			return "/system/error";
		}
    }	
    @RequestMapping("/logout.do")  
    public String  logout(HttpSession session,Model model, User user){
	    try {
	    	session.removeAttribute("user");
	    	session.removeAttribute("useId");
	    	session.removeAttribute("userId");
	    	session.removeAttribute("useoId");
	    	session.removeAttribute("power");
	        return "/system/login";
		} catch (Exception e) {
			return "/system/error";
		}
    }	
    @RequestMapping("/error.do")
    public String  error(HttpServletRequest request,HttpSession session,Model model, User user){
    	return "forward:/WEB-INF/page/system/error.jsp";
    }
    
    @RequestMapping("/login.do")
    public String  loginto(HttpServletRequest request,HttpSession session,Model model, User user){
    	if (user.getName()!=null&&user.getPwd()!=null) {
			return login(request, session, model, user);
		}
    	return "/system/login";
    }
    
    
    public String  login(HttpServletRequest request, HttpSession session,Model model, User user){
    	try {
    		  List<User> list=new ArrayList<User>();
              User record=new User();
              record.setName(user.getName());
              list=userService.select(record);
              if(list.size()==0)
            	  model.addAttribute("result","0");//该用户不存在
              else{
                  record.setPwd(user.getPwd());
                  list=userService.select(record);
                  if(list.size()==0)
                      model.addAttribute("result","1");//密码错误
                  else {
                      record=list.get(0);       
                      session.setAttribute("user",record); //将用户保存在session里面
                      session.setAttribute("useId",record.getId());
                      session.setAttribute("userId",record.getRoleid());
                      session.setAttribute("useoId",record.getOid());
                      Role r=userService.getpowerById(record.getRoleid());
                      session.setAttribute("power",r.getJurisdiction());                 
                      model.addAttribute("result","2");//登录成功
                      User up=(User) record.clone();
                      up.setLoginip(Utils.getIp(request));
                      up.setLogintime(Utils.date());
                      userService.update(up);
                      return "/system/index";
                  }
              }
           return "forward:/";	
		} catch (Exception e) {
			return "/system/error";
		}
    }
}  

