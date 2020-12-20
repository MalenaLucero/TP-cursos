package base;

import java.sql.Connection;
import java.sql.SQLException;

import base.DAO.AdminDB;
import base.menu.CourseMenu;
import base.menu.EnrollmentMenu;
import base.menu.MenuTeacher;
import base.menu.StudentMenu;
import base.test.CourseTest;
import base.test.EnrollmentTest;
import base.test.StudentTest;
import base.test.TeacherTest;
import base.util.MenuUtil;

//1. ABML de profes
//2. Inscripcion y cancelacion de inscripcion -> columna estado_inscripcion 1 Activo 0:Cancelado
// Inscripcion y cancelacion por Id de estudiante y Id de curso
//3. Agregar 2 notas a la tabla inscripcion (int)
//4. Agregar columna id_profesor en la inscripcion
//5. Agregar columna comision (varchar) en la inscripcion
//5. 2 Listados de inscripcion:
//a - esutiantes de un curso seleccionado (a eleccion)
//b - notas de un estudiante en un curso

public class AppCursos {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection connection = AdminDB.obtenerConexion();
		System.out.println("Bienvenido al sistema de cursos");
		MenuUtil.printMainMenu();
		int option = 99;
	
		switch(option) {
		case 1:
			course(connection);
			break;
		case 2: 
			student(connection);
			break;
		case 3:
			enrollment(connection);
			break;
		case 4:
			teacher(connection);
			break;
		case 99:
			runTests(connection);
		}
		System.out.println("Programa finalizado");
	}

	private static void runTests(Connection connection) throws SQLException {
		CourseTest.testCrud(connection);
		StudentTest.testCrud(connection);
		TeacherTest.testCrud(connection);
		EnrollmentTest.testCrud(connection);
		EnrollmentTest.testChangeState(connection);
		EnrollmentTest.testStudentsSearch(connection);
		EnrollmentTest.testGradesSearch(connection);
	}

	private static void course(Connection connection) throws SQLException {
		CourseMenu.printMenu();
		int option = 1;
		CourseMenu.chooseMenuOption(connection, option);
	}
	
	private static void student(Connection connection) throws SQLException {
		StudentMenu.printMenu();
		int option = 7;
		StudentMenu.chooseMenuOption(connection, option);
	}
	
	private static void enrollment(Connection connection) throws SQLException {
		EnrollmentMenu.printMenu();
		int option = 11;
		EnrollmentMenu.chooseMenuOption(connection, option);
	}
	
	private static void teacher(Connection connection) throws SQLException {
		MenuTeacher.printMenu();
		int option = 7;
		MenuTeacher.chooseMenuOption(connection, option);
	}
}
