package base.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.DAO.StudentDAO;
import base.controller.StudentController;
import base.model.Student;

public class StudentMenu {
	public static void printMenu() {
		System.out.println("------ MENU DE ALUMNOS ------");
		System.out.println("1. Listar alumnos");
		System.out.println("2. Buscar alumno");
		System.out.println("3. Agregar alumno");
		System.out.println("4. Modificar alumno");
		System.out.println("5. Eliminar alumno");
		System.out.println("0. Volver al menu principal");
		System.out.println();
	}
	
	public static void chooseMenuOption(Connection connection, int option) throws SQLException {
		switch(option) {
		case 1:
			StudentController.listAll(connection);
			break;
		case 2:
			StudentController.getById(connection, 2);
			break;
		case 3:
			StudentController.getByLastname(connection, "Kurou");
			break;
		case 4:
			Student student = new Student("Touru", "Oikawa", "及川徹");
			StudentController.insert(connection, student);
			break;
		case 5:
			Student editStudent = StudentDAO.findById(connection, 6);
			editStudent.setAlternative_name("木兎光太郎");
			StudentController.edit(connection, editStudent);
			break;
		case 6:
			StudentController.delete(connection, 2);
			break;
		case 7:
			uploadStudentBatch(connection);
		}
	}

	private static void uploadStudentBatch(Connection connection) throws SQLException {
		List<Student> students = new ArrayList<Student>();
		createStudentList("prueba prueba kanji", students);
		//for(Student student: students) StudentDAO.insert(student, connection);
	}
	
	private static void createStudentList(String nombre, List<Student> students) {
		String[] strings = nombre.split(" ");
		String name = strings[0];
		String lastname = strings[1];
		String kanji = strings[2];
		Student student = new Student(name, lastname, kanji);
		students.add(student);
	}
}
