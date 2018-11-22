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
     * ��ȡ���� �б�  
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
    * ����id��ѯ����   
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
		if (jusrisdiction.contains("����")) {
			model.addAttribute("insert",1 ); 
		} else{
			model.addAttribute("insert",0 ); 
		}
		
		if (jusrisdiction.contains("ɾ��")) {
			model.addAttribute("delete",1 ); 
		} else{
			model.addAttribute("delete",0 ); 
		}
		
		if (jusrisdiction.contains("����")) {
			model.addAttribute("update",1 ); 
		} else{
			model.addAttribute("update",0 ); 
		}
		
		if (jusrisdiction.contains("��ѯ")) {
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
    * ��� 
    * @param user  
    * @param request  
    * @return  
    */  
   @RequestMapping("/add.do")  
   public String add(Model model){ 
       return "/role/add";  
   }
   
   /**
    * ����name��ѯ
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
     * ɾ��   
     * @param id  
     * @param request  
     * @param response  
     */
    
    @RequestMapping("/del.do")  
    public void  del(int id,HttpServletResponse resp,HttpServletRequest request){  
    	
    		String jsonStr ="{\"code\":\"1000\",\"msg\":\"�����ɹ�\"}";
            if(Service.delete(id)){  
            	jsonStr ="{\"code\":\"1000\",\"msg\":\"�����ɹ�\"}";	 ;
            }  else{
            	 jsonStr="{\"code\":\"1001\",\"msg\":\"����ʧ��,�����Ƿ����û����ڸý�ɫ\"}";
            }
            Utils.commend(resp, jsonStr);
	
        
    } 
    
    
    /**  
     * ��� 
     * @param user  
     * @param request  
     * @return  
     */  
    @RequestMapping("/insert.do")  
    public void insert(Role user,Model model,HttpServletResponse resp,HttpServletRequest request){  

    	String jsonStr="{\"code\":\"1001\",\"msg\":\"����ʧ��\"}";
    	try {
    		 if(Service.save(user)){
    			 jsonStr ="{\"code\":\"1000\",\"msg\":\"�����ɹ�\"}";	 ; 
    		 } 
		} catch (Exception e) {
			jsonStr="{\"code\":\"1001\",\"msg\":\"����ʧ��\"}";
		}
         Utils.commend(resp, jsonStr);
    }  

    

    
    
    /**  
     *�༭   
     * @param user  
     * @param request  
     * @return  
     */
    @RequestMapping("/update.do")  
    public void update(HttpServletResponse resp,Role user,HttpServletRequest request,Model model){  
    	try {
      	  String jsonStr="{\"code\":\"1001\",\"msg\":\"����ʧ��\"}";
      	  Role olduser = Service.findById(user.getRoleid());
      	  user.setMdtm(Utils.date());
      	  user.setCrtm(olduser.getCrtm());
      	  if(Service.update(user)){
               jsonStr ="{\"code\":\"1000\",\"msg\":\"�����ɹ�\"}";	
          }
      	  Utils.commend(resp, jsonStr);
		} catch (Exception e) {
			Utils.commend(resp, "{\"code\":\"1001\",\"msg\":\"����ʧ��\"}");
		}
    	  
    }  
    
    /**
     * ����ɾ��
     * @param model
     * @param resp
     * @param req
     */
    @RequestMapping("/batchDelete.do")  
    public void batchDelete(Model model,HttpServletResponse resp,HttpServletRequest req) {
    	
    	String jsonStr ="{\"code\":\"1001\",\"msg\":\"����ʧ��,�����Ƿ��Ƿ����û����ڸý�ɫ\"}";
    	try {
    		String[] ids=req.getParameterValues("ids");
        	if (Service.batchDelete(ids)) {
        		jsonStr ="{\"code\":\"1000\",\"msg\":\"�����ɹ�\"}";
			}
		} catch (Exception e) {
			jsonStr="{\"code\":\"1001\",\"msg\":\"����ʧ��\"}";
		}
    	Utils.commend(resp, jsonStr);	
    	
    }
}  





