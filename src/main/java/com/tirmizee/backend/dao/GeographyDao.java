package com.tirmizee.backend.dao;

import java.util.List;

import com.tirmizee.backend.api.geography.data.CountProvincesDTO;
import com.tirmizee.core.repository.GeographyRepository;

public interface GeographyDao extends GeographyRepository {
	
	List<CountProvincesDTO> countProvince();

}
