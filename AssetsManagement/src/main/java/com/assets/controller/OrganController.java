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

import com.assets.model.Organ;
import com.assets.service.OrganService;
import com.assets.tool.Utils;

@Controller  
@RequestMapping("/organ")  
public class OrganController {  
    @Autowired  
    private OrganService Service;  
    
    /**  
     * 获取所有用户列表  
     * @param request  
     * @return  
     */  
    @RequestMapping("/getAll.do")  
    public String getAll(HttpServletRequest request,Model model, HttpSession session){  
    	try {
            List<Organ> user = Service.findAll((int) session.getAttribute("useoId"));  
            model.addAttribute("records", user);  
            request.setAttribute("records", user);
            return "/organ/list";	
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
    
   @RequestMapping("/get.do")  
   public String get(int id,HttpServletRequest request,Model model){  
	try {
		model.addAttribute("user", Service.findById(id));  
	    return "/organ/edit";  
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
    public void update(HttpServletResponse resp,Organ user,HttpServletRequest request,Model model){  
    	try {
      	  String jsonStr="";
      	  Organ olduser = Service.findById(user.getOid());
      	  user.setMdtm(Utils.date());
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
    		String jsonStr ="{\"code\":\"1001\",\"msg\":\"操作失败,请检查是否是否有用户属于该部门\"}";
            if(Service.delete(id)){  
            	jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	 ;
            }
            Utils.commend(resp, jsonStr);
		} catch (Exception e) {
			Utils.commend(resp, "{\"code\":\"1001\",\"msg\":\"操作失败,请检查是否是否有用户属于该部门\"}");
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
        return "/organ/add";  
    }  
   
    @RequestMapping("/insert.do")  
    public void insert(Organ user,Model model,HttpServletResponse resp){  
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
    public String  getName(HttpServletRequest request, HttpSession session,Model model, Organ user){
    	try {
    		if (user.getName()=="") {
    			return getAll(request,model,session);
			}else{
				  List<Organ> list=new ArrayList<Organ>();
				  Organ record=new Organ();
	              record.setName(user.getName());
	              record.setOid((int) session.getAttribute("useoId"));
	              list=Service.select(record);
	              model.addAttribute("records", list);  
	              return "/organ/list";	   
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
    	String jsonStr ="{\"code\":\"1001\",\"msg\":\"操作失败,请检查是否是否有用户属于该部门\"}";
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





