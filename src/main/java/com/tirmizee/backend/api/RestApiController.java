package com.tirmizee.backend.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.backend.dto.NHSO01Detail;

@RestController
@RequestMapping(path = "/api/rest")
public class RestApiController {

	@GetMapping(path = "/hello")
    public NHSO01Detail index() {
        return new NHSO01Detail();
    }

}
