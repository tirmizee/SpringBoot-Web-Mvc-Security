package com.tirmizee.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tirmizee.backend.dao.ProfileDao;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private ProfileDao profileDao;
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('P002')")
	@GetMapping(path = "/proile/img/{uid}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	public byte[] ddsadsa(@PathVariable Long uid) {
		byte[] blob = profileDao.findByUserId(uid).getProfileByte();
		return blob;
	}
	
}
