package base.test;

import java.sql.Connection;
import java.sql.SQLException;

import base.DAO.CourseDAO;
import base.controller.CourseController;
import base.model.Course;
import base.util.PrintUtil;

public class CourseTest {
	public static void testCrud(Connection connection) throws SQLException {
		PrintUtil.printMessage("Pruebas en el CRUD de cursos");
		CourseController.listAll(connection);
		CourseController.getById(connection, 1);
		CourseController.getByName(connection, "piano 1");
		Course addCourse = new Course("prueba");
		CourseController.insert(connection, addCourse);
		Course editCourse = CourseDAO.findByName(connection, "prueba");
		editCourse.setName("prueba editada");
		CourseController.edit(connection, editCourse);
		CourseController.delete(connection, editCourse.getId());
	}
}
