package com.assets.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.assets.model.Role;
import com.assets.model.RoleAction;
import com.assets.model.User;

public interface RoleService {  
    boolean save(Role role);    
    boolean update(Role role);    
    boolean delete(int id);    
    Role findById(int id);    
    List<Role> findAll(int oid);  
    List<Role> select(Role role);
    boolean batchDelete(String[] ids);
}  