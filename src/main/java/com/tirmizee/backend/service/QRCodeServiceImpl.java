package com.tirmizee.backend.service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRCodeServiceImpl implements QRCodeService {

	@Override
	public void generateQRCodeImage(String text, int width, int height, String filePath) throws IOException, WriterException {
		Path path = FileSystems.getDefault().getPath(filePath);
		String extension = FilenameUtils.getExtension(filePath).toUpperCase();
        BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToPath(bitMatrix, extension, path);
	}

	@Override
	public byte[] generateQRCodeByte(String text, int width, int height) throws WriterException, IOException {
		return null;
	}

}
