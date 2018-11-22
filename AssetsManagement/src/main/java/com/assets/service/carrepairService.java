package com.assets.service;

import java.util.List;

import com.assets.model.car;
import com.assets.model.carrepair;

public interface carrepairService {
	
	
    boolean save(carrepair role);    
    boolean update(carrepair role);    
    boolean delete(int id);    
    List<car> getcarAll(int oid);
    carrepair findById(int id);    
    List<carrepair> findAll(int oid);  
    boolean setreurn(carrepair role);
    boolean batchDelete(String[] ids);
}
