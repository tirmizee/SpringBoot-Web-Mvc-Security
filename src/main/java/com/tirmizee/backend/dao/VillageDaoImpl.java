package com.tirmizee.backend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tirmizee.core.domain.Village;
import com.tirmizee.core.repository.VillageRepositoryImpl;

@Repository
public class VillageDaoImpl extends VillageRepositoryImpl implements VillageDao {

	@Override
	public List<Village> findBySubDistrictCode(String subDistrictCode) {
		return null;
	}

}
