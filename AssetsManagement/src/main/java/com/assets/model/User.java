package com.assets.model;

import java.util.Date;

public class User implements Cloneable{    
    private String rolename;
    private String organname;
	private int id;
	/**
	 * 角色id
	 */
	private int roleid;
	/**
	 * 组织机构id
	 */
	private int oid;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 性别 1-男、2-女
	 */
	private int sex;
	/**
	 * 生日
	 */
	private String birth;
	/**
	 * 手机
	 */
	private String phone;
	/**
	 * 邮件
	 */
	private String email;
	/**
	 * 登录时间
	 */
	private String logintime;
	/**
	 * 登录ip
	 */
	private String loginip;
	/**
	 * 创建时间
	 */
	private String crtm;
	/**
	 * 修改时间
	 */
	private String mdtm;
	
	
	public String getBirth() {
		return birth;
	}
	public String getCrtm() {
		return crtm;
	}

	public String getEmail() {
		return email;
	}
	public int getId() {
		return id;
	}
	public String getLoginip() {
		return loginip;
	}
	public String getLogintime() {
		return logintime;
	}
	public String getMdtm() {
		return mdtm;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public String getPwd() {
		return pwd;
	}
	public int getSex() {
		return sex;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public void setCrtm(String crtm) {
		this.crtm = crtm;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public void setMdtm(String mdtm) {
		this.mdtm = mdtm;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getOid() {
		return oid;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getOrganname() {
		return organname;
	}
	public void setOrganname(String organname) {
		this.organname = organname;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", rid=" + roleid + ", deid=" + oid + ", name=" + name + ", pwd=" + pwd + ", sex=" + sex
				+ ", birth=" + birth + ", phone=" + phone + ", email=" + email + ", logintime=" + logintime
				+ ", loginip=" + loginip + ", crtm=" + crtm + ", mdtm=" + mdtm + "]";
		
	}
	   @Override  
	    public Object clone() {  
	        User stu = null;  
	        try{  
	            stu = (User)super.clone();  
	        }catch(CloneNotSupportedException e) {  
	            e.printStackTrace();  
	        }  
	        return stu;  
	    } 
}    