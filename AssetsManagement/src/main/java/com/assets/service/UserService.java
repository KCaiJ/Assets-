package com.assets.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.assets.model.Organ;
import com.assets.model.Role;
import com.assets.model.User;

public interface UserService {  
	Role getpowerById(int id);
    void save(User user);  
    boolean update(User user);  
    boolean delete(int id);  
    User findById(int id);  
    List<User> findAll(@Param("id")int id,@Param("roleid")int roleid);
    List<User> select(User user);
    List<Organ> findOrganALL();
    List<Role> findRoleALL();
    boolean batchDelete(String[] ids);
    List<User> select_name(User user);
}  