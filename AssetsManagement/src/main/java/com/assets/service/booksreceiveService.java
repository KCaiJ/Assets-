package com.assets.service;

import java.util.List;

import com.assets.model.books;
import com.assets.model.booksreceive;

public interface booksreceiveService {
	
	
    boolean save(booksreceive role);    
    boolean update(booksreceive role);    
    boolean delete(int id);    
    List<books> getbooksAll(int oid);
    booksreceive findById(int id);    
    List<booksreceive> findAll(int oid);  
    boolean setreurn(booksreceive role);
    boolean batchDelete(String[] ids);
}
