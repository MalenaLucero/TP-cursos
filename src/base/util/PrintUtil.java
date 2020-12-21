package base.util;

public class PrintUtil {
	public static void printMessage(String message) {
		System.out.println();
		System.out.println(message);
	}
	
	public static void chooseOptionMessage() {
		System.out.println();
		System.out.println("Ingrese una opcion");
	}
	
	public static void invalidOptionMessage() {
		System.out.println();
		System.out.println("Opcion invalida. Ingresala de nuevo.");
	}
}
