package com.assets.model;

public class Device {
	private int did;
	private int dtid;
	private int oid ;
	private String code;
	private String  residual;
	private String original;
	private int Status;
	private String proddate;
	private String creator;
	private String createtime;
	private String buyer;
	private String bugdate;
	private String sno;
	private String crtm;
	@Override
	public String toString() {
		return "Device [did=" + did + ", dtid=" + dtid + ", oid=" + oid + ", code=" + code + ", residual=" + residual
				+ ", original=" + original + ", Status=" + Status + ", proddate=" + proddate + ", creator=" + creator
				+ ", createtime=" + createtime + ", buyer=" + buyer + ", bugdate=" + bugdate + ", sno=" + sno
				+ ", crtm=" + crtm + ", mdtm=" + mdtm + ", brand=" + brand + ", intlcode=" + intlcode + ", model="
				+ model + "]";
	}
	private String mdtm;
	private String brand ;
	private String intlcode;
	private String model;
	public int getDid() {
		return did;
	}
	public int getDtid() {
		return dtid;
	}
	public int getOid() {
		return oid;
	}
	public String getCode() {
		return code;
	}
	public String getResidual() {
		return residual;
	}
	public String getOriginal() {
		return original;
	}
	public int getStatus() {
		return Status;
	}
	public String getProddate() {
		return proddate;
	}
	public String getCreator() {
		return creator;
	}
	public String getCreatetime() {
		return createtime;
	}
	public String getBuyer() {
		return buyer;
	}
	public String getBugdate() {
		return bugdate;
	}
	public String getSno() {
		return sno;
	}
	public String getCrtm() {
		return crtm;
	}
	public String getMdtm() {
		return mdtm;
	}
	public String getBrand() {
		return brand;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public void setDtid(int dtid) {
		this.dtid = dtid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setResidual(String residual) {
		this.residual = residual;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public void setProddate(String proddate) {
		this.proddate = proddate;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public void setBugdate(String bugdate) {
		this.bugdate = bugdate;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public void setCrtm(String crtm) {
		this.crtm = crtm;
	}
	public void setMdtm(String mdtm) {
		this.mdtm = mdtm;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setIntlcode(String intlcode) {
		this.intlcode = intlcode;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getIntlcode() {
		return intlcode;
	}
	public String getModel() {
		return model;
	}
	
	
}
