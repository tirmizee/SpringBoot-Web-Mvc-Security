package com.tirmizee.backend.api.session.data;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserLoggedDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String firstName;
	private String lastName;
	private String roleName;
	private String accessIp;
	private String sessionId;
	private Integer maxSession;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	
	private boolean expired ;
	
}
