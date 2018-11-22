package com.assets.controller;
import java.io.IOException;  
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.PrimitiveType;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;

import com.assets.dao.RoleMapper;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.service.OrganService;
import com.assets.service.RoleService;
import com.assets.service.UserService;
import com.assets.tool.Utils;  
@Controller  
@RequestMapping("/user")  
public class UserController {  
    @Autowired  
    private UserService Service;  
    /**  
     * 获取所有用户列表  
     * @param request  
     * @return  
     */  
    @RequestMapping("/getAll.do")  
    public String getAll(HttpServletRequest request,Model model, HttpSession session){  
    	try {
            List<User> user = Service.findAll((int) session.getAttribute("useId"),(int) session.getAttribute("userId"));  
            model.addAttribute("records", user);  
            request.setAttribute("records", user);
            return "/user/list";	
		} catch (Exception e) {
			return "/system/error";
		}
    }  
    
    /**
    * 根据id查询单个用户  
    * @param id  
    * @param request  
    * @return  
    */ 
    @RequestMapping("/getId.do")  
    public String getId(int id,HttpServletRequest request,Model model){  
    	try {
    		List<User> user = new ArrayList<>();  
    		user.add(Service.findById(id));
    		model.addAttribute("records", user);  
    		return "/user/list";
    	} catch (Exception e) {
    		return "/system/error";
    	}
           
    } 
    

   @RequestMapping("/get.do")  
   public String get(int id,HttpServletRequest request,Model model){ 
	 try {
		    model.addAttribute("user", Service.findById(id));  
			model.addAttribute("organs", Service.findOrganALL());  
			model.addAttribute("roles", Service.findRoleALL());  
			System.err.println(Service.findOrganALL().size());
		    return "/user/edit";  
	} catch (Exception e) {
		return "/system/error";
	}  
   } 
    
    /**  
     *编辑用户  
     * @param user  
     * @param request  
     * @return  
     */  
    @RequestMapping("/update.do")  
    public void update(HttpServletResponse resp,User user,HttpServletRequest request,Model model){  
    	try {
      	  String jsonStr="";
      	  User olduser = Service.findById(user.getId());
      	  user.setMdtm(Utils.date());
      	  user.setLoginip(olduser.getLoginip());
      	  user.setLogintime(olduser.getLogintime());
      	  user.setCrtm(olduser.getCrtm());
      	  if(Service.update(user)){  
                jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	
            }else{ 
          	  jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
            }  
      	  Utils.commend(resp, jsonStr);
		} catch (Exception e) {
			Utils.commend(resp, "{\"code\":\"1001\",\"msg\":\"操作失败\"}");
		}
    	  
    }  
    
    /**  
     * 删除用户  
     * @param id  
     * @param request  
     * @param response  
     */  
    @RequestMapping("/del.do")  
    public void  del(int id,HttpServletResponse resp,HttpServletRequest request){  
    	try {
    		String jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	
            if(Service.delete(id)){  
            	jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	 ;
            }  else{
            	 jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
            }
            Utils.commend(resp, jsonStr);
		} catch (Exception e) {
			Utils.commend(resp, "{\"code\":\"1001\",\"msg\":\"操作失败\"}");
		}
        
    } 
    
    
    /**  
     * 添加用户
     * @param user  
     * @param request  
     * @return  
     */  
    @RequestMapping("/add.do")  
    public String add(User user,Model model){ 
    	try {
    		model.addAttribute("organs", Service.findOrganALL());  
    		model.addAttribute("roles", Service.findRoleALL());  
            return "/user/edit"; 
		} catch (Exception e) {
			return "/system/error";
		}
    	 
    }  
   
    @RequestMapping("/insert.do")  
    public void insert(User user,Model model,HttpServletResponse resp){  
    	String jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	
    	try {
    		 user.setCrtm(Utils.date());
    		 user.setMdtm(Utils.date());
    		 Service.save(user);
    		 jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	 ;
		} catch (Exception e) {
			jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
		}
         Utils.commend(resp, jsonStr);
    } 
       
    /**
     * 根据name查询
     * @param request
     * @param session
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/getName.do")  
    public String  get_Name(HttpServletRequest request, HttpSession session,Model model, User user){
    	try {
    		if (user.getName()=="") {
    			return getAll(request,model,session);
			}else{
				  List<User> list=new ArrayList<User>();
	              User record=new User();
	              record.setName(user.getName());
	              record.setRoleid( (int)session.getAttribute("userId"));
	              record.setId( (int)session.getAttribute("useId"));
	              list=Service.select_name(record);
	               model.addAttribute("records", list);  
	              return "/user/list";	   
			}
		} catch (Exception e) {
			return "/system/error";
		}
    }
    
    
    
    
    /**
     * 批量删除
     * @param model
     * @param resp
     * @param req
     */
    @RequestMapping("/batchDelete.do")  
    public void batchDelete(Model model,HttpServletResponse resp,HttpServletRequest req) {
    	String jsonStr ="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
    	try {
    		String[] ids=req.getParameterValues("ids");
        	if (Service.batchDelete(ids)) {
        		jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";
			}
		} catch (Exception e) {
			jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
		}
    	Utils.commend(resp, jsonStr);	
	}

    
}  





