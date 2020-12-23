package base.test;

import java.sql.Connection;
import java.sql.SQLException;

import base.controller.EnrollmentStateController;
import base.enums.EnrollmentState;
import base.util.PrintUtil;

public class EnrollmentStateTest {
	public static void testStateChange(Connection connection) throws SQLException {
		PrintUtil.printMessage("Pruebas en editar estado de inscripcion");
		EnrollmentStateController.changeByEnrollmentId(14, connection, EnrollmentState.cancelado);
		EnrollmentStateController.changeByEnrollmentId(14, connection, EnrollmentState.activo);
		EnrollmentStateController.changeByStudentId(connection, 1, EnrollmentState.cancelado);
		EnrollmentStateController.changeByStudentId(connection, 1, EnrollmentState.activo);
		EnrollmentStateController.changeByCourseAndDivision(connection, 1, "A", EnrollmentState.cancelado);
		EnrollmentStateController.changeByCourseAndDivision(connection, 1, "A", EnrollmentState.activo);
		EnrollmentStateController.changeByCourse(connection, 1, EnrollmentState.cancelado);
		EnrollmentStateController.changeByCourse(connection, 1, EnrollmentState.activo);
	}
}
