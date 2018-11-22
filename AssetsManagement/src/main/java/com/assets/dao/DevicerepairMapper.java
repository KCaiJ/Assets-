package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.Device;
import com.assets.model.Devicerepair;
import com.assets.model.Devicetype;
import com.assets.model.Device;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface DevicerepairMapper {    
	void setStatus(Devicerepair role);
	void save(Devicerepair role);    
    boolean update(Devicerepair role);    
    boolean delete(int id);    
    Devicerepair findById(int id);    
    List<Devicerepair> findAll(int oid);  
    List<Device> getDeviceAll(int oid);
    boolean setreurn(Devicerepair role);
}    