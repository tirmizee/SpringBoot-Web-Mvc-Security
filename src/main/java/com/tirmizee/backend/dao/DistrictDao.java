package com.tirmizee.backend.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tirmizee.backend.api.address.data.DistrictCountVillageDTO;
import com.tirmizee.backend.api.address.data.SearchDistrictDTO;
import com.tirmizee.core.domain.District;
import com.tirmizee.core.repository.DistrictRepository;

public interface DistrictDao extends DistrictRepository {
	
	Page<District> findByTerm(SearchDistrictDTO search);
	
	List<DistrictCountVillageDTO> findCountVillageByDistrictCode(String districtCode);
	
}
