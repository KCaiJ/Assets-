package com.assets.service.impl;
import java.util.ArrayList;
import java.util.List;  
import javax.annotation.Resource;  
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.assets.dao.bookstypeMapper;
import com.assets.dao.OrganMapper;
import com.assets.dao.UserMapper;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.bookstype;
import com.assets.service.bookstypeService;
import com.assets.service.OrganService;
import com.assets.service.RoleService;
import com.assets.service.UserService;  

@Service("booksTypeService")
public class bookstypeServiceImpl implements bookstypeService {  
 
    @Resource  
    private bookstypeMapper mapper;  
  
    /**  
     * ����  id  ɾ�� ����  
     */  
    public boolean delete(int id) {  
        return mapper.delete(id);  
    }  
    /**  
     * ��ѯUser��ȫ������  
     */  
    public List<bookstype> findAll(int oid) {
        List<bookstype> findAllList = mapper.findAll(oid);  
        return findAllList;  
    }  
    /**  
     * ���� id ��ѯ ��Ӧ����  
     */  
    public bookstype findById(int id) {  
    	bookstype user = mapper.findById(id);  
        return user;  
    }  
    /**  
     * ��������  
     */  
    public void save(bookstype user) {  
        mapper.save(user);  
    }  
    /**  
     * ���� id �޸Ķ�Ӧ����  
     */  
    public boolean update(bookstype user) {  
        return mapper.update(user);  
    }
    
    /**
     * �û����
     */
	public List<bookstype> select_brand(bookstype user) {
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
