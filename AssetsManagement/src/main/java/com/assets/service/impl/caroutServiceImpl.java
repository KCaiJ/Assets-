package com.assets.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.assets.dao.carMapper;
import com.assets.dao.caroutMapper;
import com.assets.model.car;
import com.assets.model.carout;
import com.assets.model.cartype;
import com.assets.model.Organ;
import com.assets.service.carService;
import com.assets.service.caroutService;
import com.assets.tool.Utils;
import com.mysql.jdbc.Util;
@Service("caroutService")
public class caroutServiceImpl implements caroutService{
	@Resource
	private caroutMapper mapper;

	@Override
	public List<carout> findAll(int oid) {
		// TODO Auto-generated method stub
		return mapper.findAll(oid);
	}

	@Override
	public carout findById(int id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public List<carout> select_code(carout role) {
		// TODO Auto-generated method stub
		return mapper.select_code(role);
	}
	
	

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean batchNo(String[] ids,String name) {
		try{
			for (String id : ids){
				carout out=mapper.findById(Integer.valueOf(id));
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
				carout out=mapper.findById(Integer.valueOf(id));
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
	public boolean update(carout role) {
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
	public List<carout> getAll_record(int status,int oid) {
		System.err.println(status+"  "+oid);
		return mapper.getAll_record(status,oid);
	}
		
}
