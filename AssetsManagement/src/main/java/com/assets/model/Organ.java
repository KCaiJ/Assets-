package com.assets.model;

public class Organ {
	private int oid;
	private String  name;
	private String remarks;
	private String crtm;
	private String mdtm;

	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getOid() {
		return oid;
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
		return  "Organ [oid=" + oid + ", remarks=" + remarks + ",  name=" + name+ ", crtm=" + crtm + ", mdtm=" + mdtm + "]";	
	}
}
