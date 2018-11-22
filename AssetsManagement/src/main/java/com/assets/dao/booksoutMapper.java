package com.assets.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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
public interface booksoutMapper {
	List<booksout> select_code(booksout role);
    booksout findById(int id);    
    List<booksout> findAll(int oid);
    List<booksout> getAll_record(@Param("status")int status,@Param("oid") int oid);
    boolean update(booksout role); 
    void setStatus(booksout role);

}    