package com.tirmizee.backend.api;

import java.util.concurrent.ForkJoinPool;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.tirmizee.backend.dto.NHSO01Detail;

@RestController
@RequestMapping(path = "/api/rest")
public class RestApiController {

	@GetMapping(path = "/hello")
    public NHSO01Detail index() {
        return new NHSO01Detail();
    }
	
	@GetMapping(path = "/hellooo")
	public DeferredResult<NHSO01Detail> res(){
		DeferredResult<NHSO01Detail> deferredResult = new DeferredResult<>(15000l);
		ForkJoinPool forkJoinPool = new ForkJoinPool(200);
		forkJoinPool.submit(() -> {
			deferredResult.setResult(new NHSO01Detail());
		});
		return deferredResult;
	}
	
}
