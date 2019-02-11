import java.util.Locale;

import org.springframework.util.StringUtils;

public class Demo {

	protected static String underscoreName(String name) {
		if (!StringUtils.hasLength(name)) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		result.append(lowerCaseName(name.substring(0, 1)));
		for (int i = 1; i < name.length(); i++) {
			String s = name.substring(i, i + 1);
			String slc = lowerCaseName(s);
			if (!s.equals(slc)) {
				result.append("_").append(slc);
			}
			else {
				result.append(s);
			}
		}
		return result.toString();
	}

	/**
	 * Convert the given name to lower case.
	 * By default, conversions will happen within the US locale.
	 * @param name the original name
	 * @return the converted name
	 * @since 4.2
	 */
	protected static String lowerCaseName(String name) {
		return name.toLowerCase(Locale.US);
	}

    public static void main(String[] args) {
       System.out.println(underscoreName("LastName"));
    }

}
