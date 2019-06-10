package com.tirmizee.backend.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.tirmizee.backend.api.file.data.MockDataDTO;

public interface ExcelService {

	List<MockDataDTO> readData(Workbook workbook);
	
}
