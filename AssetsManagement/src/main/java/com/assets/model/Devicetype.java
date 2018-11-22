package com.assets.model;

import com.alibaba.druid.stat.TableStat.Name;

public class Devicetype {
	private int dtid;
	private String name;
	private String brand;
	private String intlcode;
	private String model;
	private String remarks;
	private float asl;
	private float anrv;
	private String crtm;
	private String mdtm;
	public int getDtid() {
		return dtid;
	}
	public void setDtid(int dtid) {
		this.dtid = dtid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getIntlcode() {
		return intlcode;
	}
	public void setIntlcode(String intlcode) {
		this.intlcode = intlcode;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public float getAsl() {
		return asl;
	}
	public void setAsl(float asl) {
		this.asl = asl;
	}
	public float getAnrv() {
		return anrv;
	}
	public void setAnrv(float anrv) {
		this.anrv = anrv;
	}
	public String getCrtm() {
		return crtm;
	}
	public void setCrtm(String crtm) {
		this.crtm = crtm;
	}
	public String getMdtm() {
		return mdtm;
	}
	public void setMdtm(String mdtm) {
		this.mdtm = mdtm;
	}
	

}
