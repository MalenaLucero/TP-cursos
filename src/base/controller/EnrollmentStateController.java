package base.controller;

import java.sql.Connection;
import java.sql.SQLException;

import base.DAO.EnrollmentDAO;
import base.DAO.EnrollmentStateDAO;
import base.enums.EnrollmentState;
import base.model.Enrollment;
import base.util.ResponseUtil;

public class EnrollmentStateController {
	public static void activateEnrollment(int id, Connection connection) throws SQLException {
		System.out.println("Activar inscripcion con ID " + id);
		Enrollment enrollment = EnrollmentDAO.findById(connection, id);
		if(enrollment == null) {
			System.out.println("Inscripcion no encontrada");
		} else {
			if(enrollment.getEnrollment_state().equals(EnrollmentState.activo.getValue())) {
				System.err.println("La inscripcion ya esta activada");
			} else {
				changeEnrollmentState(connection, enrollment, EnrollmentState.activo.getValue());
			} 
		}
	}
	
	public static void cancelEnrollment(int id, Connection connection) throws SQLException {
		System.out.println("Cancelar inscripcion con ID " + id);
		Enrollment enrollment = EnrollmentDAO.findById(connection, id);
		if(enrollment == null) {
			System.out.println("Inscripcion no encontrada");
		} else {
			if(enrollment.getEnrollment_state().equals(EnrollmentState.cancelado.getValue())) {
				System.err.println("La inscripcion ya esta cancelada");
			} else {
				changeEnrollmentState(connection, enrollment, EnrollmentState.cancelado.getValue());
			} 
		}
	}
	
	public static void activateEnrollmentsByStudent(Connection connection, int id_student) throws SQLException {
		System.out.println("Activar inscripciones del alumno con ID " + id_student);
		int res = EnrollmentStateDAO.changeByStudent(connection, id_student, EnrollmentState.activo.getValue());
		ResponseUtil.numberOfModifiedElements(res);
	}
	
	public static void cancelEnrollmentsByStudent(Connection connection, int id_student) throws SQLException {
		System.out.println("Cancelar inscripciones del alumno con ID " + id_student);
		int res = EnrollmentStateDAO.changeByStudent(connection, id_student, EnrollmentState.cancelado.getValue());
		ResponseUtil.numberOfModifiedElements(res);
	}

	private static void changeEnrollmentState(Connection connection, Enrollment enrollment, String state) throws SQLException {
		enrollment.setEnrollment_state(state);
		int res = EnrollmentDAO.edit(connection, enrollment);
		ResponseUtil.editMessage(res);
	}
}
