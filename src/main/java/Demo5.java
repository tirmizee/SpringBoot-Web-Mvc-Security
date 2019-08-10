import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class Demo5 {

	public static void main(String[] args) throws IOException, EncryptedDocumentException, InvalidFormatException {
		for (int i = 1; i < 1000; i++) {
			String code = new String[]{"0018610","0014257","0012481","0010903","0014848","0019734","0015696","0015458","0013685","0011577"}[new Random().nextInt(10)];
			System.out.println("update profile set branch_code = '" + code + "' where profile_id = " + i +";");
		}
	}

}
