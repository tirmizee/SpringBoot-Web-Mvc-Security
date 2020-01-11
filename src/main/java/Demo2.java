import java.util.Random;

public class Demo2 {

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 12; j++) {
				System.out.print(String.format("%.3f", new Random().nextDouble()));
				if (j<11) {
					System.out.print(",");
				}
			}
			System.out.println();
		}
		
	}

}
