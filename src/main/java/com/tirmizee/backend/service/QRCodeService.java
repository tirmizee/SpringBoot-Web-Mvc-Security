package com.tirmizee.backend.service;

import java.io.IOException;

import com.google.zxing.WriterException;

public interface QRCodeService {
	
	void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException;
	
	byte[] generateQRCodeByte(String text, int width, int height) throws WriterException, IOException;

}
