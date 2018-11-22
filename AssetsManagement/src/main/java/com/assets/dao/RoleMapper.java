package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.assets.model.Role;
import com.assets.model.RoleAction;
import com.assets.model.User;  


/** 
 * 角色的增删改从
 * @author 
 */  
public interface RoleMapper {    
    void save(Role role);    
    boolean update(Role role);    
    boolean delete(int id);    
    Role findById(int id);    
    List<Role> findAll(int oid);  
    List<Role> select(Role role);
    int maxid();
    void savepower(RoleAction map);
    void delete_power(int id);
}    