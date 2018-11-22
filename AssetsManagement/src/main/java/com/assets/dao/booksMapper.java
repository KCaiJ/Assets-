package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.books;
import com.assets.model.booksout;
import com.assets.model.bookstype;
import com.assets.model.books;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface booksMapper {    
	void savaout(booksout booksout);
    void save(books role);    
    boolean update(books role);    
    boolean delete(int id);    
    books findById(int id);    
    List<books> findAll(int oid);    
    List<books> select_core(books role);
    List<Organ> findOrganALL();
    List<bookstype> findAllDtype();  
}    