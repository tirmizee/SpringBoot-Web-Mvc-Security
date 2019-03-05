import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Base64;

import org.apache.commons.io.FilenameUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class Demo {

    public static void main(String[] args) throws WriterException, IOException {
    	String encodedString = Base64.getEncoder().encodeToString(generateQRCodeByte("102938747", 50, 50));
    	
    	
    	for (byte string : generateQRCodeByte("102938747", 50, 50)) {
			System.out.println(string);
		}
    }
    
	public static void generateQRCodeImage(String text, int width, int height, String filePath) throws IOException, WriterException {
		Path path = FileSystems.getDefault().getPath(filePath);
		String extension = FilenameUtils.getExtension(filePath).toUpperCase();
        BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToPath(bitMatrix, extension, path);
	}

	public static byte[] generateQRCodeByte(String text, int width, int height) throws WriterException, IOException {
		BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		return pngOutputStream.toByteArray();
	}

}
