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

import com.tirmizee.backend.api.address.data.DistrictCountVillageDTO;
import com.tirmizee.backend.api.address.data.SearchDistrictDTO;
import com.tirmizee.core.domain.District;
import com.tirmizee.core.repository.DistrictRepository;
import com.tirmizee.core.repository.DistrictRepositoryImpl;
import com.tirmizee.core.repository.ProvinceRepository;
import com.tirmizee.core.repository.SubDistrictRepository;
import com.tirmizee.core.repository.VillageRepository;

@Repository
public class DistrictDaoImpl extends DistrictRepositoryImpl implements DistrictDao {

	@Override
	public Page<District> findByTerm(SearchDistrictDTO search) {
		
		Pageable pageable = new PageRequest(search.getPage(), search.getSize());
		
		StringBuilder statement = new StringBuilder()
			.append(" SELECT * FROM ").append(TB_DISTRICTS)
			.append(" WHERE ").append(COL_PROVINCE_CODE).append(" = ? ")
			.append(" AND (").append(COL_DISTRICT_NAME_TH).append(" LIKE ? ")
			.append(" OR ").append(COL_DISTRICT_NAME_EN).append(" LIKE ? )");
		
		List<Object> params = new LinkedList<>();
		params.add(search.getProvinceCode());
		params.add("%" + StringUtils.trimToEmpty(search.getTerm()) + "%");
		params.add("%" + StringUtils.trimToEmpty(search.getTerm()) + "%");
		
		String statementPage =  getSqlGenerator().selectAll(statement, pageable);
		List<District> content = getJdbcOps().query(statementPage, params.toArray(), ROW_MAPPER);
		Long total = count(statement.toString(), params.toArray());
		return new PageImpl<>(content, pageable, total);
	}
	

	@Override
	public List<DistrictCountVillageDTO> findCountVillageByDistrictCode(String districtCode) {
		StringBuilder statetment = new StringBuilder()
			.append(" SELECT ")
			.append(  SubDistrictRepository.SUBDISTRICT_CODE).append(",")
			.append(  SubDistrictRepository.SUBDISTRICT_NAME_TH).append(",")
			.append(" COUNT(*) AS COUNT_VILLAGE ")
			.append(" FROM ").append(ProvinceRepository.TB_PROVINCES)
			.append(" INNER JOIN ").append(DistrictRepository.TB_DISTRICTS)
			.append(" ON ").append(ProvinceRepository.PROVINCE_CODE)
			.append(" = ").append(DistrictRepository.PROVINCE_CODE)
			.append(" INNER JOIN ").append(SubDistrictRepository.TB_SUBDISTRICTS)
			.append(" ON ").append(DistrictRepository.DISTRICT_CODE)
			.append(" = ").append(SubDistrictRepository.DISTRICT_CODE)
			.append(" INNER JOIN ").append(VillageRepository.TB_VILLAGES)
			.append(" ON ").append(SubDistrictRepository.SUBDISTRICT_CODE)
			.append(" = ").append(VillageRepository.SUB_DISTRICT_CODE)
			.append(" WHERE ").append(DistrictRepository.DISTRICT_CODE).append(" = ? ")
			.append(" GROUP BY ")
			.append(  SubDistrictRepository.SUBDISTRICT_CODE).append(",")
			.append(  SubDistrictRepository.SUBDISTRICT_NAME_TH);
		return getJdbcOps().query(statetment.toString(), params(districtCode), new BeanPropertyRowMapper<>(DistrictCountVillageDTO.class));
	}

}
