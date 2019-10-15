package com.tirmizee.backend.service.data;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ForgotPasswordModel {

	private String title;
	private String username;
	private String email;
	private String url;
	private Timestamp createDate;
	private Timestamp expiredDate;
	
}
