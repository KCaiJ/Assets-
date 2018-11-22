package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.cartype;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface cartypeMapper {    
    void save(cartype role);    
    boolean update(cartype role);    
    boolean delete(int id);    
    cartype findById(int id);    
    List<cartype> findAll(int oid);  
    List<cartype> select_brand(cartype role);
}    