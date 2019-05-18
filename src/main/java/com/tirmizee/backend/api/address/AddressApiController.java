package com.tirmizee.backend.api.address;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.api.address.data.DistrictDTO;
import com.tirmizee.backend.api.address.data.ProvinceDTO;
import com.tirmizee.backend.api.address.data.SearchDistrictDTO;
import com.tirmizee.backend.api.address.data.SearchProvinceDTO;
import com.tirmizee.backend.api.address.data.SearchSubDistrictDTO;
import com.tirmizee.backend.api.address.data.SearchVillageDTO;
import com.tirmizee.backend.api.address.data.SubDistrictCountVillageDTO;
import com.tirmizee.backend.api.address.data.SubDistrictDTO;
import com.tirmizee.backend.api.address.data.VillageDTO;
import com.tirmizee.backend.dao.DistrictDao;
import com.tirmizee.backend.dao.ProvinceDao;
import com.tirmizee.backend.dao.SubDistrictDao;
import com.tirmizee.backend.dao.VillageDao;
import com.tirmizee.core.component.PageMapper;
import com.tirmizee.core.domain.District;
import com.tirmizee.core.domain.Province;
import com.tirmizee.core.domain.Village;

@RestController
@RequestMapping(path = "api/address")
public class AddressApiController {

	@Autowired
	private PageMapper mapper;

	@Autowired
	private ProvinceDao provinceDao; 
	
	@Autowired
	private DistrictDao districtDao; 
	
	@Autowired
	private SubDistrictDao subDistrictDao;
	
	@Autowired
	private VillageDao villageDao;
	
	@PreAuthorize("hasAnyAuthority('P002')")
	@PostMapping(path = "/village")
	public Page<VillageDTO> findVillage(@RequestBody SearchVillageDTO searchVillage){
		Page<Village> page = villageDao.findByTerm(searchVillage);
		return mapper.map(page, VillageDTO.class);
	}
	
	@PreAuthorize("hasAnyAuthority('P002')")
	@PostMapping(path = "/subdistrict")
	public Page<SubDistrictDTO> findByTerm(@RequestBody @Valid SearchSubDistrictDTO search){
		return subDistrictDao.findByTerm(search);
	} 
	
	@PreAuthorize("hasAnyAuthority('P002')")
	@PostMapping(path = "/district")
	public Page<DistrictDTO> findByTerm(@RequestBody @Valid SearchDistrictDTO search){
		Page<District> page = districtDao.findByTerm(search);
		return mapper.map(page, DistrictDTO.class);
	}
	
	@PreAuthorize("hasAnyAuthority('P002')")
	@PostMapping(path = "/province")
	public Page<ProvinceDTO> findByTerm(@RequestBody @Valid SearchProvinceDTO search){
		Page<Province> page = provinceDao.findByTerm(search);
		return mapper.map(page, ProvinceDTO.class);
	}
	
	@GetMapping(path = "/subdistrict/{districtCode}/countvillage")
	public List<SubDistrictCountVillageDTO> countVillageOfSubDistrict(@PathVariable String districtCode){
		return subDistrictDao.findCountVillageByDistrictCode(districtCode);
	}
	
}
