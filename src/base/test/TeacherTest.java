package base.test;

import java.sql.Connection;
import java.sql.SQLException;

import base.DAO.TeacherDAO;
import base.controller.TeacherController;
import base.model.Teacher;
import base.util.PrintUtil;

public class TeacherTest {
	public static void testCrud(Connection connection) throws SQLException {
		PrintUtil.printMessage("Pruebas en el CRUD de docentes");
		TeacherController.getNextBirthday(connection);
		TeacherController.getCurrentMonthBirthdays(connection);
		TeacherController.listAll(connection);
		TeacherController.getById(connection, 3);
		TeacherController.getByLastname(connection, "Yoongi", "Min");
		Teacher addTeacher = new Teacher("nombre", "apellido");
		TeacherController.insert(connection, addTeacher);
		Teacher editTeacher = TeacherDAO.findByNameLastname(connection, addTeacher.getName(), addTeacher.getLastname());
		editTeacher.setName("NOMBRE");
		TeacherController.edit(connection, editTeacher);
		TeacherController.delete(connection, editTeacher.getId());
		TeacherController.getCoursesByTeacher(connection, 3);
		TeacherController.getBySimilarity(connection, "민");
	}
}
