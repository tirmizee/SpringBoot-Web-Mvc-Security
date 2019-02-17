import org.springframework.security.core.context.SecurityContextHolder;

public class Demo {

    public static void main(String[] args) {
    	
    	SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
