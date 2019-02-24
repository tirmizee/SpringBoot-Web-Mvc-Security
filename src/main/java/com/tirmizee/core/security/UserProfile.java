package com.tirmizee.core.security;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.security.core.userdetails.UserDetails;

public class UserProfile extends UserDetailsImpl {
	
	private static final long serialVersionUID = 196667297093501169L;
	
	private String fistName;
	private String lastName;
	private String roleName;
	private String accessIp;
	private boolean isFirstLogin;
	private Date credentialsExpiredDate;
	
	public UserProfile(Builder builder) {
		super(builder);
		this.fistName = builder.fistName;
		this.lastName = builder.lastName;
		this.roleName = builder.roleName;
		this.accessIp = builder.accessIp;
		this.isFirstLogin = builder.isFirstLogin;
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
		
		private String fistName;
		private String lastName;
		private String roleName;
		private String accessIp;
		private boolean isFirstLogin;
		private Date credentialsExpiredDate;
		
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
		
		public Builder isFirstLogin(boolean isFirstLogin){
			this.isFirstLogin = isFirstLogin;
			return this;
		}
		
		public Builder credentialsExpiredDate(Date credentialsExpiredDate){
			this.credentialsExpiredDate = credentialsExpiredDate;
			return this;
		}
		
		public UserProfile build(){
			return new  UserProfile(this);
		}

	}

}
