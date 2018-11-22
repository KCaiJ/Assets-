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
import com.assets.model.house;
import com.assets.model.houseout;

public interface houseService {  
	void save(house role);    
    boolean update(house role);    
    boolean delete(int id);    
    house findById(int id);    
    List<house> findAll(int oid);   
    boolean batchDelete(String[] ids);
    List<Organ> getOrganfindAll(int oid);
    List<User> findUserAll(int oid);
    void savaout(houseout booksout);
}  