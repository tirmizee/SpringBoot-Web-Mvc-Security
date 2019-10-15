package com.tirmizee.backend.service.data;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockUserTime {

	private String username;
	private Timestamp lockTime;
	
}
