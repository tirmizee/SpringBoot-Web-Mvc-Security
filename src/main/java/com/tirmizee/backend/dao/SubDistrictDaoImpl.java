package com.tirmizee.backend.dao;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.backend.api.address.data.SearchSubDistrictDTO;
import com.tirmizee.backend.api.address.data.SubDistrictDTO;
import com.tirmizee.core.repository.PostCodeRepository;
import com.tirmizee.core.repository.SubDistrictRepositoryImpl;

@Repository
public class SubDistrictDaoImpl extends SubDistrictRepositoryImpl implements SubDistrictDao {
	
	@Override
	public Page<SubDistrictDTO> findByTerm(SearchSubDistrictDTO search) {
		
		Pageable pageable = new PageRequest(search.getPage(), search.getSize());
		
		StringBuilder statement = new StringBuilder()
			.append(" SELECT ")
				.append(TB_SUBDISTRICTS).append(".* ,")
				.append(PostCodeRepository.ZIPCODE)
			.append(" FROM ").append(TB_SUBDISTRICTS)
			.append(" LEFT JOIN ").append(PostCodeRepository.TB_POSTCODE)
			.append(" ON ").append(SUBDISTRICT_CODE).append(" = ").append(PostCodeRepository.SUB_DISTRICT_CODE)
			.append(" WHERE ").append(DISTRICT_CODE).append(" = ? ")
			.append(" AND ").append(SUBDISTRICT_NAME_TH).append(" LIKE ? ");
			
		List<Object> params = new LinkedList<>();
		params.add(search.getDistrictCode());
		params.add("%" + StringUtils.trimToEmpty(search.getTerm()) + "%");
		
		String statementPage =  getSqlGenerator().selectAll(statement, pageable);
		List<SubDistrictDTO> content = getJdbcOps().query(
			statementPage, 
			params.toArray(), 
			new BeanPropertyRowMapper<>(SubDistrictDTO.class)
		);
		Long total = count(statement.toString(), params.toArray());
		return new PageImpl<>(content, pageable, total);
	}

}
