package base.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import base.DAO.StudentDAO;
import base.controller.StudentController;
import base.model.Student;
import base.util.PrintUtil;

public class StudentTest {
	public static void testCrud(Connection connection) throws SQLException {
		PrintUtil.printMessage("Pruebas en el CRUD de alumnos");
		StudentController.listAll(connection);
		StudentController.getById(connection, 1);
		StudentController.getByNameAndLastname(connection, "Tetsurou", "Kurou");
		StudentController.getByLastname(connection, "Miya");
		StudentController.getBySimilarity(connection, "yama");
		StudentController.getCompleteProfile(connection, 1);
		Student addStudent = new Student("nombre", "apellido");
		StudentController.insert(connection, addStudent);
		List<Student> students = StudentDAO.findByNameAndLastname(connection, "nombre", "apellido");
		Student editStudent = students.get(0);
		editStudent.setName("NOMBRE");
		StudentController.edit(connection, editStudent);
		StudentController.delete(connection, editStudent.getId());
	}
}
