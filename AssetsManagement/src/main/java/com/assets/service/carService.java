package com.assets.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.assets.model.Organ;
import com.assets.model.Role;
import com.assets.model.User;
import com.assets.model.car;
import com.assets.model.carout;
import com.assets.model.cartype;

public interface carService {  
    void save(car role);    
    boolean update(car role);    
    boolean delete(int id);    
    car findById(int id);    
    List<car> findAll(int oid);  
    List<car> select_brand(car role);
    boolean batchDelete(String[] ids);
    List<Organ> getOrgan();
    List<cartype> getcarType();
    void savaout(carout carout);
    boolean importExcelInfo(InputStream in, MultipartFile file, String salaryDate,int adminId,String user) throws Exception;
	XSSFWorkbook exportExcelInfo(int oid,String salaryDate);
 
}  