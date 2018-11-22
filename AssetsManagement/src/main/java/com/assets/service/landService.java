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
import com.assets.model.land;
import com.assets.model.landout;

public interface landService {  
	void save(land role);    
    boolean update(land role);    
    boolean delete(int id);    
    land findById(int id);    
    List<land> findAll(int oid);   
    boolean batchDelete(String[] ids);
    List<Organ> getOrganfindAll(int oid);
    List<User> findUserAll(int oid);
    void savaout(landout booksout);
}  