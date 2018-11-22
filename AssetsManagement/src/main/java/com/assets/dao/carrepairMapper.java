package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.car;
import com.assets.model.carrepair;
import com.assets.model.cartype;
import com.assets.model.car;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface carrepairMapper {    
	void setStatus(carrepair role);
	void save(carrepair role);    
    boolean update(carrepair role);    
    boolean delete(int id);    
    carrepair findById(int id);    
    List<carrepair> findAll(int oid);  
    List<car> getcarAll(int oid);
    boolean setreurn(carrepair role);
}    