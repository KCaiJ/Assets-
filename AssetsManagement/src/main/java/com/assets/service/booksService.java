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
import com.assets.model.books;
import com.assets.model.booksout;
import com.assets.model.bookstype;

public interface booksService {  
    void save(books role);    
    boolean update(books role);    
    boolean delete(int id);    
    books findById(int id);    
    List<books> findAll(int oid);  
    List<books> select_brand(books role);
    boolean batchDelete(String[] ids);
    List<Organ> getOrgan();
    List<bookstype> getbooksType();
    void savaout(booksout booksout);
    boolean importExcelInfo(InputStream in, MultipartFile file, String salaryDate,int adminId,String user) throws Exception;
	XSSFWorkbook exportExcelInfo(int oid,String salaryDate);
 
}  