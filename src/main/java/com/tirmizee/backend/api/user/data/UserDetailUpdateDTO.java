package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDetailUpdateDTO implements Serializable {

	private static final long serialVersionUID = -7358757628634792977L;

	private Long userId;
	private String username;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private java.sql.Date credentialsexpiredDate;
	
	private boolean credentialsnonexpired;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private java.sql.Date accountExpiredDate;
	
	private Integer maxSession;
	private boolean accountnonexpired;
	private boolean accountnonlocked;	
	private boolean enabled;
	private String citizenId;
	private boolean firstLogin;
	private String lastName;
	private String firstName;
	private String email;
	private String tel;
	private Integer roleId;
	private String roleName;
	private String roleCode;
	private Integer provinceId;
	private Integer districtId;
	private Integer subdistrictId;
	private String provinceNameTh;
	private String districtNameTh;
	private String subdistrictNameTh;
	private String subDistrictCode;
	private String zipcode;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public java.sql.Date getCredentialsexpiredDate() {
		return credentialsexpiredDate;
	}
	public void setCredentialsexpiredDate(java.sql.Date credentialsexpiredDate) {
		this.credentialsexpiredDate = credentialsexpiredDate;
	}
	public boolean isCredentialsnonexpired() {
		return credentialsnonexpired;
	}
	public void setCredentialsnonexpired(boolean credentialsnonexpired) {
		this.credentialsnonexpired = credentialsnonexpired;
	}
	public boolean isAccountnonexpired() {
		return accountnonexpired;
	}
	public void setAccountnonexpired(boolean accountnonexpired) {
		this.accountnonexpired = accountnonexpired;
	}
	public boolean isAccountnonlocked() {
		return accountnonlocked;
	}
	public void setAccountnonlocked(boolean accountnonlocked) {
		this.accountnonlocked = accountnonlocked;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getCitizenId() {
		return citizenId;
	}
	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSubDistrictCode() {
		return subDistrictCode;
	}
	public void setSubDistrictCode(String subDistrictCode) {
		this.subDistrictCode = subDistrictCode;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public Integer getSubdistrictId() {
		return subdistrictId;
	}
	public void setSubdistrictId(Integer subdistrictId) {
		this.subdistrictId = subdistrictId;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getProvinceNameTh() {
		return provinceNameTh;
	}
	public void setProvinceNameTh(String provinceNameTh) {
		this.provinceNameTh = provinceNameTh;
	}
	public String getDistrictNameTh() {
		return districtNameTh;
	}
	public void setDistrictNameTh(String districtNameTh) {
		this.districtNameTh = districtNameTh;
	}
	public String getSubdistrictNameTh() {
		return subdistrictNameTh;
	}
	public void setSubdistrictNameTh(String subdistrictNameTh) {
		this.subdistrictNameTh = subdistrictNameTh;
	}
	public java.sql.Date getAccountExpiredDate() {
		return accountExpiredDate;
	}
	public void setAccountExpiredDate(java.sql.Date accountExpiredDate) {
		this.accountExpiredDate = accountExpiredDate;
	}
	public Integer getMaxSession() {
		return maxSession;
	}
	public void setMaxSession(Integer maxSession) {
		this.maxSession = maxSession;
	}
	@Override
	public String toString() {
		return "UserDetailUpdateDTO [userId=" + userId + ", username=" + username + ", credentialsexpiredDate="
				+ credentialsexpiredDate + ", credentialsnonexpired=" + credentialsnonexpired + ", accountExpiredDate="
				+ accountExpiredDate + ", maxSession=" + maxSession + ", accountnonexpired=" + accountnonexpired
				+ ", accountnonlocked=" + accountnonlocked + ", enabled=" + enabled + ", citizenId=" + citizenId
				+ ", firstLogin=" + firstLogin + ", lastName=" + lastName + ", firstName=" + firstName + ", email="
				+ email + ", tel=" + tel + ", roleId=" + roleId + ", roleName=" + roleName + ", roleCode=" + roleCode
				+ ", provinceId=" + provinceId + ", districtId=" + districtId + ", subdistrictId=" + subdistrictId
				+ ", provinceNameTh=" + provinceNameTh + ", districtNameTh=" + districtNameTh + ", subdistrictNameTh="
				+ subdistrictNameTh + ", subDistrictCode=" + subDistrictCode + ", zipcode=" + zipcode + "]";
	}
	
}
