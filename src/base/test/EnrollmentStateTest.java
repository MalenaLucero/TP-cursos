package base.test;

import java.sql.Connection;
import java.sql.SQLException;

import base.DAO.EnrollmentDAO;
import base.controller.EnrollmentStateController;
import base.enums.EnrollmentState;
import base.model.Enrollment;
import base.util.PrintUtil;

public class EnrollmentStateTest {
	public static void testStateChange(Connection connection) throws SQLException {
		PrintUtil.printMessage("Pruebas en editar estado de inscripcion");
		Enrollment enrollment = EnrollmentDAO.findById(connection, 14);
		if(enrollment.getEnrollment_state().equals(EnrollmentState.activo.getValue())) {
			EnrollmentStateController.cancelEnrollment(enrollment.getId(), connection);
			EnrollmentStateController.activateEnrollment(enrollment.getId(), connection);
		} else {
			EnrollmentStateController.activateEnrollment(enrollment.getId(), connection);
			EnrollmentStateController.cancelEnrollment(enrollment.getId(), connection);
		}
		EnrollmentStateController.cancelEnrollmentsByStudent(connection, 1);
		EnrollmentStateController.activateEnrollmentsByStudent(connection, 1);
	}
}
