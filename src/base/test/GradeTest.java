package base.test;

import java.sql.Connection;
import java.sql.SQLException;

import base.controller.GradeController;

public class GradeTest {
	public static void test(Connection connection) throws SQLException {
		GradeController.getByStudentAndCourse(connection, 1, 1);
		GradeController.getByStudent(connection, 1);
		GradeController.getByCourse(connection, 1);
		GradeController.getOverallBestAverage(connection);
		GradeController.getBestAverageByCourse(connection, 1);
	}
}
