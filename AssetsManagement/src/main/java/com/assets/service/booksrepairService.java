package com.assets.service;

import java.util.List;

import com.assets.model.books;
import com.assets.model.booksrepair;

public interface booksrepairService {
	
	
    boolean save(booksrepair role);    
    boolean update(booksrepair role);    
    boolean delete(int id);    
    List<books> getbooksAll(int oid);
    booksrepair findById(int id);    
    List<booksrepair> findAll(int oid);  
    boolean setreurn(booksrepair role);
    boolean batchDelete(String[] ids);
}
