package com.tirmizee.backend.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.tirmizee.backend.api.address.data.SearchSubDistrictDTO;
import com.tirmizee.backend.api.address.data.SubDistrictDTO;
import com.tirmizee.core.jdbcrepository.NamedQueryJdbcOperations;
import com.tirmizee.core.repository.SubDistrictRepositoryImpl;

@Repository
public class SubDistrictDaoImpl extends SubDistrictRepositoryImpl implements SubDistrictDao {
	
	@Autowired
	private NamedQueryJdbcOperations queryNamedJdbc;
	
	@Override
	public Page<SubDistrictDTO> findByTerm(SearchSubDistrictDTO search) {
		
		Pageable pageable = new PageRequest(search.getPage(), search.getSize());
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource()
			.addValue("DISTRICT_CODE", search.getDistrictCode())
			.addValue("SUBDISTRICT_NAME_TH", "%" + StringUtils.trimToEmpty(search.getTerm()) + "%");
			
		return queryNamedJdbc.namedQueryForPage("FIND.SUBDISTRICT.BY.TERM", pageable, paramSource, SubDistrictDTO.class);
	}

}
