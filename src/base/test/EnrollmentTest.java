package base.test;

import java.sql.Connection;
import java.sql.SQLException;

import base.DAO.EnrollmentDAO;
import base.controller.EnrollmentController;
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
}
