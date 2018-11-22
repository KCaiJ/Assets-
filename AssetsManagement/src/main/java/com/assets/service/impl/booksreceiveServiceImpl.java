package com.assets.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.assets.dao.booksMapper;
import com.assets.dao.booksoutMapper;
import com.assets.dao.booksreceiveMapper;
import com.assets.model.books;
import com.assets.model.booksout;
import com.assets.model.booksreceive;
import com.assets.model.bookstype;
import com.assets.model.Organ;
import com.assets.service.booksService;
import com.assets.service.booksreceiveService;
@Service("booksreceiveService")
public class booksreceiveServiceImpl implements booksreceiveService{

	@Resource
	private booksreceiveMapper mapper;

	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean save(booksreceive role) {
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
	public boolean update(booksreceive role) {
		// TODO Auto-generated method stub
		return mapper.update(role);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return mapper.delete(id);
	}

	@Override
	public List<books> getbooksAll(int oid) {
		// TODO Auto-generated method stub
		return mapper.getbooksAll(oid);
	}

	@Override
	public booksreceive findById(int id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public List<booksreceive> findAll(int oid) {
		// TODO Auto-generated method stub
		return mapper.findAll(oid);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean setreurn(booksreceive role) {
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
