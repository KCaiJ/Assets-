package com.assets.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.assets.model.Organ;
import com.assets.model.Role;
import com.assets.model.User;  


/** 
 * �û�����ɾ�Ĵ�
 * @author 
 */  
public interface UserMapper {   
	Role getpowerById(int id);
    void save(User user);    
    boolean update(User user);    
    boolean delete(int id);    
    User findById(int id);    
    List<User> findAll(@Param("id")int id,@Param("roleid")int roleid);  
    List<User> select(User user);
    List<Organ> findOrganALL();
    List<Role> findRoleALL();
    List<User> select_name(User user);
}    