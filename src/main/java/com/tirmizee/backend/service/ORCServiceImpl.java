package com.tirmizee.backend.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class ORCServiceImpl implements ORCService {

	@Value("${app.orc.path}")
	private String orcPath;
	
	@Override
	public String generateText(File imageFile) {
		try {
			Tesseract tesseract = new Tesseract();
			tesseract.setDatapath(orcPath);
			return tesseract.doOCR(imageFile);		
		} catch (TesseractException e) {		
			return null;
		}
	}

}
