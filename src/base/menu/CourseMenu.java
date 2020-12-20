package base.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.DAO.CourseDAO;
import base.controller.CourseController;
import base.model.Course;

public class CourseMenu {
	public static void printMenu() {
		System.out.println("------ MENU DE CURSOS ------");
		System.out.println("1. Listar cursos");
		System.out.println("2. Buscar curso");
		System.out.println("3. Agregar curso");
		System.out.println("4. Modificar curso");
		System.out.println("5. Eliminar curso");
		System.out.println("0. Volver al menu principal");
		System.out.println();
	}
	
	public static void chooseMenuOption(Connection connection, int option) throws SQLException {
		switch(option) {
		case 1:
			CourseController.listAll(connection);
			break;
		case 2:
			CourseController.getById(connection, 1);
			break;
		case 3:
			CourseController.getByName(connection, "mongo db");
			break;
		case 4:
			Course course = new Course("python");
			CourseController.insert(connection, course);
			break;
		case 5:
			Course editCourse = CourseDAO.findById(connection, 15);
			editCourse.setCatedra(5);
			CourseController.edit(connection, editCourse);
			break;
		case 6:
			CourseController.delete(connection, 17);
			break;
		case 7:
			uploadCoursesBatch(connection);
		}
	}

	private static void uploadCoursesBatch(Connection connection) throws SQLException {
		List<Course> cursos = new ArrayList<Course>();
		createCoursesList("prueba", cursos);
		//for(Curso curso: cursos) CursoDAO.insert(curso, connection);
	}
	
	private static void createCoursesList(String nombre, List<Course> cursos) {
		int catedra = 0;
		Course curso = new Course(nombre, catedra);
		cursos.add(curso);
	}
}
