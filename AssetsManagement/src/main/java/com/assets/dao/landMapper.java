package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.booksout;
import com.assets.model.land;
import com.assets.model.landout;
import com.assets.model.land;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface landMapper {    
	List<User> findUserAll(int oid);
	List<Organ> getOrganfindAll(int oid);
    void save(land role);    
    boolean update(land role);    
    boolean delete(int id);    
    land findById(int id);    
    List<land> findAll(int oid);  
    void savaout(landout booksout);
}    