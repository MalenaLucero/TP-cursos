package base.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.DAO.TeacherDAO;
import base.controller.TeacherController;
import base.model.Teacher;

public class MenuTeacher {
	public static void printMenu() {
		System.out.println("------ MENU DE DOCENTES ------");
		System.out.println("1. Listar");
		System.out.println("2. Buscar");
		System.out.println("3. Agregar");
		System.out.println("4. Modificar");
		System.out.println("5. Eliminar");
		System.out.println("0. Volver al menu principal");
		System.out.println();
	}
	
	public static void chooseMenuOption(Connection connection, int option) throws SQLException {
		switch(option) {
		case 1:
			TeacherController.listAll(connection);
			break;
		case 2:
			TeacherController.getById(connection, 3);
			break;
		case 3:
			TeacherController.getByLastname(connection, "Namjoon", "Kim");
			break;
		case 4:
			Teacher teacher = new Teacher("prueba", "prueba");
			TeacherController.insert(connection, teacher);
			break;
		case 5:
			Teacher editTeacher = TeacherDAO.findById(connection, 10);
			editTeacher.setAlternative_name1("nombre alternativo");
			TeacherController.edit(connection, editTeacher);
			break;
		case 6:
			TeacherController.delete(connection, 10);
			break;
		case 7:
			uploadTeacherBatch(connection);
			break;
		}
	}
	
	private static void uploadTeacherBatch(Connection connection) throws SQLException {
		List<Teacher> teachers = new ArrayList<Teacher>();
		createTeachersList("prueba prueba prueba", teachers);
		//for(Teacher teacher: teachers) TeacherDAO.insert(teacher, connection);
	}
	
	private static void createTeachersList(String nombre, List<Teacher> teachers) {
		String[] strings = nombre.split(" ");
		String name = strings[0];
		String lastname = strings[1];
		String kanji = strings[2];
		Teacher teacher = new Teacher(name, lastname, kanji);
		teachers.add(teacher);
	}
}
