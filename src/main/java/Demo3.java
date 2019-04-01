import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Hex;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class Demo3 {

	public static void main(String[] args) throws WriterException, IOException {
//		String UUID = java.util.UUID.randomUUID().toString();
//		System.out.println(UUID);
//		System.out.println(Base64.encodeBase64String(UUID.getBytes()));
//		System.out.println(new String(Base64.decodeBase64(Base64.encodeBase64String(UUID.getBytes()))));	
		System.out.println(digest("Gdl@2019"));
	}

	public static byte[] generateQRCodeByte(String text, int width, int height) throws WriterException, IOException {
		BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		return pngOutputStream.toByteArray();
	}
	public static String digest(String plainText) {
		String encText = "";

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(plainText.getBytes());
			byte[] bytes = digest.digest();
			encText = new String(Hex.encode(bytes));
		} catch (NoSuchAlgorithmException var4) {
			;
		}

		return encText;
	}

	public static byte[] digestToByte(String plainText) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(plainText.getBytes());
			byte[] bytes = digest.digest();
			return bytes;
		} catch (NoSuchAlgorithmException var3) {
			return null;
		}
	}
	
}
