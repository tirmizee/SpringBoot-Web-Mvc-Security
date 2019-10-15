package com.tirmizee.backend.api.log.data;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LogFileDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Date createDate;
	private String createDateText;
	private String extention;
	private long size;
	
}
