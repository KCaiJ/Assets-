package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.Devicetype;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface DevicetypeMapper {    
    void save(Devicetype role);    
    boolean update(Devicetype role);    
    boolean delete(int id);    
    Devicetype findById(int id);    
    List<Devicetype> findAll(int oid);  
    List<Devicetype> select_brand(Devicetype role);
}    