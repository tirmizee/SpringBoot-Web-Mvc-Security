import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import com.tirmizee.core.utilities.DateUtils;

public class Demo6 {

	public static void main(String...args) {

		Timestamp timestamp = DateUtils.nowTimestamp();
		Timestamp timestamp2 = DateUtils.nowTimestampPlusMinutes(15);
		LocalDateTime date = timestamp.toLocalDateTime();
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(date.getMinute());
		
		
	}

}
