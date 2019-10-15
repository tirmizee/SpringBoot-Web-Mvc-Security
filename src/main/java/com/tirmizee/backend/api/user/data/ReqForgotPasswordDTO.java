package com.tirmizee.backend.api.user.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReqForgotPasswordDTO implements Serializable {

	private static final long serialVersionUID = -7875052620442575797L;

	private String email;

}
