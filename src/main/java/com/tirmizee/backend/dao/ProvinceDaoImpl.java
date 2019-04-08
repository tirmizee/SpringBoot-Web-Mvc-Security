package com.tirmizee.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.tirmizee.backend.api.province.data.SearchProvinceDTO;
import com.tirmizee.core.domain.Province;
import com.tirmizee.core.repository.ProvinceRepositoryImpl;

@Repository
public class ProvinceDaoImpl extends ProvinceRepositoryImpl implements ProvinceDao {

	@Override
	public Page<Province> findByTerm(SearchProvinceDTO search) {
		
		Pageable pageable = new PageRequest(search.getPage(), search.getSize());
		
		StringBuilder statement = new StringBuilder()
			.append(" SELECT * FROM ").append(TB_PROVINCES)
			.append(" WHERE ").append(COL_PROVINCE_NAME_TH).append(" LIKE ? ")
			.append(" OR ").append(COL_PROVINCE_NAME_EN).append(" LIKE ?");
		
		List<Object> params = new ArrayList<>();
		params.add("%" + StringUtils.trimToEmpty(search.getTerm()) + "%");
		params.add("%" + StringUtils.trimToEmpty(search.getTerm()) + "%");
		
		String statementPage =  getSqlGenerator().selectAll(statement, pageable);
		List<Province> content = getJdbcOps().query(statementPage, params.toArray(), ROW_MAPPER);
		Long total = count(statement.toString(), params.toArray());
		return new PageImpl<>(content, pageable, total);
	}

}
