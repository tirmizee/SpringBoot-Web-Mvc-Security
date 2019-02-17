import java.util.Locale;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterBatchUpdateUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.util.StringUtils;

import com.tirmizee.core.security.UserProfile;

public class Demo {

    public static void main(String[] args) {
    	
    	System.out.println(new UserProfile.Builder().username("tirmizeeq").build().hashCode());
    	System.out.println(new UserProfile.Builder().username("tirmizee").build().hashCode());
    }

}
