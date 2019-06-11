import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.compress.utils.IOUtils;

public class Demo6 {


	public static void main(String...args) throws MalformedURLException, IOException {
		byte[] b = IOUtils.toByteArray((new URL("https://randomuser.me/api/portraits/med/men/66.jpg")).openStream());
		for (byte c : b) {
			System.out.println(c);
		}
	}

}
