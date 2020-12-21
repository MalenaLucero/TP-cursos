package base.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import base.DAO.CourseDAO;
import base.controller.CourseController;
import base.model.Course;
import base.util.InputUtil;
import base.util.PrintUtil;

public class CourseMenu {
	public static void printMenu() {
		System.out.println();
		System.out.println("------ MENU DE CURSOS ------");
		System.out.println("1. Listar cursos");
		System.out.println("2. Buscar curso por ID");
		System.out.println("3. Buscar curso por nombre");
		System.out.println("4. Agregar curso");
		System.out.println("5. Editar curso");
		System.out.println("6. Eliminar curso");
		System.out.println("0. Volver al menu principal");
	}
	
	public static void chooseMenuOption(Scanner sc, Connection connection, int option) throws SQLException {
		switch(option) {
		case 1:
			CourseController.listAll(connection);
			break;
		case 2:
			int id = InputUtil.inputInt(sc, "Ingrese ID del curso:");
			CourseController.getById(connection, id);
			break;
		case 3:
			String name = InputUtil.inputString(sc, "Ingrese nombre del curso:");
			CourseController.getByName(connection, name);
			break;
		case 4:
			Course course = createCourse(sc);
			CourseController.insert(connection, course);
			break;
		case 5:
			Course editCourse = editCourse(sc, connection);
			CourseController.edit(connection, editCourse);
			break;
		case 6:
			int deleteId = InputUtil.inputInt(sc, "Ingrese ID del curso:");
			CourseController.delete(connection, deleteId);
			break;
		default:
			PrintUtil.invalidOptionMessage();
			break;
		}
	}

	private static Course createCourse(Scanner sc) {
		String name = InputUtil.inputStringNotBlank(sc, "Ingrese el nombre del curso");
		int id_catedra = InputUtil.inputInt(sc, "Ingresar ID de catedra (o 0 si se desconoce)");
		return new Course(name, id_catedra);
	}
	
	private static Course editCourse(Scanner sc, Connection connection) throws SQLException {
		int id = InputUtil.inputInt(sc, "Ingrese ID del curso a editar:");
		Course course = CourseDAO.findById(connection, id);
		boolean editName = Util.confirmEditMessage(sc, "nombre", course.getName());
		if(editName) {
			String name = InputUtil.inputStringNotBlank(sc, "Ingrese el nuevo valor:");
			course.setName(name);
		}
		boolean editCatedra = Util.confirmEditMessage(sc, "catedra", Integer.toString(course.getCatedra()));
		if(editCatedra) {
			int catedra = InputUtil.inputInt(sc, "Ingrese el nuevo valor");
			course.setCatedra(catedra);
		}
		return course;
	}
}
