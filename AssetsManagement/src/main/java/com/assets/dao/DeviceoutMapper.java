package com.assets.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.Device;
import com.assets.model.Deviceout;
import com.assets.model.Devicetype;
import com.assets.model.Device;  


/** 
 * ÔöÉ¾¸Ä´Ó
 * @author 
 */  
public interface DeviceoutMapper {
	List<Deviceout> select_code(Deviceout role);
    Deviceout findById(int id);    
    List<Deviceout> findAll(int oid);
    List<Deviceout> getAll_record(@Param("status")int status,@Param("oid") int oid);
    boolean update(Deviceout role); 
    void setStatus(Deviceout role);

}    