package com.tirmizee.backend.service;

import java.io.IOException;
import java.util.List;

import com.tirmizee.backend.api.log.data.LogFileDTO;

public interface LogFileService {
	
	List<LogFileDTO> allLogs();
	
	List<String> readLineByDate(String dateText) throws IOException;

}
