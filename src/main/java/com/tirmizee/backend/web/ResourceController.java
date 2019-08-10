package com.tirmizee.backend.web;

import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.tirmizee.backend.dao.ProfileDao;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	public Logger logger = LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private ProfileDao profileDao;
	
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('P002','P006')")
	@GetMapping(path = "/proile/img/{uid}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE } )
	public DeferredResult<byte[]> proileImg(@PathVariable Long uid) {
		DeferredResult<byte[]> deferredResult = new DeferredResult<>(30000L);
		ForkJoinPool.commonPool().submit(()->{
			try {
				byte[] blob = profileDao.findByUserId(uid).getProfileByte();
				deferredResult.setResult(blob);
			}catch (Exception exception) {
				logger.debug(exception.getMessage());
				deferredResult.setErrorResult(exception);
			}
		});
		return deferredResult;
	}
	
}
