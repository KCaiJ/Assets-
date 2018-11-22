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
import com.assets.model.Device;
import com.assets.model.Deviceout;
import com.assets.model.Devicetype;

public interface DeviceService {  
    void save(Device role);    
    boolean update(Device role);    
    boolean delete(int id);    
    Device findById(int id);    
    List<Device> findAll(int oid);  
    List<Device> select_brand(Device role);
    boolean batchDelete(String[] ids);
    List<Organ> getOrgan();
    List<Devicetype> getDeviceType();
    void savaout(Deviceout deviceout);
    boolean importExcelInfo(InputStream in, MultipartFile file, String salaryDate,int adminId,String user) throws Exception;
	XSSFWorkbook exportExcelInfo(int oid,String salaryDate);
 
}  