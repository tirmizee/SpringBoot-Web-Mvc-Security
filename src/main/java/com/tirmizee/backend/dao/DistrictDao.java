package com.tirmizee.backend.dao;

import org.springframework.data.domain.Page;

import com.tirmizee.backend.api.district.data.SearchDistrictDTO;
import com.tirmizee.core.domain.District;
import com.tirmizee.core.repository.DistrictRepository;

public interface DistrictDao extends DistrictRepository {
	
	Page<District> findByTerm(SearchDistrictDTO search);
	
}
