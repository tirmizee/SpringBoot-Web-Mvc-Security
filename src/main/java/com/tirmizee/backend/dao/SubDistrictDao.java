package com.tirmizee.backend.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tirmizee.backend.api.address.data.SearchSubDistrictDTO;
import com.tirmizee.backend.api.address.data.SubDistrictCountVillageDTO;
import com.tirmizee.backend.api.address.data.SubDistrictDTO;
import com.tirmizee.core.repository.SubDistrictRepository;

public interface SubDistrictDao extends SubDistrictRepository {
	
	Page<SubDistrictDTO> findByTerm(SearchSubDistrictDTO search);
	
	List<SubDistrictCountVillageDTO> findCountVillageByDistrictCode(String districtCode);
	
}
