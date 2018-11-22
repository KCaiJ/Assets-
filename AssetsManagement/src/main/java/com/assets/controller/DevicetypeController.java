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

import com.assets.model.Devicetype;
import com.assets.service.DevicetypeService;
import com.assets.service.OrganService;
import com.assets.tool.Utils;

@Controller  
@RequestMapping("/deviceType")  
public class DevicetypeController {  
    @Autowired  
    private DevicetypeService Service;  
    
    /**  
     * ��ȡ�����û��б�  
     * @param request  
     * @return  
     */  
    @RequestMapping("/getAll.do")  
    public String getAll(HttpServletRequest request,Model model, HttpSession session){  
    	try {
            List<Devicetype> user = Service.findAll((int) session.getAttribute("useoId"));  
            for(Devicetype s:user){
            	int crtmY=Utils.StringToDate(s.getCrtm()).getYear();
        		int newY=Utils.StringToDate(Utils.date()).getYear();
            	s.setAsl(newY-crtmY);
            }
            model.addAttribute("records", user);  
            request.setAttribute("records", user);
            return "/device/type/list";	
		} catch (Exception e) {
			e.printStackTrace();
			return "/system/error";
		}
    }  
    
    /**
    * ����id��ѯ�����û�  
    * @param id  
    * @param request  
    * @return  
    */ 
 
    
   @RequestMapping("/get.do")  
   public String get(int id,HttpServletRequest request,Model model){  
	try {
		Devicetype bean=Service.findById(id);
		int crtmY=Utils.StringToDate(bean.getCrtm()).getYear();
		int newY=Utils.StringToDate(Utils.date()).getYear();
		bean.setAsl(newY-crtmY);
		model.addAttribute("user", bean);
	    return "/device/type/edit";  
	} catch (Exception e) {
		return "/system/error";
	}
       
   } 
    
    /**  
     *�༭�û�  
     * @param user  
     * @param request  
     * @return  
     */  
    @RequestMapping("/update.do")  
    public void update(HttpServletResponse resp,Devicetype user,HttpServletRequest request,Model model){  
    	try {
      	  String jsonStr="";
      	  Devicetype olduser = Service.findById(user.getDtid());
      	  user.setCrtm(olduser.getCrtm());
      	  user.setMdtm(Utils.date());
	      int crtmY=Utils.StringToDate(olduser.getCrtm()).getYear();
		  int newY=Utils.StringToDate(Utils.date()).getYear();
		  user.setAsl(newY-crtmY);
      	  if(Service.update(user)){  
                jsonStr ="{\"code\":\"1000\",\"msg\":\"�����ɹ�\"}";	
            }else{ 
          	  jsonStr="{\"code\":\"1001\",\"msg\":\"����ʧ��\"}";
            }  
      	  Utils.commend(resp, jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			Utils.commend(resp, "{\"code\":\"1001\",\"msg\":\"����ʧ��\"}");
		}
    	  
    }  
    
    /**  
     * ɾ���û�  
     * @param id  
     * @param request  
     * @param response  
     */  
    @RequestMapping("/del.do")  
    public void  del(int id,HttpServletResponse resp,HttpServletRequest request){  
    	try {
    		String jsonStr ="{\"code\":\"1001\",\"msg\":\"����ʧ��\"}";
            if(Service.delete(id)){  
            	jsonStr ="{\"code\":\"1000\",\"msg\":\"�����ɹ�\"}";	 ;
            }
            Utils.commend(resp, jsonStr);
		} catch (Exception e) {
			Utils.commend(resp, "{\"code\":\"1001\",\"msg\":\"����ʧ��\"}");
		}
        
    } 
    
    
    /**  
     * ����û�
     * @param user  
     * @param request  
     * @return  
     */  
    @RequestMapping("/add.do")  
    public String add(Model model){ 
        return "/device/type/add";  
    }  
   
    @RequestMapping("/insert.do")  
    public void insert(Devicetype user,Model model,HttpServletResponse resp){  
    	String jsonStr ="{\"code\":\"1000\",\"msg\":\"�����ɹ�\"}";	
    	try {
    		 user.setAnrv(1);
    		 user.setAsl(0);
    		 user.setCrtm(Utils.date());
    		 user.setMdtm(Utils.date());
    		 Service.save(user);
    		 jsonStr ="{\"code\":\"1000\",\"msg\":\"�����ɹ�\"}";	 ;
		} catch (Exception e) {
			jsonStr="{\"code\":\"1001\",\"msg\":\"����ʧ��\"}";
		}
         Utils.commend(resp, jsonStr);
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
    public String  getName(HttpServletRequest request, HttpSession session,Model model, Devicetype user){
    	try {
    		 List<Devicetype> list=new ArrayList<Devicetype>();
	         list=Service.select_brand(user);
	         model.addAttribute("records", list);  
	         return "/device/type/list";	   	
		} catch (Exception e) {
			return "/system/error";
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
    	String jsonStr ="{\"code\":\"1001\",\"msg\":\"����ʧ��\"}";
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





