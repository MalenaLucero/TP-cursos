package base.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import base.DAO.EnrollmentDAO;
import base.controller.EnrollmentController;
import base.enums.EnrollmentState;
import base.model.Enrollment;
import base.util.InputUtil;

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
		System.out.println("7. Listar alumnos por curso");
		System.out.println("8. Listar alumnos por curso y comision");
		System.out.println("0. Volver al menu principal");
	}
	
	public static void chooseMenuOption(Scanner sc, Connection connection, int option) throws SQLException {
		switch(option) {
		case 1:
			int limit = InputUtil.inputInt(sc, "Ingrese la cantidad de registros");
			EnrollmentController.listAll(connection, limit);
			break;
		case 2:
			int id = InputUtil.inputInt(sc, "Ingrese ID de la inscripcion:");
			EnrollmentController.getById(connection, id);
			break;
		case 3:
			int id_course = InputUtil.inputInt(sc, "Ingrese ID del curso:");
			int id_student = InputUtil.inputInt(sc, "Ingrese ID del alumno:");
			EnrollmentController.getByCourseAndStudent(connection, id_course, id_student);
			break;
		case 4:
			Enrollment enrollment = createEnrollment(sc);
			EnrollmentController.insert(connection, enrollment);
			break;
		case 5:
			Enrollment editEnrollment = editEnrollment(sc, connection);
			EnrollmentController.edit(connection, editEnrollment);
			break;
		case 6:
			int deleteId = InputUtil.inputInt(sc, "Ingrese ID de la inscripcion:");
			EnrollmentController.delete(connection, deleteId);
			break;
		case 7:
			int courseId = InputUtil.inputInt(sc, "Ingrese ID del curso:");
			EnrollmentController.getStudentsByCourse(connection, courseId);
			break;
		case 8:
			int idCourse = InputUtil.inputInt(sc, "Ingrese ID del curso:");
			String division = InputUtil.inputSingleWord(sc, "Ingrese la comision");
			EnrollmentController.getStudentsByCourseAndDivision(connection, idCourse, division);
			break;
		}
	}

	private static Enrollment createEnrollment(Scanner sc) {
		int id_course = InputUtil.inputInt(sc, "Ingrese ID del curso");
		int id_student = InputUtil.inputInt(sc, "Ingrese ID del alumno");
		Enrollment enrollment = new Enrollment(id_course, id_student);
		if(Util.confirmOptionalField(sc, "comision y docente")) {
			String division = InputUtil.inputSingleWord(sc, "Ingresar la comision");
			enrollment.setDivision(division.toUpperCase());
			int id_docente = InputUtil.inputInt(sc, "Ingresar ID del docente");
			enrollment.setId_teacher(id_docente);
		}
		if(Util.confirmOptionalField(sc, "estado de inscripcion (valor por default: activo)")) {
			int state = InputUtil.inputInt(sc, "Ingrese 1 para cargar estado de inscripcion como CANCELADO");
			if(state == 1) enrollment.setEnrollment_state(EnrollmentState.cancelado.getValue());
		}
		if(Util.confirmOptionalField(sc, "notas")) {
			int grade1 = InputUtil.inputInt(sc, "Ingrese la nota 1");
			int grade2 = InputUtil.inputInt(sc, "Ingrese la nota 2");
			enrollment.setGrade1(grade1);
			enrollment.setGrade2(grade2);
		}
		if(Util.confirmOptionalField(sc, "ciclo lectivo (valor por default: 2021)")) {
			int year = InputUtil.inputInt(sc, "Ingrese el ciclo lectivo");
			enrollment.setYear(year);
		}
		return enrollment;
	}

	private static Enrollment editEnrollment(Scanner sc, Connection connection) throws SQLException {
		int id = InputUtil.inputInt(sc, "Ingrese ID de la inscripcion a editar:");
		Enrollment enrollment = EnrollmentDAO.findById(connection, id);
		if(Util.confirmEditMessage(sc, "curso", Integer.toString(enrollment.getId_course()))) {
			int id_course = InputUtil.inputInt(sc, "Ingrese el nuevo valor");
			enrollment.setId_course(id_course);
		}
		if(Util.confirmEditMessage(sc, "alumno", Integer.toString(enrollment.getId_student()))) {
			int id_student = InputUtil.inputInt(sc, "Ingrese el nuevo valor");
			enrollment.setId_student(id_student);
		}
		if(Util.confirmEditMessage(sc, "estado de inscripcion", enrollment.getEnrollment_state())) {
			String state = InputUtil.inputSingleWord(sc, "Ingrese el nuevo valor");
			enrollment.setEnrollment_state(state.toLowerCase());
		}
		if(Util.confirmEditMessage(sc, "docente", Integer.toString(enrollment.getId_teacher()))) {
			int id_teacher = InputUtil.inputInt(sc, "Ingrese el nuevo valor");
			enrollment.setId_teacher(id_teacher);
		}
		if(Util.confirmEditMessage(sc, "comision", enrollment.getDivision())) {
			String division = InputUtil.inputLine(sc, "Ingrese el nuevo valor:");
			enrollment.setDivision(division.toUpperCase());
		}
		if(Util.confirmEditMessage(sc, "nota 1", Integer.toString(enrollment.getGrade1()))) {
			int grade1 = InputUtil.inputInt(sc, "Ingrese el nuevo valor");
			enrollment.setGrade1(grade1);
		}
		if(Util.confirmEditMessage(sc, "nota 2", Integer.toString(enrollment.getGrade2()))) {
			int grade2 = InputUtil.inputInt(sc, "Ingrese el nuevo valor");
			enrollment.setGrade2(grade2);
		}
		if(Util.confirmEditMessage(sc, "ciclo lectivo", Integer.toString(enrollment.getYear()))) {
			int year = InputUtil.inputInt(sc, "Ingrese el nuevo valor");
			enrollment.setYear(year);
		}
		return enrollment;
	}
}
