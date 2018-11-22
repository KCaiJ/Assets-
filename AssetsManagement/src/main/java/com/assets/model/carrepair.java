package com.assets.model;

public class carrepair {
	private int id,did,status;
	private String damager,damagedate,damageremarks,repairdate,repairer,repairremarks,crtm,mdtm;
	private String code_S,name;
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
	public String getDamager() {
		return damager;
	}
	public void setDamager(String damager) {
		this.damager = damager;
	}
	public String getDamagedate() {
		return damagedate;
	}
	public void setDamagedate(String damagedate) {
		this.damagedate = damagedate;
	}
	public String getDamageremarks() {
		return damageremarks;
	}
	public void setDamageremarks(String damageremarks) {
		this.damageremarks = damageremarks;
	}
	public String getRepairdate() {
		return repairdate;
	}
	public void setRepairdate(String repairdate) {
		this.repairdate = repairdate;
	}
	public String getRepairer() {
		return repairer;
	}
	public void setRepairer(String repairer) {
		this.repairer = repairer;
	}
	public String getRepairremarks() {
		return repairremarks;
	}
	public void setRepairremarks(String repairremarks) {
		this.repairremarks = repairremarks;
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
	public String getCode_S() {
		return code_S;
	}
	public void setCode_S(String code_S) {
		this.code_S = code_S;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "carrepair [id=" + id + ", did=" + did + ", status=" + status + ", damager=" + damager
				+ ", damagedate=" + damagedate + ", damageremarks=" + damageremarks + ", repairdate=" + repairdate
				+ ", repairer=" + repairer + ", repairremarks=" + repairremarks + ", crtm=" + crtm + ", mdtm=" + mdtm
				+ ", code_S=" + code_S + ", name=" + name + "]";
	}
	
}
