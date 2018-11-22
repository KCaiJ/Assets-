package com.assets.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.assets.dao.houseMapper;
import com.assets.dao.houseoutMapper;
import com.assets.model.house;
import com.assets.model.houseout;
import com.assets.model.Organ;
import com.assets.service.houseService;
import com.assets.service.houseoutService;
import com.assets.tool.Utils;
import com.mysql.jdbc.Util;
@Service("houseoutService")
public class houseoutServiceImpl implements houseoutService{
	@Resource
	private houseoutMapper mapper;

	@Override
	public List<houseout> findAll(int oid) {
		// TODO Auto-generated method stub
		return mapper.findAll(oid);
	}

	@Override
	public houseout findById(int id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean batchNo(String[] ids,String name) {
		try{
			for (String id : ids){
				houseout out=mapper.findById(Integer.valueOf(id));
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
				houseout out=mapper.findById(Integer.valueOf(id));
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
	public boolean update(houseout role) {
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
			System.err.println(role.toString());
			mapper.setStatus(role);
			return true;
		}catch(Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
            return false;
       }
	}
		
}
