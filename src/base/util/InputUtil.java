package base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputUtil {
	public static int inputInt(Scanner sc) {
		PrintUtil.chooseOptionMessage();
		return sc.nextInt();
	}
	
	public static int inputInt(Scanner sc, String message) {
		PrintUtil.printMessage(message);
		return sc.nextInt();
	}
	
	public static String inputSingleWord(Scanner sc, String message) {
		PrintUtil.printMessage(message);
		return sc.next();
	}
	
	public static String inputString(Scanner sc, String message) {
		PrintUtil.printMessage(message);
		sc.nextLine();
		return sc.nextLine();
	}
	
	public static String inputLine(Scanner sc, String message) {
		PrintUtil.printMessage(message);
		return sc.nextLine();
	}
	
	public static String inputStringNotBlank(Scanner sc, String message) {
		String input = null;
		while(StringUtil.isBlank(input)) {
			PrintUtil.printMessage(message);
			sc.nextLine();
			input = sc.nextLine();
			if(StringUtil.isBlank(input)) {
				System.out.println("Texto ingresado invalido");
			}
		}
		return input;
	}
	
	public static Date inputDate(Scanner sc, String message, String format) throws ParseException {
		String input = InputUtil.inputSingleWord(sc, message + " en formato " + format);
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(input);
	}
}
