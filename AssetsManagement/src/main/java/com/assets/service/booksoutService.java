package com.assets.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.holders.StringHolder;

import com.assets.model.Organ;
import com.assets.model.Role;
import com.assets.model.User;
import com.assets.model.books;
import com.assets.model.booksout;
import com.assets.model.bookstype;

public interface booksoutService {  
	List<booksout> select_code(booksout role);
    List<booksout> findAll(int oid);  
    booksout findById(int id);
    boolean batchNo(String[] ids,String name);
    boolean batchTo(String[] ids,String name);
    boolean update(booksout role);
	List<booksout> getAll_record(int status, int oid); 
    
}  