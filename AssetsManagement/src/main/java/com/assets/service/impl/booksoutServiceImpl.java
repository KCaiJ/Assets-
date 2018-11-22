package com.assets.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.assets.dao.booksMapper;
import com.assets.dao.booksoutMapper;
import com.assets.model.books;
import com.assets.model.booksout;
import com.assets.model.bookstype;
import com.assets.model.Organ;
import com.assets.service.booksService;
import com.assets.service.booksoutService;
import com.assets.tool.Utils;
import com.mysql.jdbc.Util;
@Service("booksoutService")
public class booksoutServiceImpl implements booksoutService{
	@Resource
	private booksoutMapper mapper;

	@Override
	public List<booksout> findAll(int oid) {
		// TODO Auto-generated method stub
		return mapper.findAll(oid);
	}

	@Override
	public booksout findById(int id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public List<booksout> select_code(booksout role) {
		// TODO Auto-generated method stub
		return mapper.select_code(role);
	}
	
	

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean batchNo(String[] ids,String name) {
		try{
			for (String id : ids){
				booksout out=mapper.findById(Integer.valueOf(id));
				out.setApprovaldate(Utils.date());
				out.setMdtm(Utils.date());
				out.setApprover(name);
				out.setStatus(3);
	    		mapper.update(out);
	    		out.setStatus(1);
	    		mapper.setStatus(out);
	    	}
			return true;
        }catch(Exception e){
             e.printStackTrace();
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
             return false;
        }
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean batchTo(String[] ids,String name) {
		try{
			for (String id : ids){
				booksout out=mapper.findById(Integer.valueOf(id));
				out.setApprovaldate(Utils.date());
				out.setApprover(name);
				out.setMdtm(Utils.date());
				out.setStatus(2);
	    		mapper.update(out);
	    		out.setStatus(3);
	    		mapper.setStatus(out);
	    	}
			return true;
        }catch(Exception e){
             e.printStackTrace();
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
             return false;
        }
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean update(booksout role) {
		try{
			// TODO Auto-generated method stub
			mapper.update(role);
			if (role.getStatus()==2) {
				role.setStatus(3);
			}else{
				if (role.getStatus()==3) {
					role.setStatus(1);
				}
			}
			mapper.setStatus(role);
			return true;
		}catch(Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
            return false;
       }
	}

	@Override
	public List<booksout> getAll_record(int status,int oid) {
		System.err.println(status+"  "+oid);
		return mapper.getAll_record(status,oid);
	}
		
}
