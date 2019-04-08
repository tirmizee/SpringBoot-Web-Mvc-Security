package com.tirmizee.backend.api.district;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.api.district.data.DistrictDTO;
import com.tirmizee.backend.api.district.data.SearchDistrictDTO;
import com.tirmizee.backend.dao.DistrictDao;
import com.tirmizee.core.component.PageMapper;
import com.tirmizee.core.domain.District;

@RestController
@RequestMapping(path = "api/district")
public class DistrictApiController {

	@Autowired
	private PageMapper mapper;
	
	@Autowired
	private DistrictDao districtDao; 
	
	@PreAuthorize("hasAnyAuthority('P002')")
	@PostMapping(path = "/findByTerm")
	public Page<DistrictDTO> findByTerm(@RequestBody @Valid SearchDistrictDTO search){
		Page<District> page = districtDao.findByTerm(search);
		return mapper.map(page, DistrictDTO.class);
	}
	
}
