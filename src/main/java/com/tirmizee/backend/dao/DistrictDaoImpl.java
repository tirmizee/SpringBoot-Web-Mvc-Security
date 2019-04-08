package com.tirmizee.backend.dao;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.tirmizee.backend.api.district.data.SearchDistrictDTO;
import com.tirmizee.core.domain.District;
import com.tirmizee.core.repository.DistrictRepositoryImpl;

@Repository
public class DistrictDaoImpl extends DistrictRepositoryImpl implements DistrictDao {

	@Override
	public Page<District> findByTerm(SearchDistrictDTO search) {
		
		Pageable pageable = new PageRequest(search.getPage(), search.getSize());
		
		StringBuilder statement = new StringBuilder()
			.append(" SELECT * FROM ").append(TB_DISTRICTS)
			.append(" WHERE ").append(COL_FK_PROVINCE_ID).append(" = ? ")
			.append(" AND (").append(COL_DISTRICT_NAME_TH).append(" LIKE ? ")
			.append(" OR ").append(COL_DISTRICT_NAME_EN).append(" LIKE ? )");
		
		List<Object> params = new LinkedList<>();
		params.add(search.getProvinceId());
		params.add("%" + StringUtils.trimToEmpty(search.getTerm()) + "%");
		params.add("%" + StringUtils.trimToEmpty(search.getTerm()) + "%");
		
		String statementPage =  getSqlGenerator().selectAll(statement, pageable);
		List<District> content = getJdbcOps().query(statementPage, params.toArray(), ROW_MAPPER);
		Long total = count(statement.toString(), params.toArray());
		return new PageImpl<>(content, pageable, total);
	}

}
