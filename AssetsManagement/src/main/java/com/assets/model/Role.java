package com.assets.model;

import javax.persistence.Id;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;

public class Role {
	private String jurisdiction;
	private int roleid;
	private String  name;
	private String remarks;
	private String crtm;
	private String mdtm;
	private String[] power;

	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public void setPower(String[] power) {
		this.power = power;
	}
	public String[] getPower() {
		return power;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "Role [roleid=" + roleid + ", remarks=" + remarks + ",  name=" + name+ ", crtm=" + crtm + ", mdtm=" + mdtm + ",power="+jurisdiction+"]";	
	}
}
