package com.assets.model;

public class land {
	private int did;
	private int oid ;
	private String landcode;
	private String  landlaction;
	private String landarea;
	private int Status;
	private String creator;
	private String crtm;
	private String mdtm;
	@Override
	public String toString() {
		return "land [did=" + did + ", oid=" + oid + ", Landcode=" + landcode + ", Landlaction=" + landlaction
				+ ", LandArea=" + landarea + ", Status=" + Status + ", creator=" + creator + ", crtm=" + crtm
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
	
	public String getLandcode() {
		return landcode;
	}
	public void setLandcode(String landcode) {
		this.landcode = landcode;
	}
	public String getLandlaction() {
		return landlaction;
	}
	public void setLandlaction(String landlaction) {
		this.landlaction = landlaction;
	}
	
	public String getLandarea() {
		return landarea;
	}
	public void setLandarea(String landarea) {
		this.landarea = landarea;
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
