package com.assets.service;

import java.util.List;

import com.assets.model.car;
import com.assets.model.carreceive;

public interface carreceiveService {
	
	
    boolean save(carreceive role);    
    boolean update(carreceive role);    
    boolean delete(int id);    
    List<car> getcarAll(int oid);
    carreceive findById(int id);    
    List<carreceive> findAll(int oid);  
    boolean setreurn(carreceive role);
    boolean batchDelete(String[] ids);
}
