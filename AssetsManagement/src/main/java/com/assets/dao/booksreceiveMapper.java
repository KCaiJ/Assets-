package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.books;
import com.assets.model.booksreceive;
import com.assets.model.bookstype;
import com.assets.model.books;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface booksreceiveMapper {    
	void setStatus(booksreceive role);
	void save(booksreceive role);    
    boolean update(booksreceive role);    
    boolean delete(int id);    
    booksreceive findById(int id);    
    List<booksreceive> findAll(int oid);  
    List<books> getbooksAll(int oid);
    boolean setreurn(booksreceive role);
}    