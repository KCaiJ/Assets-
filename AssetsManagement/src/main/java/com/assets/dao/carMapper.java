package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.car;
import com.assets.model.carout;
import com.assets.model.cartype;
import com.assets.model.car;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface carMapper {    
	void savaout(carout carout);
    void save(car role);    
    boolean update(car role);    
    boolean delete(int id);    
    car findById(int id);    
    List<car> findAll(int oid);    
    List<car> select_core(car role);
    List<Organ> findOrganALL();
    List<cartype> findAllDtype();  
}    