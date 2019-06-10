package com.tirmizee.backend.api.geography;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.api.geography.data.CountProvincesDTO;
import com.tirmizee.backend.dao.GeographyDao;

@RestController
@RequestMapping(path = "api/geography")
public class GeographyApiController {
	
	@Autowired
	private GeographyDao geographyDao;
	
	@GetMapping("/countprovince")
	public List<CountProvincesDTO> countProvinceByGeography(){
		return geographyDao.countProvince();
	}

}
