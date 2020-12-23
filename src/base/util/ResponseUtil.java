package base.util;

public class ResponseUtil {
	public static void addMessage(int res) {
		if(res == 1) {
			System.out.println("Se agrego el elemento");
		} else{
			System.err.println("No se agrego el elemento");
		}
	}
	
	public static void editMessage(int res) {
		if(res == 1) {
			System.out.println("Se realizo la modificacion");
		} else{
			System.err.println("No se realizo la modificacion");
		}
	}
	
	public static void deleteMessage(int res) {
		if(res == 1) {
			System.out.println("Se elimino el elemento");
		} else{
			System.err.println("No se elimino el elemento");
		}
	}
	
	public static void numberOfModifiedElements(int res) {
		if(res == 0) {
			System.err.println("No se realizaron cambios");
		} else if (res == 1) {
			System.out.println("Se modifico 1 elemento");
		} else {
			System.out.println("Se modificaron " + res + " elementos");
		}
	}
}
