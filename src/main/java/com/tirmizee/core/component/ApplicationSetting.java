package com.tirmizee.core.component;

import lombok.Data;

@Data
public class ApplicationSetting {
	
	private Integer sessionTimeOut;
	private Integer maxLoginFail;
	private Integer passwordChangeDay;

}
