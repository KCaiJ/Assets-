package com.assets.service;

import java.util.ArrayList;
import java.util.List;

import com.assets.model.Organ;
import com.assets.model.Role;
import com.assets.model.User;
import com.assets.model.Devicetype;

public interface DevicetypeService {  
    void save(Devicetype role);    
    boolean update(Devicetype role);    
    boolean delete(int id);    
    Devicetype findById(int id);    
    List<Devicetype> findAll(int oid);  
    List<Devicetype> select_brand(Devicetype role);
    boolean batchDelete(String[] ids);
}  