package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.bookstype;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface bookstypeMapper {    
    void save(bookstype role);    
    boolean update(bookstype role);    
    boolean delete(int id);    
    bookstype findById(int id);    
    List<bookstype> findAll(int oid);  
    List<bookstype> select_brand(bookstype role);
}    