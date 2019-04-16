package com.tirmizee.backend.api.file;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.api.file.data.MockDataDTO;
import com.tirmizee.backend.api.file.data.UploadExcelDTO;
import com.tirmizee.backend.service.ExcelService;
import com.tirmizee.core.utilities.WorkbookUtils;

@RestController
@RequestMapping(path = "api/file")
public class ApiUploadController {
	
	@Autowired
	private ExcelService excelService;
	
	@PostMapping(path = "/excel/pre", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<MockDataDTO> uploadExcel(@ModelAttribute @Valid UploadExcelDTO uploadExcelDTO) {
		List<MockDataDTO> dataExcel = new ArrayList<>();
		try(Workbook workbook = WorkbookUtils.create(uploadExcelDTO.getFile())){
			dataExcel = excelService.readData(workbook);
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		return dataExcel;
	}

}
