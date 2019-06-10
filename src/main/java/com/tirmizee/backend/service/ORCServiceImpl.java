package com.tirmizee.backend.service;

import java.io.File;

import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class ORCServiceImpl implements ORCService {

	@Override
	public String generateText(File imageFile) {
		try {
			Tesseract tesseract = new Tesseract();
			tesseract.setDatapath("E:/Tesseract-OCR/tessdata");
			return tesseract.doOCR(imageFile);		
		} catch (TesseractException e) {		
			return null;
		}
	}

}
