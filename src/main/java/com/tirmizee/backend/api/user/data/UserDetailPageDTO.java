package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

import com.tirmizee.core.datatable.SortColumn;
import com.tirmizee.core.repository.BankBranchRepository;
import com.tirmizee.core.repository.ProfileRepository;
import com.tirmizee.core.repository.RoleRepository;
import com.tirmizee.core.repository.UserRepository;

import lombok.Data;

@Data
public class UserDetailPageDTO implements Serializable {

	private static final long serialVersionUID = 1831017480680058509L;

	@SortColumn(UserRepository.COL_USER_ID)
	private Long userId;
	
	@SortColumn(UserRepository.COL_USERNAME)
	private String username;
	
	@SortColumn(UserRepository.COL_CREDENTIALS_EXPIRED_DATE)
	private java.sql.Date credentialsExpiredDate;
	
	@SortColumn(UserRepository.COL_CREDENTIALS_NON_EXPIRED)
	private boolean credentialsNonExpired;
	
	@SortColumn(UserRepository.COL_ACCOUNT_NON_EXPIRED)
	private boolean accountNonExpired;
	
	@SortColumn(UserRepository.COL_ACCOUNT_NON_LOCKED)
	private boolean accountNonLocked;	
	
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
	
	@SortColumn(BankBranchRepository.COL_BRANCH_NAME)
	private String branchName;
	
	@SortColumn(RoleRepository.COL_ROLE_NAME)
	private String roleName;
	
	private String roleCode;
	
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public boolean isFirstLogin() {
		return firstLogin;
	}
	
}
