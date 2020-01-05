import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Demo2 {

	public static void main(String[] args) throws Exception {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(11);
		System.out.println(bCryptPasswordEncoder.encode("password"));
		
	}

}
