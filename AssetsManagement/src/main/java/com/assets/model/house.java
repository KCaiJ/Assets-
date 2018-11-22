package com.assets.model;

public class house {
	private int did;
	private int oid ;
	private String housecode;
	private String  houselaction;
	private String housearea;
	private int Status;
	private String creator;
	private String crtm;
	private String mdtm;
	@Override
	public String toString() {
		return "house [did=" + did + ", oid=" + oid + ", housecode=" + housecode + ", houselaction=" + houselaction
				+ ", houseArea=" + housearea + ", Status=" + Status + ", creator=" + creator + ", crtm=" + crtm
				+ ", mdtm=" + mdtm + "]";
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	
	public String gethousecode() {
		return housecode;
	}
	public void sethousecode(String housecode) {
		this.housecode = housecode;
	}
	public String gethouselaction() {
		return houselaction;
	}
	public void sethouselaction(String houselaction) {
		this.houselaction = houselaction;
	}
	
	public String gethousearea() {
		return housearea;
	}
	public void sethousearea(String housearea) {
		this.housearea = housearea;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
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
