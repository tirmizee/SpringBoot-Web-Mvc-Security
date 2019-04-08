package com.tirmizee.backend.dao;

import org.springframework.data.domain.Page;

import com.tirmizee.backend.api.subdistrict.data.SearchSubDistrictDTO;
import com.tirmizee.backend.api.subdistrict.data.SubDistrictDTO;
import com.tirmizee.core.repository.SubDistrictRepository;

public interface SubDistrictDao extends SubDistrictRepository {
	
	Page<SubDistrictDTO> findByTerm(SearchSubDistrictDTO search);

}
