import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Demo4 {

	public static void main(String[] args) {
		 Tesseract tesseract = new Tesseract();
		 try {
			tesseract.setDatapath("E:/Tesseract-OCR/tessdata");
			String text = tesseract.doOCR(new File("E:/Tesseract-OCR/Text_entropy.png"));		
			System.out.print(text);
		 } catch (TesseractException e) {		
			e.printStackTrace();
		}

	}

}
