package com.tirmizee.backend.service;

import static com.tirmizee.core.constant.Constant.AppSetting.PASSWORD_CHANGE_DAY;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.tirmizee.backend.api.user.data.ReqPasswordDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordExpriedDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordResetTokenDTO;
import com.tirmizee.backend.api.user.data.ReqUpdateStatusDTO;
import com.tirmizee.backend.api.user.data.UserDetailCriteriaDTO;
import com.tirmizee.backend.api.user.data.UserDetailPageDTO;
import com.tirmizee.backend.api.user.data.UserDetailUpdateDTO;
import com.tirmizee.backend.dao.ForgotPasswordDao;
import com.tirmizee.backend.dao.LogPasswordDao;
import com.tirmizee.backend.dao.ProfileDao;
import com.tirmizee.backend.dao.UserDao;
import com.tirmizee.backend.service.data.ForgotPasswordModel;
import com.tirmizee.core.component.PageMapper;
import com.tirmizee.core.config.security.UserProfile;
import com.tirmizee.core.constant.MessageCode;
import com.tirmizee.core.constant.PermissionCode;
import com.tirmizee.core.datatable.PageRequestHelper;
import com.tirmizee.core.datatable.RequestTable;
import com.tirmizee.core.datatable.ResponseTable;
import com.tirmizee.core.domain.ForgotPassword;
import com.tirmizee.core.domain.LogPassword;
import com.tirmizee.core.domain.Profile;
import com.tirmizee.core.domain.User;
import com.tirmizee.core.exception.MessageSourceException;
import com.tirmizee.core.exception.MessageException;
import com.tirmizee.core.utilities.DateUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("taskExecutor")
	private TaskExecutor task;
	
	@Autowired 
	private PageMapper mapper;
	
	@Autowired 
	private HttpServletRequest request;
	
	@Autowired 
	private UserDao userDao;
	
	@Autowired 
	private LogPasswordDao logPasswordDao;
	
	@Autowired 
	private ProfileDao profileDao;
	
	@Autowired 
	private ForgotPasswordDao forgotPasswordDao;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired 
	private ForgotPasswordService forgotPasswordService;
	
	@Autowired 
	private LogPasswordService logPasswordService;

	@Autowired
	private AppSettingService appSettingService;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserAttempService userAttempService;
	
	@Override
	@Transactional
	public void changePasswordFirstLogin(String username, ReqPasswordDTO passwordDTO) {
		
		User user = userDao.findByUsername(username);
		
		// VALIDATE THE PASSWORD MUST BE UNIQUE.
		if (passwordEncoder.matches(passwordDTO.getConfirmPassword(), user.getPassword())) {
			throw new MessageSourceException(MessageCode.MSG003);
		}
		
		final String passwordEncode = passwordEncoder.encode(passwordDTO.getConfirmPassword());
		user.setPassword(passwordEncode);
		user.setUpdateDate(DateUtils.now());
		user.setFirstLogin(false);
		userDao.save(user);
		
		task.execute(() -> {
			LogPassword logPassword = new LogPassword();
			logPassword.setUsername(username);
			logPassword.setPassword(passwordEncode);
			logPassword.setCreateDate(DateUtils.now());
			logPasswordDao.save(logPassword);
		});
		
	}
	
	@Override
	@Transactional
	public void changePasswordExpired(String username, ReqPasswordExpriedDTO passwordExpriedDTO) {
		
		User user = userDao.findByUsername(username);
		
		// VALIDATE OLD PASSWORD NOT VALID
		if (!passwordEncoder.matches(passwordExpriedDTO.getOldPassword(), user.getPassword())) {
			throw new MessageSourceException(MessageCode.MSG001);
		}
		
		// VALIDATE NEW PASSWORD MUST NOT BE LIKE OLD PASSWORD
		if (logPasswordService.isPasswordExists(username, passwordExpriedDTO.getNewPasswordConfirm(), 2)) {
			throw new MessageSourceException(MessageCode.MSG002);
		}
		
		int day = Integer.parseInt(appSettingService.getValue(PASSWORD_CHANGE_DAY));
		final String passwordEncode = passwordEncoder.encode(passwordExpriedDTO.getNewPasswordConfirm());
		
		user.setPassword(passwordEncode);
		user.setCredentialsExpiredDate(DateUtils.plusDays(day));
		user.setCredentialsNonExpired(true);
		user.setUpdateDate(DateUtils.now());
		userDao.save(user);
		
		task.execute(() -> {
			LogPassword logPassword = new LogPassword();
			logPassword.setUsername(username);
			logPassword.setPassword(passwordEncode);
			logPassword.setCreateDate(DateUtils.now());
			logPasswordDao.save(logPassword);
		});
		
	}

	@Override
	public ResponseTable<UserDetailPageDTO> dataTableByAuthority(RequestTable<UserDetailCriteriaDTO> requestTable, UserProfile userProfile) {
		
		Set<String> authorities = AuthorityUtils.authorityListToSet(userProfile.getAuthorities());
		if (CollectionUtils.isEmpty(authorities)) {
			throw new MessageSourceException(MessageCode.MSG001);
		}
		
		Pageable pageable = PageRequestHelper.build(requestTable, UserDetailPageDTO.class);
		if(authorities.contains(PermissionCode.P002)) {
			Page<UserDetailPageDTO> page = userDao.findPageAllUserByCriteria(pageable, requestTable.getSerch());
			return new ResponseTable<>(page);
		} 
		
		else if(authorities.contains(PermissionCode.P006)) {
			Page<UserDetailPageDTO> page = userDao.findPageBranchUserByCriteria(pageable, userProfile.getBranchCode(), requestTable.getSerch());
			return new ResponseTable<>(page);
		}
		
		return new ResponseTable<>(userDao.findPageAllUserByCriteria(pageable, requestTable.getSerch()));
	}

	@Override
	public void fourceAccountExpired(String username) {
		User user = userDao.findByUsername(username);
		user.setAccountNonExpired(false);
		user.setUpdateDate(DateUtils.now());
		userDao.save(user);
	}
	
	@Override
	public void fourcePasswordExpired(String username) {
		User user = userDao.findByUsername(username);
		user.setCredentialsNonExpired(false);
		user.setUpdateDate(DateUtils.now());
		userDao.save(user);
	}
	
	@Override
	public long countUses() {
		return userDao.count();
	}

	@Override
	public void forgotPassword(String email) {
		
		User user = userDao.findByEmail(email);
		if (user == null) {
			throw new MessageSourceException(MessageCode.MSG004);
		}
		
		String accessIp = request.getRemoteAddr();
		String token = forgotPasswordService.generateToken();
		String url = forgotPasswordService.createURLResetPassword(user.getId(),token);
		
		ForgotPassword forgotPassword = new ForgotPassword();
		forgotPassword.setUserId(user.getId());
		forgotPassword.setEmail(email);
		forgotPassword.setToken(token);
		forgotPassword.setAccessIp(accessIp);
		forgotPassword.setExpiredDate(DateUtils.nowTimestampPlusMinutes(15));
		forgotPassword.setCreateDate(DateUtils.nowTimestamp());
		forgotPassword.setReset(false);
		forgotPasswordDao.save(forgotPassword);

		task.execute(() -> {
			ForgotPasswordModel passwordModel = mapper.map(forgotPassword, ForgotPasswordModel.class);
			passwordModel.setTitle("Forgot Password");
			passwordModel.setUrl(url);
			passwordModel.setUsername(user.getUsername());
			emailService.sendMailForgotPassword(passwordModel);
		});
		
	}

	@Override
	public void resetPassword(ReqPasswordResetTokenDTO passwordResetTokenDTO) {
		
		Long uid = passwordResetTokenDTO.getUid();
		String token = passwordResetTokenDTO.getToken();
		
		ForgotPassword forgotPassword = forgotPasswordDao.findByUserIdAndToken(uid, token);
		if (forgotPassword == null) {
			throw new MessageSourceException(MessageCode.MSG005);
		}
		
		User user = userDao.findOne(uid);
		user.setAccountLockedDate(null);
		user.setPassword(passwordEncoder.encode(passwordResetTokenDTO.getConfirmPassword()));
		user.setUpdateDate(DateUtils.now());
		userDao.save(user);
		
		forgotPassword.setReset(true);
		forgotPasswordDao.save(forgotPassword);
		
	}

	@Override
	public boolean isPasswordExpired(String username) {
		User user = userDao.findByUsername(username);
		if (user == null) { return false; }
		return DateUtils.nowAfter(user.getCredentialsExpiredDate());
	} 
	
	@Override
	public void updateStatusEnable(ReqUpdateStatusDTO updateEnableDTO) {
		
		String username = updateEnableDTO.getUsername();
		User user = userDao.findByUsername(username);

		if (user == null) {
			throw new MessageSourceException(MessageCode.MSG006, username);
		}
		
		user.setEnabled(updateEnableDTO.isStatus());
		user.setUpdateDate(DateUtils.now());
		userDao.save(user);
	}

	@Override
	public void updateStatusPasswordExpired(ReqUpdateStatusDTO UpdatePasswordExpiredDTO) {
		
		String username = UpdatePasswordExpiredDTO.getUsername();
		User user = userDao.findByUsername(username);

		if (user == null) {
			throw new MessageSourceException(MessageCode.MSG006, username);
		}
		
		user.setCredentialsNonExpired(UpdatePasswordExpiredDTO.isStatus());
		user.setUpdateDate(DateUtils.now());
		userDao.save(user);
	}

	@Override
	public void updateStatusLocked(ReqUpdateStatusDTO updateAccountNonLockedDTO) {
		
		String username = updateAccountNonLockedDTO.getUsername();
		User user = userDao.findByUsername(username);

		if (user == null) {
			throw new MessageSourceException(MessageCode.MSG006,username);
		}
		
		user.setAccountNonLocked(updateAccountNonLockedDTO.isStatus());
		user.setUpdateDate(DateUtils.now());
		userDao.save(user);
		userAttempService.resetLoginAttempt(user.getUsername(), request.getRemoteAddr());
	}

	@Override
	public void updateStatusFirstLogin(ReqUpdateStatusDTO updateFirstLogin) {
		
		String username = updateFirstLogin.getUsername();
		User user = userDao.findByUsername(username);

		if (user == null) {
			throw new MessageSourceException(MessageCode.MSG006, username);
		}
		
		user.setFirstLogin(updateFirstLogin.isStatus());
		user.setUpdateDate(DateUtils.now());
		userDao.save(user);
	}

	@Override
	public void updateStatusAccountExpired(ReqUpdateStatusDTO updateAccountExpired) {
		
		String username = updateAccountExpired.getUsername();
		User user = userDao.findByUsername(username);

		if (user == null) {
			throw new MessageSourceException(MessageCode.MSG006, username);
		}
		
		user.setAccountNonExpired(updateAccountExpired.isStatus());
		user.setUpdateDate(DateUtils.now());
		userDao.save(user);
	}

	@Override
	@Transactional
	public void updateUser(UserDetailUpdateDTO updateUser) {
		
		User user = userDao.findOne(updateUser.getUserId());
		if (user == null) {
			throw new MessageSourceException(MessageCode.MSG006, updateUser.getUserId());
		}
		
		User usernameUpdate = userDao.findByUsername(updateUser.getUsername(), updateUser.getUserId());
		if (usernameUpdate != null) {
			throw new MessageSourceException(MessageCode.MSG007, updateUser.getUsername());
		}
		
		Profile profile = profileDao.findOne(user.getProfileId());
		
		mapper.map(updateUser, user);
		user.setFkRoleId(updateUser.getRoleId());
		userDao.save(user);
		
		mapper.map(updateUser, profile);
		profileDao.save(profile);
		
	}

	@Override
	public boolean hasBranch(Long userId, String branchCode) {
		return userDao.getUserByUserIdAndBranchCode(userId, branchCode) != null;
	}

	@Override
	public boolean hasBranch(String username, String branchCode) {
		return userDao.getUserByUsernameAndBranchCode(username, branchCode) != null;
	}
	
	@Override
	public void validateUserByAuthority(Long userId, UserProfile userProfile) {
		Set<String> authorities = AuthorityUtils.authorityListToSet(userProfile.getAuthorities());
		if(authorities.contains(PermissionCode.P006)) {
			if (!hasBranch(userId, userProfile.getBranchCode())) {
				throw new MessageException("E001");
			}
		} 
	}

	@Override
	public void validateUserByAuthority(String username, UserProfile userProfile) {
		Set<String> authorities = AuthorityUtils.authorityListToSet(userProfile.getAuthorities());
		if(authorities.contains(PermissionCode.P006)) {
			if (!hasBranch(username, userProfile.getBranchCode())) {
				throw new MessageSourceException(MessageCode.MSG008);
			}
		} 
	}

}
