package base.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import base.controller.StudentController;
import base.model.Student;
import base.util.InputUtil;

public class StudentMenu {
	public static void printMenu() {
		System.out.println();
		System.out.println("------ MENU DE ALUMNOS ------");
		System.out.println("1. Listar alumnos");
		System.out.println("2. Buscar alumno por ID");
		System.out.println("3. Buscar alumnos por apellido");
		System.out.println("4. Agregar alumno");
		System.out.println("5. Editar alumno");
		System.out.println("6. Eliminar alumno");
		System.out.println("0. Volver al menu principal");
	}
	
	public static void chooseMenuOption(Scanner sc, Connection connection, int option) throws SQLException {
		switch(option) {
		case 1:
			StudentController.listAll(connection);
			break;
		case 2:
			int id = InputUtil.inputInt(sc, "Ingrese ID del alumno:");
			StudentController.getById(connection, id);
			break;
		case 3:
			String lastname = InputUtil.inputString(sc, "Ingrese apellido:");
			StudentController.getByLastname(connection, lastname);
			break;
		case 4:
			Student student = createStudent(sc);
			StudentController.insert(connection, student);
			break;
		case 5:
			Student editStudent = editStudent(sc, connection);
			StudentController.edit(connection, editStudent);
			break;
		case 6:
			int deleteId = InputUtil.inputInt(sc, "Ingrese ID del alumno:");
			StudentController.delete(connection, deleteId);
			break;
		}
	}
	
	private static Student createStudent(Scanner sc) {
		String name = InputUtil.inputStringNotBlank(sc, "Ingrese nombre");
		String lastname = InputUtil.inputLine(sc, "Ingrese apellido:");
		return new Student(name, lastname, null);
	}
	
	private static base.model.Student editStudent(Scanner sc, Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}
}
