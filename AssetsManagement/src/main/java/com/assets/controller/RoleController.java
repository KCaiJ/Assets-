package com.assets.controller;
import java.io.IOException;  
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.search.StringTerm;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;

import com.assets.model.Role;
import com.assets.model.RoleAction;
import com.assets.model.User;
import com.assets.service.RoleService;
import com.assets.tool.Utils;  
@Controller  
@RequestMapping("/role")  
public class RoleController {  
    @Autowired  
    private RoleService Service;  
    
    /**  
     * 获取所有 列表  
     * @param request  
     * @return  
     */  
    @RequestMapping("/getAll.do")  
    public String getAll(HttpServletRequest request,Model model, HttpSession session){  
    	try {
            List<Role> user = Service.findAll((int) session.getAttribute("userId"));  
            model.addAttribute("records", user);  
            request.setAttribute("records", user);
            return "/role/list";	
		} catch (Exception e) {
			e.printStackTrace();
			return "/system/error";
		}
    }  
    
    /**
    * 根据id查询单个   
    * @param id  
    * @param request  
    * @return  
    */  
    
   @RequestMapping("/get.do")  
   public String get(int id,HttpServletRequest request,Model model){  
	try {
		Role role=Service.findById(id);
		model.addAttribute("user",role );  
		String jusrisdiction=role.getJurisdiction();
		if (jusrisdiction.contains("增加")) {
			model.addAttribute("insert",1 ); 
		} else{
			model.addAttribute("insert",0 ); 
		}
		
		if (jusrisdiction.contains("删除")) {
			model.addAttribute("delete",1 ); 
		} else{
			model.addAttribute("delete",0 ); 
		}
		
		if (jusrisdiction.contains("更改")) {
			model.addAttribute("update",1 ); 
		} else{
			model.addAttribute("update",0 ); 
		}
		
		if (jusrisdiction.contains("查询")) {
			model.addAttribute("select",1 ); 
		} else{
			model.addAttribute("select",0 ); 
		}
	    return "/role/edit";  
	} catch (Exception e) {
		return "/system/error";
	}
       
   } 
    
   
   /**  
    * 添加 
    * @param user  
    * @param request  
    * @return  
    */  
   @RequestMapping("/add.do")  
   public String add(Model model){ 
       return "/role/add";  
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
   public String  getName(HttpServletRequest request, HttpSession session,Model model, Role user){
   	try {
   		if (user.getName()=="") {
   			return getAll(request,model,session);
			}else{
				  List<Role> list=new ArrayList<Role>();
				  Role record=new Role();
	              record.setName(user.getName());
	              System.err.println((int) session.getAttribute("userId"));
	              record.setRoleid((int) session.getAttribute("userId"));
	              list=Service.select(record);
	               model.addAttribute("records", list);  
	              return "/role/list";	   
			}
		} catch (Exception e) {
			return "/system/error";
		}
   }
    
   
   
   
   
   
    /**  
     * 删除   
     * @param id  
     * @param request  
     * @param response  
     */
    
    @RequestMapping("/del.do")  
    public void  del(int id,HttpServletResponse resp,HttpServletRequest request){  
    	
    		String jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";
            if(Service.delete(id)){  
            	jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	 ;
            }  else{
            	 jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败,请检查是否有用户属于该角色\"}";
            }
            Utils.commend(resp, jsonStr);
	
        
    } 
    
    
    /**  
     * 添加 
     * @param user  
     * @param request  
     * @return  
     */  
    @RequestMapping("/insert.do")  
    public void insert(Role user,Model model,HttpServletResponse resp,HttpServletRequest request){  

    	String jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
    	try {
    		 if(Service.save(user)){
    			 jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	 ; 
    		 } 
		} catch (Exception e) {
			jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
		}
         Utils.commend(resp, jsonStr);
    }  

    

    
    
    /**  
     *编辑   
     * @param user  
     * @param request  
     * @return  
     */
    @RequestMapping("/update.do")  
    public void update(HttpServletResponse resp,Role user,HttpServletRequest request,Model model){  
    	try {
      	  String jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
      	  Role olduser = Service.findById(user.getRoleid());
      	  user.setMdtm(Utils.date());
      	  user.setCrtm(olduser.getCrtm());
      	  if(Service.update(user)){
               jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	
          }
      	  Utils.commend(resp, jsonStr);
		} catch (Exception e) {
			Utils.commend(resp, "{\"code\":\"1001\",\"msg\":\"操作失败\"}");
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
    	
    	String jsonStr ="{\"code\":\"1001\",\"msg\":\"操作失败,请检查是否是否有用户属于该角色\"}";
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





