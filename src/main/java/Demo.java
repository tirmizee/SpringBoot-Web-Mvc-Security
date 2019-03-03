import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
    	for (int i = 1801,j=1; i <= 2700; i++,j++) {
			System.out.println("UPDATE users SET profile_id = "+ j +" where user_id = " + i +";");
		}
    }

}
