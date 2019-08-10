package com.tirmizee.core.domain;

import java.sql.Timestamp;

import org.springframework.data.domain.Persistable;

import lombok.Data;

@Data
public class ForgotPassword implements Persistable<Integer> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Long userId;
	private String token;
	private Timestamp updateDate;
	private Timestamp createDate;
	private Timestamp expiredDate;
	private String email;
	private String accessIp;
	private boolean isReset;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public boolean isNew() {
		return id == null;
	}

}
