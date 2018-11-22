package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.Device;
import com.assets.model.Devicereceive;
import com.assets.model.Devicetype;
import com.assets.model.Device;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface DevicereceiveMapper {    
	void setStatus(Devicereceive role);
	void save(Devicereceive role);    
    boolean update(Devicereceive role);    
    boolean delete(int id);    
    Devicereceive findById(int id);    
    List<Devicereceive> findAll(int oid);  
    List<Device> getDeviceAll(int oid);
    boolean setreurn(Devicereceive role);
}    