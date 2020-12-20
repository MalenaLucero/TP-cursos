package base.test;

import java.sql.Connection;
import java.sql.SQLException;

import base.DAO.EnrollmentDAO;
import base.controller.EnrollmentController;
import base.enums.EnrollmentState;
import base.model.Enrollment;

public class EnrollmentTest {
	public static void testCrud(Connection connection) throws SQLException {
		EnrollmentController.listAll(connection, 50);
		EnrollmentController.getById(connection, 14);
		EnrollmentController.getByCourseAndStudent(connection, 1, 62);
		Enrollment addEnrollment = new Enrollment(1, 3);
		EnrollmentController.insert(connection, addEnrollment);
		Enrollment editEnrollment = EnrollmentDAO.findByCourseAndStudent(connection, addEnrollment.getId_course(), addEnrollment.getId_student());
		editEnrollment.setGrade1(8);
		EnrollmentController.edit(connection, editEnrollment);
		EnrollmentController.delete(connection, editEnrollment.getId());
	}
	
	public static void testChangeState(Connection connection) throws SQLException {
		Enrollment enrollment = EnrollmentDAO.findById(connection, 14);
		if(enrollment.getEnrollment_state().equals(EnrollmentState.cancelado.getValue())) {
			EnrollmentController.changeEnrollmentState(connection, enrollment.getId_course(),
					enrollment.getId_student(), EnrollmentState.activo.getValue());
			EnrollmentController.changeEnrollmentState(connection, enrollment.getId_course(),
					enrollment.getId_student(), EnrollmentState.cancelado.getValue());
		} else {
			EnrollmentController.changeEnrollmentState(connection, enrollment.getId_course(),
					enrollment.getId_student(), EnrollmentState.cancelado.getValue());
			EnrollmentController.changeEnrollmentState(connection, enrollment.getId_course(),
					enrollment.getId_student(), EnrollmentState.activo.getValue());
		}
	}
	
	public static void testStudentsSearch(Connection connection) throws SQLException {
		EnrollmentController.getStudentsByCourse(connection, 1);
		EnrollmentController.getStudentsByCourseAndDivision(connection, 1, "A");
	}
	
	public static void testGradesSearch(Connection connection) throws SQLException {
		EnrollmentController.getGrades(connection, 14);
	}
}
