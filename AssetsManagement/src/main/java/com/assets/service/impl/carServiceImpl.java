package com.assets.service.impl;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.assets.dao.carMapper;
import com.assets.model.car;
import com.assets.model.carout;
import com.assets.model.cartype;
import com.assets.model.ExcelBean;
import com.assets.model.Organ;
import com.assets.service.carService;
import com.assets.tool.ExcelUtil;
import com.assets.tool.Utils;
@Service("carService")
public class carServiceImpl implements carService{
	@Resource
	private carMapper mapper;

	@Override
	public void save(car role) {
		// TODO Auto-generated method stub
		mapper.save(role);
	}

	@Override
	public boolean update(car role) {
		// TODO Auto-generated method stub
		return mapper.update(role);
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return mapper.delete(id);
	}

	@Override
	public car findById(int id) {
		return mapper.findById(id);
	}

	@Override
	public List<car> findAll(int oid) {
		return mapper.findAll(oid);
	}

	@Override
	public List<car> select_brand(car role) {
		// TODO Auto-generated method stub
		return mapper.select_core(role);
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
	public List<Organ> getOrgan() {
		// TODO Auto-generated method stub
		return mapper.findOrganALL();
	}

	@Override
	public List<cartype> getcarType() {
		// TODO Auto-generated method stub
		return mapper.findAllDtype();
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void savaout(carout carout) {
		try{
			mapper.savaout(carout);
			car d=mapper.findById(carout.getDid());
			d.setStatus(2);
			mapper.update(d);
        }catch(Exception e){
             e.printStackTrace();
             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//�ֶ��ع�����
        }
		
	}
	
	//��������
	@Transactional(rollbackFor = Exception.class)
	public boolean importExcelInfo(InputStream in, MultipartFile file, String salaryDate,int adminId,String user) {
	    List<List<Object>> listob;
		try {
			listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
			List<car> salaryList = new ArrayList<car>();
		    //����listob���ݣ������ݷŵ�List��
		    for (int i = 0; i < listob.size(); i++) {
		        List<Object> ob = listob.get(i);
		        car salarymanage = new car();
		        //���ñ��
		       // salarymanage.setSerial(SerialUtil.salarySerial());
		        //ͨ������ʵ�ְ�ÿһ�з�װ��һ��model�У��ٰ����е�model��List����װ��
		       // salarymanage.setAdminId(adminId);
		        salarymanage.setDtid(Integer.valueOf(ob.get(1).toString()));
		        salarymanage.setOid(adminId);
		        salarymanage.setCode("Q"+Utils.getCurrentYear()+Utils.generateShortUuid());
		        salarymanage.setResidual(String.valueOf(ob.get(7)));
		        salarymanage.setOriginal(String.valueOf(ob.get(8)));
		        salarymanage.setStatus(1);
		        salarymanage.setProddate(String.valueOf(ob.get(10)));
		        salarymanage.setCreator(user);
		        salarymanage.setCreatetime(String.valueOf(ob.get(12)));
		        salarymanage.setBuyer(String.valueOf(ob.get(13)));
		        salarymanage.setBugdate(String.valueOf(ob.get(14)));
		        salarymanage.setSno(String.valueOf(ob.get(15)));
		        salarymanage.setCrtm(String.valueOf(ob.get(16)));
		        salarymanage.setMdtm(salaryDate);
		        salaryList.add(salarymanage);
		        System.err.println(salarymanage.toString());
		        mapper.save(salarymanage);
		    }
		    return true;
		    //��������
		    //
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//�ֶ��ع�����
			 return false;
		}
	    
	}
	
	
	//��������
	
	public XSSFWorkbook exportExcelInfo(int oid,String salaryDate) {
	    //����������ѯ���ݣ�������װ�ص�һ��list��
	    List<car> list = mapper.findAll(oid);
	    List<ExcelBean> excel=new ArrayList<>();
	    Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
	    XSSFWorkbook xssfWorkbook=null;
	    //���ñ�����
	    excel.add(new ExcelBean("���","did",0));
	    excel.add(new ExcelBean("�豸����","dtid",0));
	    excel.add(new ExcelBean("��������","oid",0));
	    excel.add(new ExcelBean("��ˮ��","code",0));
	    excel.add(new ExcelBean("Ʒ��","brand",0));
	    excel.add(new ExcelBean("���ʱ��","intlcode",0));
	    excel.add(new ExcelBean("�ͺ�","model",0));
	    excel.add(new ExcelBean("����ֵ","residual",0));
	    excel.add(new ExcelBean("ԭֵ","original",0));
	    excel.add(new ExcelBean("״̬","status",0));
	    excel.add(new ExcelBean("��������","proddate",0));
	    excel.add(new ExcelBean("�Ǽ���","creator",0));
	    excel.add(new ExcelBean("�Ǽ�ʱ��","createtime",0));
	    excel.add(new ExcelBean("������","buyer",0));
	    excel.add(new ExcelBean("��������","bugdate",0));
	    excel.add(new ExcelBean("���к�","sno",0));
	    excel.add(new ExcelBean("����ʱ��","crtm",0));
	    excel.add(new ExcelBean("�޸�ʱ��","mdtm",0));
	    map.put(0, excel);
	    String sheetName = salaryDate ;
	    //����ExcelUtil�ķ���
	    try {
			xssfWorkbook = ExcelUtil.createExcelFile(car.class, list, map, sheetName);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return xssfWorkbook;
	}
	
}
