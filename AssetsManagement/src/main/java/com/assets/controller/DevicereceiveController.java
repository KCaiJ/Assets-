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

import com.assets.model.Device;
import com.assets.model.Deviceout;
import com.assets.model.Devicereceive;
import com.assets.model.User;
import com.assets.service.DeviceService;
import com.assets.service.DevicereceiveService;
import com.assets.service.DeviceService;
import com.assets.service.OrganService;
import com.assets.tool.Utils;

@Controller  
@RequestMapping("/devicereceive")  
public class DevicereceiveController {  
    @Autowired  
    private DevicereceiveService Service;  
    
    /**  
     * 获取所有用户列表  
     * @param request  
     * @return  
     */  
    @RequestMapping("/getAll.do")  
    public String getAll(HttpServletRequest request,Model model, HttpSession session){  
    	try {
            List<Devicereceive> user = Service.findAll((int) session.getAttribute("useoId"));  
            model.addAttribute("records", user);  
            request.setAttribute("records", user);
            return "/device/receive/list";	
		} catch (Exception e) {
			e.printStackTrace();
			return "/system/error";
		}
    }  
    
    @RequestMapping("/add.do")  
    public String add(HttpServletRequest request,Model model, HttpSession session){  
    	model.addAttribute("device", Service.getDeviceAll((int) session.getAttribute("useoId")));
        return "/device/receive/add";  
    }  
    
    @RequestMapping("/receive.do")  
    public void insert( Devicereceive device,Model model,HttpServletResponse resp, HttpSession session){  
    	String jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}";
    	try {
    		 User u=(User) session.getAttribute("user"); 
    		 device.setCode("DJS"+Utils.getCurrentYear()+Utils.generateShortUuid());
    		 device.setRecipients(u.getName());
    		 device.setReceivedate(Utils.date());
    		 device.setStatus(1);
    		 device.setCrtm(Utils.date());
    		 device.setMdtm(Utils.date());
    		 if (Service.save(device)) {
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
    	return "/device/receive/return";  
	}
    
    @RequestMapping("/return_to.do")  
    public void accep(Model model,Devicereceive deviceout,HttpServletResponse resp, HttpSession session){
    	String jsonStr="{\"code\":\"1001\",\"msg\":\"操作失败\"}"; 
    	try {
    		 deviceout.setMdtm(Utils.date());
    		 deviceout.setReturndate(Utils.date());
    		 deviceout.setStatus(2);
    		if (Service.setreurn(deviceout)) {
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





