import java.util.Random;
import java.util.UUID;

import com.fasterxml.uuid.Generators;

public class Demo7 {

	public static void main(String[] args) {
		
		Random r = new Random();
		System.out.println(new UUID(r.nextLong(), r.nextLong()));
		System.out.println(UUID.randomUUID());
	
		 /***** Generate Version 1 UUID - Time-Based UUID *****/
        UUID uuid1 = Generators.timeBasedGenerator().generate();
        System.out.println("Generated Version 1 UUID?= " + uuid1.toString() + ", UUID Version?= " + uuid1.version() + ", UUID Variant?= " + uuid1.version() + "\n");
 
        /***** Generate Version 4 UUID - Randomly Generated UUID *****/
        UUID uuid2 = Generators.randomBasedGenerator().generate();
        System.out.println("Generated Version 4 UUID?= " + uuid2.toString() + ", UUID Version?= " + uuid2.version() + ", UUID Variant?= " + uuid2.version());
    
//        BeanUtils.copyProperties(source, target);
        
	}

}
