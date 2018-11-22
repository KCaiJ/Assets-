package com.assets.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.assets.dao.carMapper;
import com.assets.dao.caroutMapper;
import com.assets.dao.carreceiveMapper;
import com.assets.model.car;
import com.assets.model.carout;
import com.assets.model.carreceive;
import com.assets.model.cartype;
import com.assets.model.Organ;
import com.assets.service.carService;
import com.assets.service.carreceiveService;
@Service("carreceiveService")
public class carreceiveServiceImpl implements carreceiveService{

	@Resource
	private carreceiveMapper mapper;

	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean save(carreceive role) {
		try {
			mapper.save(role);
			role.setStatus(4);
			mapper.setStatus(role);
			return true;
		} catch (Exception e) {
			 e.printStackTrace();
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//�ֶ��ع�����
             return false;
		}
		
	}

	@Override
	public boolean update(carreceive role) {
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
	public carreceive findById(int id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public List<carreceive> findAll(int oid) {
		// TODO Auto-generated method stub
		return mapper.findAll(oid);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean setreurn(carreceive role) {
		try {
			mapper.setreurn(role);
			role=mapper.findById(role.getId());
			role.setStatus(1);
			mapper.setStatus(role);
			return true;
		} catch (Exception e) {
			 e.printStackTrace();
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//�ֶ��ع�����
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
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//�ֶ��ع�����
             return false;
        }
	}

}
