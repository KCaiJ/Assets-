package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.books;
import com.assets.model.booksrepair;
import com.assets.model.bookstype;
import com.assets.model.books;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface booksrepairMapper {    
	void setStatus(booksrepair role);
	void save(booksrepair role);    
    boolean update(booksrepair role);    
    boolean delete(int id);    
    booksrepair findById(int id);    
    List<booksrepair> findAll(int oid);  
    List<books> getbooksAll(int oid);
    boolean setreurn(booksrepair role);
}    