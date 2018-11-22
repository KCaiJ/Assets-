package com.assets.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.land;
import com.assets.model.landout;
import com.assets.model.land;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface landoutMapper {
    landout findById(int id);    
    List<landout> findAll(int oid);
    boolean update(landout role); 
    void setStatus(landout role);

}    