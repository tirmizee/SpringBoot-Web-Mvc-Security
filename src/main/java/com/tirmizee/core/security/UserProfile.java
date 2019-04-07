package com.tirmizee.core.security;

import java.sql.Date;

import org.springframework.security.core.userdetails.UserDetails;

public class UserProfile extends UserDetailsImpl {
	
	private static final long serialVersionUID = 196667297093501169L;
	
	private String firstName;
	private String lastName;
	private String roleCode;
	private String roleName;
	private String accessIp;
	private boolean isFirstLogin;
	private Integer maxSession;
	private Date credentialsExpiredDate;
	private Date accountExpiredDate;
	private java.util.Date createDate;
	
	public UserProfile(Builder builder) {
		super(builder);
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.roleCode = builder.roleCode;
		this.roleName = builder.roleName;
		this.accessIp = builder.accessIp;
		this.createDate = builder.createDate;
		this.isFirstLogin = builder.isFirstLogin;
		this.maxSession = builder.maxSession;
		this.credentialsExpiredDate = builder.credentialsExpiredDate;
		this.accountExpiredDate = builder.accountExpiredDate;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getAccessIp() {
		return accessIp;
	}
	
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}
	
	public boolean isFirstLogin() {
		return isFirstLogin;
	}

	public void setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public Date getCredentialsExpiredDate() {
		return credentialsExpiredDate;
	}
	
	public void setCredentialsExpiredDate(Date credentialsExpiredDate) {
		this.credentialsExpiredDate = credentialsExpiredDate;
	}
	
	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
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

	@Override
    public boolean equals(Object obj) {
		if (obj instanceof UserDetails) {
			return getUsername().equals(((UserDetails) obj).getUsername());
		}
		return false;
    }

    @Override
    public int hashCode() {
        return getUsername() != null ? getUsername().hashCode() : 0;
    }
	
	public static class Builder extends UserDetailsImpl.Builder<Builder>{
		
		private String firstName;
		private String lastName;
		private String roleCode;
		private String roleName;
		private String accessIp;
		private Integer maxSession;
		private java.util.Date createDate;
		private boolean isFirstLogin;
		private Date credentialsExpiredDate;
		private Date accountExpiredDate;
		
		public Builder(){}
		
		public Builder firstName(String firstName){
			this.firstName = firstName;
			return this;
		}
		
		public Builder lastName(String lastName){
			this.lastName = lastName;
			return this;
		}
		
		public Builder roleCode(String roleCode){
			this.roleCode = roleCode;
			return this;
		}
		
		public Builder roleName(String roleName){
			this.roleName = roleName;
			return this;
		}
		
		public Builder accessIp(String accessIp){
			this.accessIp = accessIp;
			return this;
		}
		
		public Builder maxSession(Integer maxSession){
			this.maxSession = maxSession;
			return this;
		}
		
		public Builder createDate(java.util.Date createDate){
			this.createDate = createDate;
			return this;
		}
		
		public Builder isFirstLogin(boolean isFirstLogin){
			this.isFirstLogin = isFirstLogin;
			return this;
		}
		
		public Builder credentialsExpiredDate(Date credentialsExpiredDate){
			this.credentialsExpiredDate = credentialsExpiredDate;
			return this;
		}
		
		public Builder accountExpiredDate(Date accountExpiredDate){
			this.accountExpiredDate = accountExpiredDate;
			return this;
		}
		
		public UserProfile build(){
			return new  UserProfile(this);
		}

	}

}
