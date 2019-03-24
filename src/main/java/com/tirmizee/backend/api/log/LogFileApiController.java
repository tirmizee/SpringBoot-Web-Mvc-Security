package com.tirmizee.backend.api.log;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.service.LogFileService;

@RestController
@RequestMapping(path="/api/log")
public class LogFileApiController {
	
	@Autowired
	private LogFileService logFileService;
	
	@PreAuthorize("hasAnyAuthority('P004')")
	@GetMapping(path = "/all")
	public Map<String, Object> allLogs(){
		Map<String, Object> response = new HashMap<>();
		response.put("data", logFileService.allLogs());
		return response;
	}
	
	@PreAuthorize("hasAnyAuthority('P004')")
	@GetMapping(path = "/{date}")
	public List<String> reaData(@PathVariable String date) throws IOException{
		return logFileService.readLineByDate(date);
	}

}
