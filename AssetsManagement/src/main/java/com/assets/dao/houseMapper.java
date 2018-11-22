package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.booksout;
import com.assets.model.house;
import com.assets.model.houseout;
import com.assets.model.house;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface houseMapper {    
	List<User> findUserAll(int oid);
	List<Organ> getOrganfindAll(int oid);
    void save(house role);    
    boolean update(house role);    
    boolean delete(int id);    
    house findById(int id);    
    List<house> findAll(int oid);  
    void savaout(houseout booksout);
}    