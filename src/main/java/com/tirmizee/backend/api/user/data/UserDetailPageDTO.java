package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

import com.tirmizee.core.datatable.SortColumn;
import com.tirmizee.core.repository.ProfileRepository;
import com.tirmizee.core.repository.RoleRepository;
import com.tirmizee.core.repository.UserRepository;

public class UserDetailPageDTO implements Serializable {

	private static final long serialVersionUID = 1831017480680058509L;

	@SortColumn(UserRepository.COL_USER_ID)
	private Long userId;
	
	@SortColumn(UserRepository.COL_USERNAME)
	private String username;
	
	@SortColumn(UserRepository.COL_CREDENTIALSEXPIRED_DATE)
	private java.sql.Date credentialsexpiredDate;
	
	@SortColumn(UserRepository.COL_CREDENTIALSNONEXPIRED)
	private boolean credentialsnonexpired;
	
	@SortColumn(UserRepository.COL_ACCOUNTNONEXPIRED)
	private boolean accountnonexpired;
	
	@SortColumn(UserRepository.COL_ACCOUNTNONLOCKED)
	private boolean accountnonlocked;	
	
	@SortColumn(UserRepository.COL_ENABLED)
	private boolean enabled;
	
	@SortColumn(UserRepository.COL_FIRST_LOGIN)
	private boolean firstLogin;
	
	private Integer profileId;
	
	private String profileImage;
	
	@SortColumn(ProfileRepository.COL_LAST_NAME)
	private String lastName;
	
	@SortColumn(ProfileRepository.COL_FIRST_NAME)
	private String firstName;
	
	@SortColumn(ProfileRepository.COL_EMAIL)
	private String email;
	
	@SortColumn(ProfileRepository.COL_TEL)
	private String tel;
	
	private Integer roleId;
	
	@SortColumn(RoleRepository.COL_ROLE_NAME)
	private String roleName;
	
	private String roleCode;
	
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getProfileId() {
		return profileId;
	}
	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
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
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
