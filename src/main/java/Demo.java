import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Demo {

    public static void main(String[] args) throws WriterException, IOException {
//    	String ff = "C:\\Users\\User\\workspace\\SpringBoot\\log";
//    	File directory = new File(ff);
//    	if (directory.isDirectory()) {
//			System.out.println("Directory : " + directory.getName());
//			for (File file : directory.listFiles()) {
//				if (file.isFile()) {
//					String ext1 = FilenameUtils.getExtension(file.getPath()); 
//					System.out.println("     File : " + file.getName() + " : " + ext1);
//					List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8.name());
//					for (String string : lines) {
//						
//						
//					}
//				}
//			}
//		}
    	try {
    		 final Map<EncodeHintType, Object> hintMap = new HashMap<>();
    		 hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    		 hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
    		 hintMap.put(EncodeHintType.DATA_MATRIX_SHAPE, SymbolShapeHint.FORCE_SQUARE);
			Writer writer = new Code128Writer();
			BitMatrix bitMatrix = writer.encode("888888888888888888888888", BarcodeFormat.CODE_128, 350, 80,hintMap);
			
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
			System.out.println(Base64.encodeBase64String(byteArrayOutputStream.toByteArray()));
		} catch (Exception e) {
		}
    	
    	
    	
    }

}
