package com.assets.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.holders.StringHolder;

import com.assets.model.Organ;
import com.assets.model.Role;
import com.assets.model.User;
import com.assets.model.car;
import com.assets.model.carout;
import com.assets.model.cartype;

public interface caroutService {  
	List<carout> select_code(carout role);
    List<carout> findAll(int oid);  
    carout findById(int id);
    boolean batchNo(String[] ids,String name);
    boolean batchTo(String[] ids,String name);
    boolean update(carout role);
	List<carout> getAll_record(int status, int oid); 
    
}  