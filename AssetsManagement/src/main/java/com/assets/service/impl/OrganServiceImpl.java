package com.assets.service.impl;
import java.util.ArrayList;
import java.util.List;  
import javax.annotation.Resource;  
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.assets.dao.OrganMapper;
import com.assets.dao.UserMapper;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.service.OrganService;
import com.assets.service.RoleService;
import com.assets.service.UserService;  

@Service("organService")
public class OrganServiceImpl implements OrganService {  
 
    @Resource  
    private OrganMapper mapper;  
  
    /**  
     * ����  id  ɾ�� ����  
     */  
    public boolean delete(int id) {  
        return mapper.delete(id);  
    }  
    /**  
     * ��ѯUser��ȫ������  
     */  
    public List<Organ> findAll(int oid) {  
        List<Organ> findAllList = mapper.findAll(oid);  
        return findAllList;  
    }  
    /**  
     * ���� id ��ѯ ��Ӧ����  
     */  
    public Organ findById(int id) {  
    	Organ user = mapper.findById(id);  
        return user;  
    }  
    /**  
     * ��������  
     */  
    public void save(Organ user) {  
        mapper.save(user);  
    }  
    /**  
     * ���� id �޸Ķ�Ӧ����  
     */  
    public boolean update(Organ user) {  
        return mapper.update(user);  
    }
    
    /**
     * �û����
     */
	public List<Organ> select(Organ user) {
		return mapper.select(user);  
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
