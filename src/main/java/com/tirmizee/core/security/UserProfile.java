package com.tirmizee.core.security;

import java.sql.Timestamp;

public class UserProfile extends UserDetailsImpl {
	
	private static final long serialVersionUID = 196667297093501169L;
	
	private String fistName;
	private String lastName;
	private String roleName;
	private String accessIp;
	private boolean initialLogin;
	private Timestamp credentialsExpiredDate;
	
	public UserProfile(Builder builder) {
		super(builder);
		this.fistName = builder.fistName;
		this.lastName = builder.lastName;
		this.roleName = builder.roleName;
		this.accessIp = builder.accessIp;
		this.initialLogin = builder.initialLogin;
		this.credentialsExpiredDate = builder.credentialsExpiredDate;
	}
	
	public String getFistName() {
		return fistName;
	}
	
	public void setFistName(String fistName) {
		this.fistName = fistName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	public boolean isInitialLogin() {
		return initialLogin;
	}
	
	public void setInitialLogin(boolean initialLogin) {
		this.initialLogin = initialLogin;
	}
	
	public Timestamp getCredentialsExpiredDate() {
		return credentialsExpiredDate;
	}
	
	public void setCredentialsExpiredDate(Timestamp credentialsExpiredDate) {
		this.credentialsExpiredDate = credentialsExpiredDate;
	}
	
	public static class Builder extends UserDetailsImpl.Builder<Builder>{
		
		private String fistName;
		private String lastName;
		private String roleName;
		private String accessIp;
		private boolean initialLogin;
		private Timestamp credentialsExpiredDate;
		
		public Builder(){}
		
		public Builder fistName(String fistName){
			this.fistName = fistName;
			return this;
		}
		
		public Builder lastName(String lastName){
			this.lastName = lastName;
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
		
		public Builder initialLogin(boolean initialLogin){
			this.initialLogin = initialLogin;
			return this;
		}
		
		public Builder credentialsExpiredDate(Timestamp credentialsExpiredDate){
			this.credentialsExpiredDate = credentialsExpiredDate;
			return this;
		}
		
		public UserProfile build(){
			return new  UserProfile(this);
		}

	}

}
