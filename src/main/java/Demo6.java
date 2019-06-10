import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tirmizee.core.component.SOAPConnector;
import com.tirmizee.soap.schemas.GetCountryRequest;
import com.tirmizee.soap.schemas.GetCountryResponse;

public class Demo6 {

	public static void main(String...args) throws MalformedURLException, IOException {
		byte[] b = IOUtils.toByteArray((new URL("https://randomuser.me/api/portraits/med/men/66.jpg")).openStream());
		for (byte c : b) {
			System.out.println(c);
		}
		SOAPConnector soapConnector = new SOAPConnector();
		GetCountryRequest countryRequest = new GetCountryRequest();
		countryRequest.setName("Spain");
		GetCountryResponse countryResponse = (GetCountryResponse) soapConnector.callWebService("http://localhost:8888/ws/countries", countryRequest);
		System.out.println(countryResponse.getCountry().getCapital());
	}

}
