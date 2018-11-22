package com.assets.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assets.model.car;
import com.assets.model.carout;
import com.assets.model.carrepair;
import com.assets.model.User;
import com.assets.service.carService;
import com.assets.service.carrepairService;
import com.assets.service.carService;
import com.assets.service.OrganService;
import com.assets.tool.Utils;

@Controller  
@RequestMapping("/carrepair")  
public class carrepairController {  
    @Autowired  
    private carrepairService Service;  
    
    /**  
     * 获取所有用户列表  
     * @param request  
     * @return  
     */  
    @RequestMapping("/getAll.do")  
    public String getAll(HttpServletRequest request,Model model, HttpSession session){  
    	try {
            List<carrepair> user = Service.findAll((int) session.getAttribute("useoId"));  
            model.addAttribute("records", user);  
            request.setAttribute("records", user);
            return "/car/repair/list";	
		} catch (Exception e) {
			e.printStackTrace();
			return "/system/error";
		}
    }  
    
    @RequestMapping("/add.do")  
    public String add(HttpServletRequest request,Model model, HttpSession session){  
    	model.addAttribute("car", Service.getcarAll((int) session.getAttribute("useoId")));
        return "/car/repair/add";  
    }  
    
    @RequestMapping("/repair.do")  
    public void insert( carrepair car,Model model,HttpServletResponse resp, HttpSession session){  
    	String jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
    	try {
    		 User u=(User) session.getAttribute("user"); 
    		 car.setDamager(u.getName());
    		 car.setDamagedate(Utils.date());
    		 car.setStatus(1);
    		 car.setCrtm(Utils.date());
    		 car.setMdtm(Utils.date());
    		 if (Service.save(car)) {
    			 jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	 ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
		}
         Utils.commend(resp, jsonStr);
    }  
 
    @RequestMapping("/return.do")  
    public String name(Model model,int id) {
    	model.addAttribute("id", id);
    	return "/car/repair/return";  
	}
    
    @RequestMapping("/updatereturn_to.do")  
    public void accep(Model model,carrepair carout,HttpServletResponse resp, HttpSession session){
    	String jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}"; 
    	try {
    		 User u=(User) session.getAttribute("user"); 
    		 carout.setRepairer(u.getName());
    		 carout.setMdtm(Utils.date());
    		 carout.setRepairdate(Utils.date());
    		 carout.setStatus(2);
    		if (Service.setreurn(carout)) {
    			 jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	 
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
		}
         Utils.commend(resp, jsonStr);
    }
    
    
    @RequestMapping("/del.do")  
    public void  del(int id,HttpServletResponse resp,HttpServletRequest request){  
    	try {
    		System.err.println(id);
    		String jsonStr ="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
            if(Service.delete(id)){  
            	jsonStr ="{\"code\":\"1000\",\"msg\":\"操作成功\"}";	 ;
            }
            Utils.commend(resp, jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
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





