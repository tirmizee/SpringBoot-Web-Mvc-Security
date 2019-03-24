
public class Demo3 {

	public static void main(String[] args) {
		String reg2 = "^SpringBoot.[0-9]{2}-[0-9]{2}-[0-9]{4}.log$";

		//examples/test cases:

		System.out.println("V:1e0".matches(reg2));
		System.out.println("V:1e01".matches(reg2));
		System.out.println("V:1e3".matches(reg2));
		
		String dd = "fkdsmfkdsf".format("ddd%s", "4444");
		System.out.println(dd);
	}

}
