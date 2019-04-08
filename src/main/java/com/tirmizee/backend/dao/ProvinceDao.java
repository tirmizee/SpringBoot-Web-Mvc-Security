package com.tirmizee.backend.dao;

import org.springframework.data.domain.Page;

import com.tirmizee.backend.api.province.data.SearchProvinceDTO;
import com.tirmizee.core.domain.Province;
import com.tirmizee.core.repository.ProvinceRepository;

public interface ProvinceDao extends ProvinceRepository {

	Page<Province> findByTerm(SearchProvinceDTO search);
	
}
