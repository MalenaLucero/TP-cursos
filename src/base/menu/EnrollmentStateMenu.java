package base.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import base.controller.EnrollmentStateController;
import base.util.InputUtil;

public class EnrollmentStateMenu {
	public static void printMenu() {
		System.out.println();
		System.out.println("------ MENU DE ESTADO DE INSCRIPCIONES ------");
		System.out.println("1. Activar inscripcion por ID");
		System.out.println("2. Cancelar inscripcion por ID");
		System.out.println("3. Activar inscripciones por alumno");
		System.out.println("4. Cancelar inscripciones por alumno");
		System.out.println("5. Activar inscripciones por comision");
		System.out.println("6. Cancelar inscripciones por comision");
		System.out.println("7. Activar inscripciones por curso");
		System.out.println("8. Cancelar inscripciones por curso");
		System.out.println("0. Volver al menu principal");
	}
	
	public static void chooseMenuOption(Scanner sc, Connection connection, int option) throws SQLException {
		switch(option) {
		case 1:
			int id = InputUtil.inputInt(sc, "Ingrese ID de la inscripcion:");
			EnrollmentStateController.activateEnrollment(id, connection);
			break;
		case 2:
			int id_enrollment = InputUtil.inputInt(sc, "Ingrese ID de la inscripcion:");
			EnrollmentStateController.cancelEnrollment(id_enrollment, connection);
			break;
		case 3:
			int id_student = InputUtil.inputInt(sc, "Ingrese ID del alumno");
			EnrollmentStateController.activateEnrollmentsByStudent(connection, id_student);
			break;
		case 4:
			int idStudent = InputUtil.inputInt(sc, "Ingrese ID del alumno");
			EnrollmentStateController.cancelEnrollmentsByStudent(connection, idStudent);
		}
	}

	
}
