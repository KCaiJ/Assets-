package com.assets.service.impl;
import java.util.ArrayList;
import java.util.List;  
import javax.annotation.Resource;  
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.assets.dao.houseMapper;
import com.assets.dao.UserMapper;
import com.assets.model.house;
import com.assets.model.houseout;
import com.assets.model.Organ;
import com.assets.model.User;
import com.assets.model.books;
import com.assets.model.booksout;
import com.assets.service.houseService;
import com.assets.service.RoleService;
import com.assets.service.UserService;  

@Service("houseService")
public class houseServiceImpl implements houseService {  
 
    @Resource  
    private houseMapper mapper;  
  
    /**  
     * ����  id  ɾ�� ����  
     */  
    public boolean delete(int id) {  
        return mapper.delete(id);  
    }  
    /**  
     * ��ѯUser��ȫ������  
     */  
    public List<house> findAll(int oid) {  
        List<house> findAllList = mapper.findAll(oid);  
        return findAllList;  
    }  
    /**  
     * ���� id ��ѯ ��Ӧ����  
     */  
    public house findById(int id) {  
    	house user = mapper.findById(id);  
        return user;  
    }  
    /**  
     * ��������  
     */  
    public void save(house user) {  
        mapper.save(user);  
    }  
    /**  
     * ���� id �޸Ķ�Ӧ����  
     */  
    public boolean update(house user) {  
        return mapper.update(user);  
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
	@Override
	public List<Organ> getOrganfindAll(int oid) {
		// TODO Auto-generated method stub
		return mapper.getOrganfindAll(oid);
	}
	@Override
	public List<User> findUserAll(int oid) {
		// TODO Auto-generated method stub
		return mapper.findUserAll(oid);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void savaout(houseout booksout) {
		try{
			mapper.savaout(booksout);
			house d=mapper.findById(booksout.getDid());
			d.setStatus(2);
			mapper.update(d);
        }catch(Exception e){
             e.printStackTrace();
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//�ֶ��ع�����
        }
		
	}
  
	
}  
