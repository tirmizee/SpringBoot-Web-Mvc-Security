package com.tirmizee.core.domain;

import java.io.Serializable;
import java.sql.Date;

public class UserDetail implements Serializable{

	private static final long serialVersionUID = -4363276001483060389L;
	
	private String username;
	private String password;
	private String lastName;
	private String firstName;
	private String profileImage;
	private Integer roleId;
	private String roleCode;
	private String roleName;
	private Date credentialsexpiredDate;
	private Date accountExpiredDate;
	private boolean credentialsnonexpired;
	private boolean accountnonexpired;
	private boolean accountnonlocked;	
	private boolean enabled;
	private boolean firstLogin;
	private Integer maxSession;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public boolean getFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(boolean isFirstLogin) {
		this.firstLogin = isFirstLogin;
	}
	public Date getCredentialsexpiredDate() {
		return credentialsexpiredDate;
	}
	public void setCredentialsexpiredDate(Date credentialsexpiredDate) {
		this.credentialsexpiredDate = credentialsexpiredDate;
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
	public Integer getMaxSession() {
		return maxSession;
	}
	public void setMaxSession(Integer maxSession) {
		this.maxSession = maxSession;
	}
	public Date getAccountExpiredDate() {
		return accountExpiredDate;
	}
	public void setAccountExpiredDate(Date accountExpiredDate) {
		this.accountExpiredDate = accountExpiredDate;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	@Override
	public String toString() {
		return "UserDetail [username=" + username + ", password=" + password + ", lastName=" + lastName + ", firstName="
				+ firstName + ", credentialsnonexpired=" + credentialsnonexpired + ", accountnonexpired="
				+ accountnonexpired + ", accountnonlocked=" + accountnonlocked + ", enabled=" + enabled
				+ ", isFirstLogin=" + firstLogin + "]";
	}
	
}
