package com.assets.controller;

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

import com.assets.model.cartype;
import com.assets.service.cartypeService;
import com.assets.service.OrganService;
import com.assets.tool.Utils;

@Controller  
@RequestMapping("/carType")  
public class cartypeController {  
    @Autowired  
    private cartypeService Service;  
    
    /**  
     * 获取所有用户列表  
     * @param request  
     * @return  
     */  
    @RequestMapping("/getAll.do")  
    public String getAll(HttpServletRequest request,Model model, HttpSession session){  
    	try {
            List<cartype> user = Service.findAll((int) session.getAttribute("useoId"));  
            for(cartype s:user){
            	int crtmY=Utils.StringToDate(s.getCrtm()).getYear();
        		int newY=Utils.StringToDate(Utils.date()).getYear();
            	s.setAsl(newY-crtmY);
            }
            model.addAttribute("records", user);  
            request.setAttribute("records", user);
            return "/car/type/list";	
		} catch (Exception e) {
			e.printStackTrace();
			return "/system/error";
		}
    }  
    
    /**
    * 根据id查询单个用户  
    * @param id  
    * @param request  
    * @return  
    */ 
 
    
   @RequestMapping("/get.do")  
   public String get(int id,HttpServletRequest request,Model model){  
	try {
		cartype bean=Service.findById(id);
		int crtmY=Utils.StringToDate(bean.getCrtm()).getYear();
		int newY=Utils.StringToDate(Utils.date()).getYear();
		bean.setAsl(newY-crtmY);
		model.addAttribute("user", bean);
	    return "/car/type/edit";  
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
    public void update(HttpServletResponse resp,cartype user,HttpServletRequest request,Model model){  
    	try {
      	  String jsonStr="";
      	  cartype olduser = Service.findById(user.getDtid());
      	  user.setCrtm(olduser.getCrtm());
      	  user.setMdtm(Utils.date());
	      int crtmY=Utils.StringToDate(olduser.getCrtm()).getYear();
		  int newY=Utils.StringToDate(Utils.date()).getYear();
		  user.setAsl(newY-crtmY);
      	  if(Service.update(user)){  
                jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	
            }else{ 
          	  jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
            }  
      	  Utils.commend(resp, jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
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
    		String jsonStr ="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
            if(Service.delete(id)){  
            	jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	 ;
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
    public String add(Model model){ 
        return "/car/type/add";  
    }  
   
    @RequestMapping("/insert.do")  
    public void insert(cartype user,Model model,HttpServletResponse resp){  
    	String jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	
    	try {
    		 user.setAnrv(1);
    		 user.setAsl(0);
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
    public String  getName(HttpServletRequest request, HttpSession session,Model model, cartype user){
    	try {
    		 List<cartype> list=new ArrayList<cartype>();
	         list=Service.select_brand(user);
	         model.addAttribute("records", list);  
	         return "/car/type/list";	   	
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





