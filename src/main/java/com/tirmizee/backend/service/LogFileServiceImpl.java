package com.tirmizee.backend.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tirmizee.backend.api.log.data.LogFileDTO;

@Service
public class LogFileServiceImpl implements LogFileService {

	public static final String LOG_FORMAT = "^SpringBoot.[0-9]{2}-[0-9]{2}-[0-9]{4}.log$";
	public static final String LOG_FORMAT_NOW = "^SpringBoot.log$";
	
	@Value("${app.root.path}")
	public String rootPath;
	
	@Override
	public List<LogFileDTO> allLogs() {
		
		List<LogFileDTO> listLogs = new ArrayList<>();

		File logFolder = new File(rootPath + "/log");
		if (logFolder.isDirectory()) {
			for (File logFile : logFolder.listFiles()) {
				
				String fileName = logFile.getName();
				long size = logFile.length();
				
				LogFileDTO logFileDTO = new LogFileDTO();
				logFileDTO.setName(fileName);
				logFileDTO.setSize(size);
				logFileDTO.setExtention(FilenameUtils.getExtension(fileName));
				if (fileName.matches(LOG_FORMAT)) {
					logFileDTO.setCreateDateText(fileName.split("\\.")[1]);
				}
				listLogs.add(logFileDTO);
			}
		}
		return listLogs;
	}

	@Override
	public List<String> readLineByDate(String dateText) throws IOException {
		
		String pathFileNow = rootPath + "/log/SpringBoot.log";
		String pathFileDate = rootPath + "/log/SpringBoot." + dateText + ".log";

		File logFile = new File(pathFileNow);

		if (StringUtils.isNotBlank(dateText)) {
			logFile = new File(pathFileDate);
		}
		
		if (logFile.exists()) {
			return FileUtils.readLines(logFile, "UTF-8");
		}
		
		return Collections.emptyList();
	}
	
}
