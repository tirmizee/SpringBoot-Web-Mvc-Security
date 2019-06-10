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

import com.tirmizee.backend.api.address.data.SearchVillageDTO;
import com.tirmizee.core.domain.Village;
import com.tirmizee.core.repository.SubDistrictRepository;
import com.tirmizee.core.repository.VillageRepositoryImpl;

@Repository
public class VillageDaoImpl extends VillageRepositoryImpl implements VillageDao {

	@Override
	public Page<Village> findByTerm(SearchVillageDTO search) {
		
		Pageable pageable = new PageRequest(search.getPage(), search.getSize());
		
		StringBuilder statement = new StringBuilder()
			.append(" SELECT * ")
			.append(" FROM ").append(TB_VILLAGES)
			.append(" INNER JOIN ").append(SubDistrictRepository.TB_SUBDISTRICTS)
			.append(" ON ").append(SUB_DISTRICT_CODE).append(" = ").append(SubDistrictRepository.SUBDISTRICT_CODE)
			.append(" WHERE ").append(SUB_DISTRICT_CODE).append(" = ? ")
			.append(" AND ").append(VILLAGE_NAME).append(" LIKE ? ");
			
		List<Object> params = new LinkedList<>();
		params.add(search.getSubDistrictCode());
		params.add("%" + StringUtils.trimToEmpty(search.getTerm()) + "%");
		
		String statementPage =  getSqlGenerator().selectAll(statement, pageable);
		List<Village> content = getJdbcOps().query(
			statementPage, 
			params.toArray(), 
			new BeanPropertyRowMapper<>(Village.class)
		);
		Long total = count(statement.toString(), params.toArray());
		return new PageImpl<>(content, pageable, total);
	}

}
