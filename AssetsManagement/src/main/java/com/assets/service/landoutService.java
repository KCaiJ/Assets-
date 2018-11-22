package com.assets.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.rpc.holders.StringHolder;
import com.assets.model.Organ;
import com.assets.model.Role;
import com.assets.model.User;
import com.assets.model.land;
import com.assets.model.landout;

public interface landoutService {  
    List<landout> findAll(int oid);  
    landout findById(int id);
    boolean batchNo(String[] ids,String name);
    boolean batchTo(String[] ids,String name);
    boolean update(landout role); 
}  