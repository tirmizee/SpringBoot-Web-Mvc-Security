package com.tirmizee.backend.dao;

import org.springframework.data.domain.Page;

import com.tirmizee.backend.api.address.data.SearchVillageDTO;
import com.tirmizee.core.domain.Village;
import com.tirmizee.core.repository.VillageRepository;

public interface VillageDao extends VillageRepository {
	
	Page<Village> findByTerm(SearchVillageDTO search);

}
