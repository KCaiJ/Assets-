package com.assets.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.house;
import com.assets.model.houseout;
import com.assets.model.house;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface houseoutMapper {
    houseout findById(int id);    
    List<houseout> findAll(int oid);
    boolean update(houseout role); 
    void setStatus(houseout role);

}    