import java.util.Random;

public class Demo {

    public static void main(String[] args) {
    	
    	for (int i = 1; i < 1000; i++) {
			System.out.println(new Random().nextInt(1000) + 1);
		}
    	
    }

}
