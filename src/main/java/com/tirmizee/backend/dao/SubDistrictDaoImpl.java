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
import com.tirmizee.backend.api.address.data.SubDistrictCountVillageDTO;
import com.tirmizee.backend.api.address.data.SubDistrictDTO;
import com.tirmizee.core.repository.DistrictRepository;
import com.tirmizee.core.repository.PostCodeRepository;
import com.tirmizee.core.repository.ProvinceRepository;
import com.tirmizee.core.repository.SubDistrictRepository;
import com.tirmizee.core.repository.SubDistrictRepositoryImpl;
import com.tirmizee.core.repository.VillageRepository;

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

	@Override
	public List<SubDistrictCountVillageDTO> findCountVillageByDistrictCode(String districtCode) {
		StringBuilder statetment = new StringBuilder()
			.append(" SELECT ")
			.append(  SUBDISTRICT_CODE).append(",")
			.append(  SUBDISTRICT_NAME_TH).append(",")
			.append(" COUNT(*) AS COUNT_VILLAGE ")
			.append(" FROM ").append(ProvinceRepository.TB_PROVINCES)
			.append(" INNER JOIN ").append(DistrictRepository.TB_DISTRICTS)
			.append(" ON ").append(ProvinceRepository.PROVINCE_CODE)
			.append(" = ").append(DistrictRepository.PROVINCE_CODE)
			.append(" INNER JOIN ").append(SubDistrictRepository.TB_SUBDISTRICTS)
			.append(" ON ").append(DistrictRepository.DISTRICT_CODE)
			.append(" = ").append(DISTRICT_CODE)
			.append(" INNER JOIN ").append(VillageRepository.TB_VILLAGES)
			.append(" ON ").append(SUBDISTRICT_CODE)
			.append(" = ").append(VillageRepository.SUB_DISTRICT_CODE)
			.append(" WHERE ").append(DistrictRepository.DISTRICT_CODE).append(" = ? ")
			.append(" GROUP BY ")
			.append(  SUBDISTRICT_CODE).append(",")
			.append(  SUBDISTRICT_NAME_TH);
		return getJdbcOps().query(statetment.toString(), params(districtCode), new BeanPropertyRowMapper<>(SubDistrictCountVillageDTO.class));
	}

}
