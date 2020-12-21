package base.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import base.DAO.EnrollmentDAO;
import base.DAO.StudentDAO;
import base.controller.EnrollmentController;
import base.enums.EnrollmentState;
import base.model.Enrollment;
import base.model.Student;

public class EnrollmentMenu {
	public static void printMenu() {
		System.out.println();
		System.out.println("------ MENU DE INSCRIPCION ------");
		System.out.println("1. Listar inscripciones");
		System.out.println("2. Buscar inscripcion por ID");
		System.out.println("3. Buscar inscripcion por curso y alumno");
		System.out.println("4. Agregar inscripcion");
		System.out.println("5. Modificar inscripcion");
		System.out.println("6. Eliminar inscripcion");
		System.out.println("7. Cancelar inscripcion");
		System.out.println("8. Activar inscripcion");
		System.out.println("9. Listar alumnos por curso");
		System.out.println("10. Listar alumnos por curso y comision");
		System.out.println("11. Buscar notas por ID de inscripcion");
		System.out.println("0. Volver al menu principal");
	}
	
	public static void chooseMenuOption(Scanner sc, Connection connection, int option) throws SQLException {
		switch(option) {
		case 1:
			EnrollmentController.listAll(connection, 50);
			break;
		case 2:
			EnrollmentController.getById(connection, 1);
			break;
		case 3:
			EnrollmentController.getByCourseAndStudent(connection, 5, 1);
			break;
		case 4:
			Enrollment enrollment = new Enrollment(1, 3);
			EnrollmentController.insert(connection, enrollment);
			break;
		case 5:
			Enrollment editEnrollment = EnrollmentDAO.findById(connection, 10);
			editEnrollment.setCourseState("aprobado");
			editEnrollment.setYear(2020);
			EnrollmentController.edit(connection, editEnrollment);
			break;
		case 6:
			EnrollmentController.delete(connection, 11);
			break;
		case 7:
			EnrollmentController.changeEnrollmentState(connection, 5, 5, EnrollmentState.cancelado.getValue());
			break;
		case 8:
			EnrollmentController.changeEnrollmentState(connection, 5, 5, EnrollmentState.activo.getValue());
			break;
		case 9:
			EnrollmentController.getStudentsByCourse(connection, 5);
			break;
		case 10:
			EnrollmentController.getStudentsByCourseAndDivision(connection, 1, "A");
			break;
		case 11:
			EnrollmentController.getGrades(connection, 41);
			break;
		case 98:
			int catedras[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
			for (int i = 0; i < catedras.length; i++) {
				deleteRepeatedStudents(connection, catedras[i]);
			}
			break;
		case 99:
			uploadEnrollmentBatch(connection);
		}
	}

	private static void uploadEnrollmentBatch(Connection connection) throws SQLException {
		List<Enrollment> enrollments = new ArrayList<Enrollment>();
		Random rand = new Random();  
		int id_teacher = rand.nextInt(8) + 12;
		int courses[] = {};
		for(int i=0; i < courses.length; i++) {
			int quantity = rand.nextInt(8) + 3;
			List<Student> students = StudentDAO.getRandom(connection, quantity);
			for(Student student: students) {
				createEnrollmentList(student.getId(), courses[i], id_teacher, enrollments);
			}
		}
		//for(Enrollment enrollment: enrollments) EnrollmentDAO.insert(enrollment, connection);
	}

	private static void createEnrollmentList(int id_student, int id_course, int id_teacher, List<Enrollment> enrollments) {
		Random rand = new Random();  
		String enrollment_state = "activo";
		String division = "B";
		int grade1 = rand.nextInt(7) + 4;
		int grade2 = rand.nextInt(7) + 4;
		int average_grade = (grade1 + grade2) / 2;
		String course_state = average_grade > 5 ? "aprobado" : "desaprobado";
		int year = 2020;
		Enrollment enrollment = new Enrollment(id_course, id_student, enrollment_state, id_teacher,
				division, grade1, grade2, average_grade, course_state, year);
		enrollments.add(enrollment);
	}
	
	public static void deleteRepeatedStudents(Connection connection, int id_catedra) throws SQLException {
		List<Enrollment> enrollments = EnrollmentDAO.getByCatedra(connection, id_catedra);
		List<Enrollment> firstAppearance = new ArrayList<Enrollment>();
		List<Enrollment> repetitions = new ArrayList<Enrollment>();
		for(Enrollment enrollment: enrollments) {
			boolean isPresent = false;
			for(Enrollment firstTime: firstAppearance) {
				if(firstTime.getId_student() == enrollment.getId_student()) {
					isPresent = true;
				}
			}
			if(isPresent) {
				repetitions.add(enrollment);
			} else {
				firstAppearance.add(enrollment);
			}
		}
		System.out.println("Repetitions");
		for(Enrollment a: repetitions) {
			System.out.println(a);
			EnrollmentDAO.delete(a.getId(), connection);
		}
	}
}
