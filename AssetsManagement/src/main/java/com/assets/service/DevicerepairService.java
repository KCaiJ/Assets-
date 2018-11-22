package com.assets.service;

import java.util.List;

import com.assets.model.Device;
import com.assets.model.Devicerepair;

public interface DevicerepairService {
	
	
    boolean save(Devicerepair role);    
    boolean update(Devicerepair role);    
    boolean delete(int id);    
    List<Device> getDeviceAll(int oid);
    Devicerepair findById(int id);    
    List<Devicerepair> findAll(int oid);  
    boolean setreurn(Devicerepair role);
    boolean batchDelete(String[] ids);
}
