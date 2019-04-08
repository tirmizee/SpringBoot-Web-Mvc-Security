package com.tirmizee.backend.api.subdistrict;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.api.subdistrict.data.SearchSubDistrictDTO;
import com.tirmizee.backend.api.subdistrict.data.SubDistrictDTO;
import com.tirmizee.backend.dao.SubDistrictDao;

@RestController
@RequestMapping(path = "api/subdistrict")
public class SubDistrictApiController {

	@Autowired
	private SubDistrictDao subDistrictDao;
	
	@PostMapping(path = "/findByTerm")
	public Page<SubDistrictDTO> findByTerm(@RequestBody @Valid SearchSubDistrictDTO search){
		return subDistrictDao.findByTerm(search);
	}
	
}
