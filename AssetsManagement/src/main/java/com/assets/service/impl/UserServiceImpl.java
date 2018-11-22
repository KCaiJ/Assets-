package com.assets.service.impl;
import java.util.ArrayList;
import java.util.List;  

import javax.annotation.Resource;  
  
import org.springframework.stereotype.Service;  
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.assets.dao.UserMapper;
import com.assets.model.Organ;
import com.assets.model.Role;
import com.assets.model.User;
import com.assets.service.UserService;  

@Service("userService")
public class UserServiceImpl implements UserService {  
 
    @Resource  
    private UserMapper mapper;  
  
    /**  
     * 根据  id  删除 数据  
     */  
    public boolean delete(int id) {  
        return mapper.delete(id);  
    }  
    /**  
     * 查询User的全部数据  
     */  
    public List<User> findAll(int id,int roleid) {  
        List<User> findAllList = mapper.findAll(id,roleid);  
        return findAllList;  
    }  
    /**  
     * 根据 id 查询 对应数据  
     */  
    public User findById(int id) {  
        User user = mapper.findById(id);  
        return user;  
    }  
    /**  
     * 新增数据  
     */  
    public void save(User user) {  
        mapper.save(user);  
    }  
    /**  
     * 根据 id 修改对应数据  
     */  
    public boolean update(User user) {  
        return mapper.update(user);  
    }
    
    /**
     * 用户检测
     */
	public List<User> select(User user) {
		return mapper.select(user);  
	}
	
	@Override
	public List<Organ> findOrganALL() {
		// TODO Auto-generated method stub
		return mapper.findOrganALL();
	}
	
	@Override
	public List<Role> findRoleALL() {
		// TODO Auto-generated method stub
		return mapper.findRoleALL();  
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
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
             return false;
        }
	}
	@Override
	public List<User> select_name(User user) {
		// TODO Auto-generated method stub
		return mapper.select_name(user);
	}
	@Override
	public Role getpowerById(int id) {
		// TODO Auto-generated method stub
		return mapper.getpowerById(id);
	}
  
	
}  
