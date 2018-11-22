package com.assets.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.assets.dao.DeviceMapper;
import com.assets.dao.DeviceoutMapper;
import com.assets.model.Device;
import com.assets.model.Deviceout;
import com.assets.model.Devicetype;
import com.assets.model.Organ;
import com.assets.service.DeviceService;
import com.assets.service.DeviceoutService;
import com.assets.tool.Utils;
import com.mysql.jdbc.Util;
@Service("DeviceoutService")
public class DeviceoutServiceImpl implements DeviceoutService{
	@Resource
	private DeviceoutMapper mapper;

	@Override
	public List<Deviceout> findAll(int oid) {
		// TODO Auto-generated method stub
		return mapper.findAll(oid);
	}

	@Override
	public Deviceout findById(int id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public List<Deviceout> select_code(Deviceout role) {
		// TODO Auto-generated method stub
		return mapper.select_code(role);
	}
	
	

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean batchNo(String[] ids,String name) {
		try{
			for (String id : ids){
				Deviceout out=mapper.findById(Integer.valueOf(id));
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
				Deviceout out=mapper.findById(Integer.valueOf(id));
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
	public boolean update(Deviceout role) {
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
	public List<Deviceout> getAll_record(int status,int oid) {
		System.err.println(status+"  "+oid);
		return mapper.getAll_record(status,oid);
	}
		
}
