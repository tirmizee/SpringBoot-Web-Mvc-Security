package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReqUpdateStatusDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private boolean status;
	
}
