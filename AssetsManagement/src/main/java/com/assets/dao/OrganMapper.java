package com.assets.dao;

import java.util.ArrayList;
import java.util.List;
import com.assets.model.Organ;
import com.assets.model.User;  


/** 
 * ��ɫ����ɾ�Ĵ�
 * @author 
 */  
public interface OrganMapper {    
    void save(Organ role);    
    boolean update(Organ role);    
    boolean delete(int id);    
    Organ findById(int id);    
    List<Organ> findAll(int oid);  
    List<Organ> select(Organ role);
}    