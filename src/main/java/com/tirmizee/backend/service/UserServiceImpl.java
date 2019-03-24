package com.tirmizee.backend.service;

import static com.tirmizee.core.constant.Constant.AppSetting.PASSWORD_CHANGE_DAY;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tirmizee.backend.api.user.data.ReqPasswordDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordExpriedDTO;
import com.tirmizee.backend.api.user.data.ReqPasswordResetTokenDTO;
import com.tirmizee.backend.api.user.data.UserDetailCriteriaDTO;
import com.tirmizee.backend.api.user.data.UserDetailDTO;
import com.tirmizee.backend.dao.ForgotPasswordDao;
import com.tirmizee.backend.dao.LogPasswordDao;
import com.tirmizee.backend.dao.UserDao;
import com.tirmizee.backend.service.data.ForgotPasswordModel;
import com.tirmizee.core.component.PageMapper;
import com.tirmizee.core.constant.MessageCode;
import com.tirmizee.core.datatable.PageRequestHelper;
import com.tirmizee.core.datatable.RequestTable;
import com.tirmizee.core.datatable.ResponseTable;
import com.tirmizee.core.domain.ForgotPassword;
import com.tirmizee.core.domain.LogPassword;
import com.tirmizee.core.domain.User;
import com.tirmizee.core.exception.BusinessException;
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
	private EmailService emailService;
	
	@Autowired 
	private UserDao userDao;
	
	@Autowired 
	private LogPasswordDao logPasswordDao;
	
	@Autowired 
	private ForgotPasswordDao forgotPasswordDao;
	
	@Autowired 
	private ForgotPasswordService forgotPasswordService;
	
	@Autowired 
	private LogPasswordService logPasswordService;

	@Autowired
	private AppSettingService appSettingService;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void changePasswordFirstLogin(String username, ReqPasswordDTO passwordDTO) {
		
		User user = userDao.findByUsername(username);
		
		// VALIDATE THE PASSWORD MUST BE UNIQUE.
		if (passwordEncoder.matches(passwordDTO.getConfirmPassword(), user.getPassword())) {
			throw new BusinessException(MessageCode.MSG003);
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
			throw new BusinessException(MessageCode.MSG001);
		}
		
		// VALIDATE NEW PASSWORD MUST NOT BE LIKE OLD PASSWORD
		if (logPasswordService.isPasswordExists(username, passwordExpriedDTO.getNewPasswordConfirm())) {
			throw new BusinessException(MessageCode.MSG002);
		}
		
		int day = Integer.parseInt(appSettingService.getValue(PASSWORD_CHANGE_DAY));
		final String passwordEncode = passwordEncoder.encode(passwordExpriedDTO.getNewPasswordConfirm());
		
		user.setPassword(passwordEncode);
		user.setCredentialsexpiredDate(DateUtils.plusDays(day));
		user.setCredentialsnonexpired(true);
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
	public ResponseTable<UserDetailDTO> pagingTable(RequestTable<UserDetailCriteriaDTO> requestTable) {
		Pageable pageable = PageRequestHelper.build(requestTable, UserDetailDTO.class);
		Page<UserDetailDTO> page = userDao.findPageByCriteria(pageable, requestTable.getSerch());
		return new ResponseTable<>(page);
	}

	@Override
	public boolean isPasswordExpried(Date expriedDate) {
		return expriedDate == null ? false : DateUtils.now().after(expriedDate);
	}

	@Override
	public void fourcePasswordExpired(String username) {
		User user = userDao.findByUsername(username);
		user.setCredentialsnonexpired(false);
		user.setUpdateDate(DateUtils.now());
		userDao.save(user);
	}
	
	@Override
	public long countUses() {
		return userDao.count();
	}

	@Override
	@Transactional
	public void forgotPassword(String email) {
		
		User user = userDao.findByEmail(email);
		if (user == null) {
			throw new BusinessException(MessageCode.MSG004);
		}
		
		String accessIp = request.getRemoteAddr();
		String token = forgotPasswordService.generateToken();
		String url = forgotPasswordService.createURLResetPassword(user.getId(),token);
		
		ForgotPassword forgotPassword = new ForgotPassword();
		forgotPassword.setUserId(user.getId());
		forgotPassword.setEmail(email);
		forgotPassword.setToken(token);
		forgotPassword.setAccessIp(accessIp);
		forgotPassword.setExpiredDate(DateUtils.plusMinutes(15));
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
	@Transactional
	public void resetPassword(ReqPasswordResetTokenDTO passwordResetTokenDTO) {
		
		Long uid = passwordResetTokenDTO.getUid();
		String token = passwordResetTokenDTO.getToken();
		
		ForgotPassword forgotPassword = forgotPasswordDao.findByUserIdAndToken(uid, token);
		if (forgotPassword == null) {
			throw new BusinessException(MessageCode.MSG005);
		}
		
		User user = userDao.findOne(uid);
		user.setPassword(passwordEncoder.encode(passwordResetTokenDTO.getConfirmPassword()));
		user.setUpdateDate(DateUtils.now());
		userDao.save(user);
		
		forgotPassword.setReset(true);
		forgotPasswordDao.save(forgotPassword);
		
	} 
	
}
