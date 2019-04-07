package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

public class UserDetailUpdateDTO implements Serializable {

	private static final long serialVersionUID = -7358757628634792977L;

	private Long userId;
	private String username;
	private java.sql.Date credentialsexpiredDate;
	private boolean credentialsnonexpired;
	private boolean accountnonexpired;
	private boolean accountnonlocked;	
	private boolean enabled;
	private boolean firstLogin;
	private String lastName;
	private String firstName;
	private String email;
	private Integer roleId;
	private String roleName;
	private String roleCode;
	
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
	
}
