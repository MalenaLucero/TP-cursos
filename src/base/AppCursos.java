package base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import base.DAO.AdminDB;
import base.menu.CourseMenu;
import base.menu.EnrollmentMenu;
import base.menu.MenuTeacher;
import base.menu.StudentMenu;
import base.test.CourseTest;
import base.test.EnrollmentTest;
import base.test.StudentTest;
import base.test.TeacherTest;
import base.util.InputUtil;
import base.util.MenuUtil;
import base.util.PrintUtil;

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
	public static void main(String[] args) {
		System.out.println("Bienvenido al sistema de cursos");
		MenuUtil.printMainMenu();
		Scanner sc = new Scanner(System.in);
		int option = InputUtil.inputInt(sc);
		
		try {
			PrintUtil.printMessage("Conectando a la base de datos...");
			Connection connection = AdminDB.obtenerConexion();
			System.out.println("Conexion establecida");
			
			while(option != 0) {
				switch(option) {
				case 1:
					course(sc, connection);
					break;
				case 2: 
					student(sc, connection);
					break;
				case 3:
					enrollment(sc, connection);
					break;
				case 4:
					teacher(sc, connection);
					break;
				case 99:
					runTests(connection);
					break;
				default:
					PrintUtil.invalidOptionMessage();
					break;
				}
				MenuUtil.printMainMenu();
				option = InputUtil.inputInt(sc);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		PrintUtil.printMessage("Programa finalizado");
	}

	private static void course(Scanner sc, Connection connection) throws SQLException {
		CourseMenu.printMenu();
		int option = InputUtil.inputInt(sc);
		while(option != 0) {
			CourseMenu.chooseMenuOption(sc, connection, option);
			option = InputUtil.inputInt(sc);
		}
	}
	
	private static void student(Scanner sc, Connection connection) throws SQLException {
		StudentMenu.printMenu();
		int option = 1;
		StudentMenu.chooseMenuOption(connection, option);
	}
	
	private static void enrollment(Scanner sc, Connection connection) throws SQLException {
		EnrollmentMenu.printMenu();
		int option = 1;
		EnrollmentMenu.chooseMenuOption(connection, option);
	}
	
	private static void teacher(Scanner sc,Connection connection) throws SQLException {
		MenuTeacher.printMenu();
		int option = 1;
		MenuTeacher.chooseMenuOption(connection, option);
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
}
