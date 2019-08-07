package com.tirmizee.backend.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.tirmizee.backend.api.address.data.DistrictCountVillageDTO;
import com.tirmizee.backend.api.address.data.SearchDistrictDTO;
import com.tirmizee.core.domain.District;
import com.tirmizee.core.jdbcrepository.NamedQueryJdbcOperations;
import com.tirmizee.core.repository.DistrictRepositoryImpl;

@Repository
public class DistrictDaoImpl extends DistrictRepositoryImpl implements DistrictDao {

	@Autowired
	private NamedQueryJdbcOperations queryNamedJdbc;
	
	@Override
	public Page<District> findByTerm(SearchDistrictDTO search) {
		
		Pageable pageable = new PageRequest(search.getPage(), search.getSize());
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource()
			.addValue("PROVINCE_CODE", search.getProvinceCode())
			.addValue("DISTRICT_NAME_TH", "%" + StringUtils.trimToEmpty(search.getTerm()) + "%");
		
		return queryNamedJdbc.namedQueryForPage("FIND.DISTRICT.BY.PROVINCECODE", pageable, paramSource, District.class);
	}
	
	@Override
	public List<DistrictCountVillageDTO> findCountVillageByDistrictCode(String districtCode) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource("DISTRICT_CODE", districtCode);
		return queryNamedJdbc.namedQuery("FIND.COUNT.VILLAGE.BY.DISTRICTCODE", paramSource, DistrictCountVillageDTO.class);
	}

}
