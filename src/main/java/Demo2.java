import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class Demo2 {

	public static void main(String[] args) throws Exception {
		
//		Document document = new Document();
//      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("barcode.pdf"));
//
//      document.open();
//      PdfContentByte cb = writer.getDirectContent();
//		
//		Barcode128 barcode128 = new Barcode128();
//		barcode128.setCode("memorynotfound.com");
//		barcode128.setCodeType(Barcode.CODE128);
//		com.itextpdf.text.Image code128Image = barcode128.createImageWithBarcode(cb, null, null);
////		document.add(code128Image);
//		
//		String encodedImage = Base64.encodeBase64String(code128Image.getRawData());
//		  
//		document.close();
		
		
//		Barcode128 barcode128 = new Barcode128();
//		barcode128.setCode("memorynotfound.com");
//		barcode128.setCodeType(Barcode.CODE128);
//		barcode128.setAltText("ssssssssssss");
//		java.awt.Image awtImage = barcode128.createAwtImage(Color.BLACK, Color.WHITE);
//		
//		BufferedImage bImage= new BufferedImage((int) barcode128.getBarcodeSize().getWidth(), (int) barcode128.getBarcodeSize().getHeight(), BufferedImage.TYPE_INT_RGB);
//		Graphics2D g = bImage.createGraphics();
//		g.drawImage(awtImage, 0, 0, null);
//		g.dispose();
//
//		ImageIO.write(bImage, "jpg", new File("code39.jpg"));
//		System.out.println(Base64.encodeBase64String(genBarcodeBuffer("BBOYZAA\n123456")));
		System.out.println(Base64.encodeBase64String(genBarcodeBuffer("BBOYZAA 123456")));
	}

	public static byte[] genBarcodeBuffer(String data) throws Exception {
		 
		final int dpi = 300;
		final double x = 3.37f;
 
		BitmapCanvasProvider canvas = null;
		BufferedImage barcodeImage = null;
		ByteArrayOutputStream baos = null;
		byte[] imageInByte = null;
 
		try {
			// final int dpi = 300; pixel 567 x 57
			Code128Bean bean = new Code128Bean();
			bean.setModuleWidth(UnitConv.in2mm(x / dpi));
			bean.setBarHeight(22d);
			bean.doQuietZone(false);
 
			canvas = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
 
			bean.generateBarcode(canvas, data);
			canvas.finish(); 
 
			barcodeImage = canvas.getBufferedImage();
			baos = new ByteArrayOutputStream();
			ImageIO.write(barcodeImage, "PNG", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
 
		} finally {
			if (null != baos) {
				baos.close();
			}
		}
 
		return imageInByte;
		// return ((DataBufferByte) barcodeImage.getRaster().getDataBuffer()).getData();
	}
	
}
//