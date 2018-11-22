package com.assets.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.assets.model.User;
import com.assets.model.booksout;
import com.assets.model.land;
import com.assets.model.landout;
import com.assets.service.OrganService;
import com.assets.service.landService;
import com.assets.tool.Utils;

@Controller  
@RequestMapping("/land")  
public class landController {  
    @Autowired  
    private landService Service;  
    
    /**  
     * 获取所有用户列表  
     * @param request  
     * @return  
     */  
    @RequestMapping("/getAll.do")  
    public String getAll(HttpServletRequest request,Model model, HttpSession session){  
    	try {
            List<land> user = Service.findAll((int) session.getAttribute("useoId"));  
            model.addAttribute("records", user);  
            request.setAttribute("records", user);
            return "/land/land/list";	
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
		//organs
		land l=Service.findById(id);
		model.addAttribute("organs", Service.getOrganfindAll(l.getOid()));  
		model.addAttribute("user",l );  
	    return "/land/land/edit";  
	} catch (Exception e) {
		e.printStackTrace();
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
    public void update(HttpServletResponse resp,land user,HttpServletRequest request,Model model){  
    	try {
    		System.err.println(user.toString());
      	  String jsonStr="";
      	  land olduser = Service.findById(user.getDid());
      	System.err.println(olduser.toString());
      	  olduser.setLandlaction(user.getLandlaction());
      	  olduser.setLandarea(user.getLandarea());
      	  olduser.setOid(user.getOid());
      	  olduser.setMdtm(Utils.date());
      	  if(Service.update(olduser)){  
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
        return "/land/land/add";  
    }  
    
    @RequestMapping("/insert.do")  
    public void insert(land user,Model model,HttpServletResponse resp, HttpSession session){  
    	String jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	
    	try {
    		 int oid=(int) session.getAttribute("useoId");
    		 User u=(User) session.getAttribute("user");
    		 user.setOid(oid);
    		 user.setCreator(u.getName());
    		 user.setStatus(1);
    		 user.setLandcode(UUID.randomUUID().toString());
    		 user.setCrtm(Utils.date());
    		 user.setMdtm(Utils.date());
    		 System.err.println(user.toString());
    		 Service.save(user);
    		 jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	 ;
		} catch (Exception e) {
			e.printStackTrace();
			jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
		}
         Utils.commend(resp, jsonStr);
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
    
    @RequestMapping("/out.do")  
    public String name(Model model,int id, HttpSession session) {
    	model.addAttribute("id", id);
    	model.addAttribute("users", Service.findUserAll((int) session.getAttribute("useoId")));
    	return "/land/land/out";  
	}
    
    @RequestMapping("/acceptout.do")  
    public void accep(Model model,landout booksout,HttpServletResponse resp, HttpSession session){
    	String jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}"; 
    	try {
    		 User u=(User) session.getAttribute("user"); 
    		 booksout.setCrtm(Utils.date());
    		 booksout.setMdtm(Utils.date());
    		 booksout.setOutter(u.getName());
    		 booksout.setStatus(1);
    		 Service.savaout(booksout);
    		 jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	 ;
		} catch (Exception e) {
			e.printStackTrace();
			jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
		}
         Utils.commend(resp, jsonStr);
    }
}  





