package com.assets.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.holders.StringHolder;

import com.assets.model.Organ;
import com.assets.model.Role;
import com.assets.model.User;
import com.assets.model.Device;
import com.assets.model.Deviceout;
import com.assets.model.Devicetype;

public interface DeviceoutService {  
	List<Deviceout> select_code(Deviceout role);
    List<Deviceout> findAll(int oid);  
    Deviceout findById(int id);
    boolean batchNo(String[] ids,String name);
    boolean batchTo(String[] ids,String name);
    boolean update(Deviceout role);
	List<Deviceout> getAll_record(int status, int oid); 
    
}  