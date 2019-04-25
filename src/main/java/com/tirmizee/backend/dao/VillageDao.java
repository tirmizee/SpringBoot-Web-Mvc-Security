package com.tirmizee.backend.dao;

import java.util.List;

import com.tirmizee.core.domain.Village;
import com.tirmizee.core.repository.VillageRepository;

public interface VillageDao extends VillageRepository {
	
	List<Village> findBySubDistrictCode(String subDistrictCode);

}
