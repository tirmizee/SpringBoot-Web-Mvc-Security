package com.tirmizee.backend.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.tirmizee.backend.api.geography.data.CountProvincesDTO;
import com.tirmizee.core.repository.GeographyRepositoryImpl;
import com.tirmizee.core.repository.ProvinceRepository;

@Repository
public class GeographyDaoImpl extends GeographyRepositoryImpl implements GeographyDao {

	@Override
	public List<CountProvincesDTO> countProvince() {
		StringBuilder satement = new StringBuilder()
			.append(" SELECT ")
				.append(GEO_ID).append(" , ")
				.append(GEO_NAME_TH).append(" , ")
				.append(GEO_NAME_TH).append(" , ")
			.append(" COUNT(*) AS COUNT_PROVINCE ")
			.append(" FROM ").append(TB_GEOGRAPHY)
			.append(" INNER JOIN ").append(ProvinceRepository.TB_PROVINCES)
			.append(" ON ").append(GEO_ID).append(" = ").append(ProvinceRepository.GEO_ID)
			.append(" GROUP BY ")
				.append(GEO_ID).append(" , ")
				.append(GEO_NAME_TH).append(" , ")
				.append(GEO_NAME_TH);
		return getJdbcOps().query(satement.toString(), new BeanPropertyRowMapper<>(CountProvincesDTO.class));
	}

}
