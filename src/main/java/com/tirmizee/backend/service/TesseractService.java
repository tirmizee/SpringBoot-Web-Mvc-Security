package com.tirmizee.backend.service;

import java.io.File;

public interface TesseractService {

	String generateText(File imageFile);
	
}
