package com.assets.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.assets.dao.carMapper;
import com.assets.dao.caroutMapper;
import com.assets.dao.carrepairMapper;
import com.assets.model.car;
import com.assets.model.carout;
import com.assets.model.carrepair;
import com.assets.model.cartype;
import com.assets.model.Organ;
import com.assets.service.carService;
import com.assets.service.carrepairService;
@Service("carrepairService")
public class carrepairServiceImpl implements carrepairService{

	@Resource
	private carrepairMapper mapper;

	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean save(carrepair role) {
		try {
			mapper.save(role);
			role.setStatus(5);
			mapper.setStatus(role);
			return true;
		} catch (Exception e) {
			 e.printStackTrace();
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
             return false;
		}
		
	}

	@Override
	public boolean update(carrepair role) {
		// TODO Auto-generated method stub
		return mapper.update(role);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return mapper.delete(id);
	}

	@Override
	public List<car> getcarAll(int oid) {
		// TODO Auto-generated method stub
		return mapper.getcarAll(oid);
	}

	@Override
	public carrepair findById(int id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public List<carrepair> findAll(int oid) {
		// TODO Auto-generated method stub
		return mapper.findAll(oid);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean setreurn(carrepair role) {
		try {
			mapper.setreurn(role);
			role=mapper.findById(role.getId());
			role.setStatus(1);
			mapper.setStatus(role);
			return true;
		} catch (Exception e) {
			 e.printStackTrace();
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
             return false;
		}
		
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

}
