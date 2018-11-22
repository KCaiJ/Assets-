package com.assets.service.impl;
import java.util.ArrayList;
import java.util.List;  
import javax.annotation.Resource;  
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.assets.dao.cartypeMapper;
import com.assets.dao.OrganMapper;
import com.assets.dao.UserMapper;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.cartype;
import com.assets.service.cartypeService;
import com.assets.service.OrganService;
import com.assets.service.RoleService;
import com.assets.service.UserService;  

@Service("carTypeService")
public class cartypeServiceImpl implements cartypeService {  
 
    @Resource  
    private cartypeMapper mapper;  
  
    /**  
     * ����  id  ɾ�� ����  
     */  
    public boolean delete(int id) {  
        return mapper.delete(id);  
    }  
    /**  
     * ��ѯUser��ȫ������  
     */  
    public List<cartype> findAll(int oid) {
        List<cartype> findAllList = mapper.findAll(oid);  
        return findAllList;  
    }  
    /**  
     * ���� id ��ѯ ��Ӧ����  
     */  
    public cartype findById(int id) {  
    	cartype user = mapper.findById(id);  
        return user;  
    }  
    /**  
     * ��������  
     */  
    public void save(cartype user) {  
        mapper.save(user);  
    }  
    /**  
     * ���� id �޸Ķ�Ӧ����  
     */  
    public boolean update(cartype user) {  
        return mapper.update(user);  
    }
    
    /**
     * �û����
     */
	public List<cartype> select_brand(cartype user) {
		return mapper.select_brand(user);  
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean batchDelete(String[] ids) {
		try{
			for (String id : ids){
	    		mapper.delete(Integer.valueOf(id));
	    	}
			return true;
        }catch(Exception e){
             e.printStackTrace();
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//�ֶ��ع�����
             return false;
        }
	}
  
	
}  
