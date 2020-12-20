package base.util;

public class StringUtil {
	public static boolean isBlank(String string) {
		if(string != null && string.length() > 0) {
			return false;
		} else {
			return true;
		}
	}
}
