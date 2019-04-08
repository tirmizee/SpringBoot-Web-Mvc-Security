package com.tirmizee.backend.api.province;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.api.province.data.ProvinceDTO;
import com.tirmizee.backend.api.province.data.SearchProvinceDTO;
import com.tirmizee.backend.dao.ProvinceDao;
import com.tirmizee.core.component.PageMapper;
import com.tirmizee.core.domain.Province;

@RestController
@RequestMapping(path = "api/province")
public class ApiProvinceController {
	
	@Autowired
	private PageMapper mapper;

	@Autowired
	private ProvinceDao provinceDao; 
	
	@PreAuthorize("hasAnyAuthority('P002')")
	@PostMapping(path = "/findByTerm")
	public Page<ProvinceDTO> findByTerm(@RequestBody @Valid SearchProvinceDTO search){
		Page<Province> page = provinceDao.findByTerm(search);
		return mapper.map(page, ProvinceDTO.class);
	}
	
}
