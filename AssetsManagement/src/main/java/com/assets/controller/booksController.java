package com.assets.controller;

import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.assets.model.books;
import com.assets.model.booksout;
import com.assets.model.User;
import com.assets.service.booksService;
import com.assets.service.booksService;
import com.assets.service.OrganService;
import com.assets.tool.Utils;

@Controller  
@RequestMapping("/books")  
public class booksController {  
    @Autowired  
    private booksService Service;  
    
    /**  
     * ��ȡ�����û��б�  
     * @param request  
     * @return  
     */  
    @RequestMapping("/getAll.do")  
    public String getAll(HttpServletRequest request,Model model, HttpSession session){  
    	try {
    		int useoId=(int) session.getAttribute("useoId");
    		System.err.println(useoId);
            List<books> user = Service.findAll(useoId);  
            model.addAttribute("records", user);  
            request.setAttribute("records", user);
            return "/books/books/list";	
		} catch (Exception e) {
			e.printStackTrace();
			return "/system/error";
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
    	model.addAttribute("types", Service.getbooksType());
        return "/books/books/add";  
    }  
    
    @RequestMapping("/out.do")  
    public String name(Model model,int id) {
    	model.addAttribute("id", id);
    	return "/books/books/out";  
	}

    
    @RequestMapping("/acceptout.do")  
    public void accep(Model model,booksout booksout,HttpServletResponse resp, HttpSession session){
    	String jsonStr="{\"code\":\"1001\",\"msg\":\"����ʧ��\"}"; 
    	try {
    		 User u=(User) session.getAttribute("user"); 
    		 booksout.setCrtm(Utils.date());
    		 booksout.setMdtm(Utils.date());
    		 booksout.setOutdate(Utils.date());
    		 booksout.setOutter(u.getName());
    		 booksout.setStatus(1);
    		 Service.savaout(booksout);
    		 jsonStr ="{\"code\":\"1000\",\"msg\":\"�����ɹ�\"}";	 ;
		} catch (Exception e) {
			e.printStackTrace();
			jsonStr="{\"code\":\"1001\",\"msg\":\"����ʧ��\"}";
		}
         Utils.commend(resp, jsonStr);
    }
    
    
    @RequestMapping("/insert.do")  
    public void insert(books user,Model model,HttpServletResponse resp, HttpSession session){  
    	String jsonStr ="{\"code\":\"1000\",\"msg\":\"�����ɹ�\"}";	
    	try {
    		 User u=(User) session.getAttribute("user"); 
    		 user.setOid((int) session.getAttribute("useoId"));
    		 user.setCreator(u.getName()); 
    		 user.setCode("J"+Utils.getCurrentYear()+Utils.generateShortUuid());
    		 user.setCreatetime(Utils.date());
    		 user.setStatus(1);
    		 user.setCrtm(Utils.date());
    		 user.setMdtm(Utils.date());
    		 Service.save(user);
    		 jsonStr ="{\"code\":\"1000\",\"msg\":\"�����ɹ�\"}";	 ;
		} catch (Exception e) {
			e.printStackTrace();
			jsonStr="{\"code\":\"1001\",\"msg\":\"����ʧ��\"}";
		}
         Utils.commend(resp, jsonStr);
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
		books bean=Service.findById(id);
		model.addAttribute("user", bean);
		model.addAttribute("organs", Service.getOrgan());
    	model.addAttribute("types", Service.getbooksType());
	    return "/books/books/edit";  
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
    public void update(int id,HttpServletResponse resp,books user,HttpServletRequest request,Model model){  
    	try {
      	  String jsonStr="";
      	  books olduser = Service.findById(id);
      	  user.setCreator(olduser.getCreator());
      	  user.setCrtm(olduser.getCrtm());
      	  user.setStatus(olduser.getStatus());
      	  user.setCode(olduser.getCode());
      	  user.setCreatetime(olduser.getCreatetime());  
      	  user.setMdtm(Utils.date());
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
     * ����name��ѯ
     * @param request
     * @param session
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/getName.do")  
    public String  getName(HttpServletRequest request, HttpSession session,Model model, books user){
    	try {
    		 List<books> list=new ArrayList<books>();
    		 user.setOid((int) session.getAttribute("useoId"));
	         list=Service.select_brand(user);
	         model.addAttribute("records", list);  
	         return "/books/books/list";	   	
		} catch (Exception e) {
			e.printStackTrace();
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
    
    @RequestMapping("/import.do")
    public String impor(Model model,HttpServletResponse resp,HttpServletRequest req){
    	 return "/books/books/import";	   	
    }
    
    @RequestMapping("/insert_import.do")
    public void impotr(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response)  {
    		String  jsonstr="{\"code\":\"1002\",\"msg\":\"����ʧ��\"}" ;
    	try {
    		 int adminId = (int) session.getAttribute("useoId");
    		 User u=(User) session.getAttribute("user");
             //��ȡ�ϴ����ļ�
             MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
             MultipartFile file = multipart.getFile("upfile");
             String month = Utils.date();
             InputStream in = file.getInputStream();
             //���ݵ���
             if (Service.importExcelInfo(in,file,month,adminId,u.getName())) {
            	 in.close();
                 jsonstr ="{\"code\":\"1000\",\"msg\":\"����ɹ�\"}";
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	Utils.commend(response, jsonstr);
    }

    
    
    @RequestMapping("/export.do")
    public @ResponseBody void export(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException, UnsupportedEncodingException {
    	String salaryDate = request.getParameter("string");
        String jsonstr="";
        if(salaryDate!=""){
            response.reset(); //���buffer����
            Map<String,Object> map=new HashMap<String,Object>();
            // ָ�����ص��ļ��������������ʹ�ñ��ر��룬��GBK��������յ�����ļ�������ISO-8859-1�����룬Ȼ����GBK����ʾ
            // ����������GBK���룬ISO-8859-1�����룬��������Ǳ߻ᷴ����ִ�С�
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(salaryDate.getBytes("GBK"),"ISO-8859-1")+".xlsx");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            XSSFWorkbook workbook=null;
            //����Excel����
            workbook = Service.exportExcelInfo((int) session.getAttribute("useoId"),salaryDate);
            OutputStream output;
            try {
                output = response.getOutputStream();
                BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
                bufferedOutPut.flush();
                workbook.write(bufferedOutPut);
                bufferedOutPut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}  





