package com.assets.model;

public class landout {
	private int id,did,status,oid;
	private String outter,applyer,remarks,approver,approvalremarks,approvaldate,crtm,mdtm,landarea,landlaction,landcode;
	
	
	
	
	public String getLandarea() {
		return landarea;
	}
	public void setLandarea(String landarea) {
		this.landarea = landarea;
	}
	public String getLandlaction() {
		return landlaction;
	}
	public void setLandlaction(String landlaction) {
		this.landlaction = landlaction;
	}
	public String getLandcode() {
		return landcode;
	}
	public void setLandcode(String landcode) {
		this.landcode = landcode;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOutter() {
		return outter;
	}
	public void setOutter(String outter) {
		this.outter = outter;
	}
	public String getApplyer() {
		return applyer;
	}
	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getApprovalremarks() {
		return approvalremarks;
	}
	public void setApprovalremarks(String approvalremarks) {
		this.approvalremarks = approvalremarks;
	}
	public String getApprovaldate() {
		return approvaldate;
	}
	public void setApprovaldate(String approvaldate) {
		this.approvaldate = approvaldate;
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
		return "landout [id=" + id + ", did=" + did + ", status=" + status + ", outter=" + outter + ", applyer="
				+ applyer + ", remarks=" + remarks + ", approver=" + approver + ", approvalremarks=" + approvalremarks
				+ ", approvaldate=" + approvaldate + ", crtm=" + crtm + ", mdtm=" + mdtm + "]";
	}
	

}
