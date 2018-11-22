package com.assets.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.rpc.holders.StringHolder;
import com.assets.model.Organ;
import com.assets.model.Role;
import com.assets.model.User;
import com.assets.model.house;
import com.assets.model.houseout;

public interface houseoutService {  
    List<houseout> findAll(int oid);  
    houseout findById(int id);
    boolean batchNo(String[] ids,String name);
    boolean batchTo(String[] ids,String name);
    boolean update(houseout role); 
}  