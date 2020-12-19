package base.menu;

import java.sql.Connection;
import java.sql.SQLException;

import base.DAO.EnrollmentDAO;
import base.controller.EnrollmentController;
import base.enums.EnrollmentState;
import base.model.Enrollment;

public class EnrollmentMenu {
	public static void printMenu() {
		System.out.println("------ MENU DE INSCRIPCION ------");
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
			EnrollmentController.listAll(connection);
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
		}
	}
}
