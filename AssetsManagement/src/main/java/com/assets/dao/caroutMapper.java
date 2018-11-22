package com.assets.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.car;
import com.assets.model.carout;
import com.assets.model.cartype;
import com.assets.model.car;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface caroutMapper {
	List<carout> select_code(carout role);
    carout findById(int id);    
    List<carout> findAll(int oid);
    List<carout> getAll_record(@Param("status")int status,@Param("oid") int oid);
    boolean update(carout role); 
    void setStatus(carout role);

}    