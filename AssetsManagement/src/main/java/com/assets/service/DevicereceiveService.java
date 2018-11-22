package com.assets.service;

import java.util.List;

import com.assets.model.Device;
import com.assets.model.Devicereceive;

public interface DevicereceiveService {
	
	
    boolean save(Devicereceive role);    
    boolean update(Devicereceive role);    
    boolean delete(int id);    
    List<Device> getDeviceAll(int oid);
    Devicereceive findById(int id);    
    List<Devicereceive> findAll(int oid);  
    boolean setreurn(Devicereceive role);
    boolean batchDelete(String[] ids);
}
