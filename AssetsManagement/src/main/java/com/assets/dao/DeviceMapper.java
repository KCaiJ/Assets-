package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.Device;
import com.assets.model.Deviceout;
import com.assets.model.Devicetype;
import com.assets.model.Device;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface DeviceMapper {    
	void savaout(Deviceout deviceout);
    void save(Device role);    
    boolean update(Device role);    
    boolean delete(int id);    
    Device findById(int id);    
    List<Device> findAll(int oid);    
    List<Device> select_core(Device role);
    List<Organ> findOrganALL();
    List<Devicetype> findAllDtype();  
}    