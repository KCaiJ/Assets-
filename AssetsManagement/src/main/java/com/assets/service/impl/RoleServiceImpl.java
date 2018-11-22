package com.assets.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.assets.dao.RoleMapper;
import com.assets.dao.UserMapper;
import com.assets.model.Role;
import com.assets.model.RoleAction;
import com.assets.model.User;
import com.assets.service.RoleService;
import com.assets.service.UserService;
import com.assets.tool.Utils;  

@Service("roleService")
public class RoleServiceImpl implements RoleService {  
 
    @Resource  
    private RoleMapper mapper;  
  
    /**  
     * ����  id  ɾ�� ����  
     */ 
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(int id) {  
    	 try{
    		 mapper.delete_power(id);
             if(mapper.delete(id)){  
             	return true;  
             }
             return false; 
         }catch(Exception e){
              e.printStackTrace();
              TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//�ֶ��ع�����
              return false;
         }
    } 
    
    
    
    
    /**  
     * ��ѯUser��ȫ������  
     */  
    public List<Role> findAll(int oid) {  
        List<Role> findAllList = mapper.findAll(oid);  
        return findAllList;  
    }  
    /**  
     * ���� id ��ѯ ��Ӧ����  
     */  
    public Role findById(int id) {  
    	Role user = mapper.findById(id);  
        return user;  
    }  
    /**  
     * ��������  
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Role user) {
		try{
			user.setCrtm(Utils.date());
			user.setMdtm(Utils.date());
			String[] powers=user.getPower();
			mapper.save(user); 
			int id=mapper.maxid();
			for(String action:powers){
				 RoleAction map=new RoleAction();
				 map.setAction(action);
				 map.setRoleid(id);
				 mapper.savepower(map);
			}
			return true;
        }catch(Exception e){
             e.printStackTrace();
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//�ֶ��ع�����
             return false;
        }
		 
    } 
    	
    /**  
     * ���� id �޸Ķ�Ӧ����  
     */  
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Role user) {
    	try{
	    	if(mapper.update(user)){
	  		  	int roleid=user.getRoleid();
	  		    mapper.delete_power(roleid);//ɾ��ȫ��Ȩ��
	  		  	String[] powers=user.getPower();
	  		  	for(String action:powers){
	       			 RoleAction map=new RoleAction();
	       			 map.setAction(action);
	       			 map.setRoleid(roleid);
	       			 mapper.savepower(map);
	  		  	}
	            return true;
	        }else{ 
	        	return false;
	        }  
    	}catch(Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//�ֶ��ع�����
            return false;
    	}
    }
    
    /**
     * �û����
     */
	public List<Role> select(Role user) {
		return mapper.select(user);  
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean batchDelete(String[] ids) {
		try{
			for (String id : ids){
				mapper.delete_power(Integer.valueOf(id));
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
