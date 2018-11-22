package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.car;
import com.assets.model.carreceive;
import com.assets.model.cartype;
import com.assets.model.car;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface carreceiveMapper {    
	void setStatus(carreceive role);
	void save(carreceive role);    
    boolean update(carreceive role);    
    boolean delete(int id);    
    carreceive findById(int id);    
    List<carreceive> findAll(int oid);  
    List<car> getcarAll(int oid);
    boolean setreurn(carreceive role);
}    