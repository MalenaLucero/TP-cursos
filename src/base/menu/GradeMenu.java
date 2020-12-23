package base.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import base.controller.GradeController;

public class GradeMenu {
	public static void printMenu() {
		System.out.println();
		System.out.println("------ MENU DE NOTAS ------");
		System.out.println("1. Ver notas de alumno por curso");
		System.out.println("2. Ver todas las notas de un alumno");
		System.out.println("3. Ver todas las notas de un curso");
		System.out.println("4. Ver mejores promedios del instituto");
		System.out.println("5. Ver mejores promedios por curso");
		System.out.println("0. Volver al menu principal");
	}
	
	public static void chooseMenuOption(Scanner sc, Connection connection, int option) throws SQLException {
		switch(option) {
		case 1:
			GradeController.getByStudentAndCourse(connection, 1, 1);
			break;
		case 2: 
			GradeController.getByStudent(connection, 1);
			break;
		}
	}
}
