package com.assets.service;

import java.util.ArrayList;
import java.util.List;

import com.assets.model.Organ;
import com.assets.model.Role;
import com.assets.model.User;

public interface OrganService {  
    void save(Organ role);    
    boolean update(Organ role);    
    boolean delete(int id);    
    Organ findById(int id);    
    List<Organ> findAll(int oid);  
    List<Organ> select(Organ role);
    boolean batchDelete(String[] ids);
}  