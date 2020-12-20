package base.test;

import java.sql.Connection;
import java.sql.SQLException;

import base.DAO.StudentDAO;
import base.controller.StudentController;
import base.model.Student;

public class StudentTest {
	public static void testCrud(Connection connection) throws SQLException {
		System.out.println("Pruebas en el CRUD de alumnos");
		StudentController.listAll(connection);
		StudentController.getById(connection, 1);
		StudentController.getByLastname(connection, "Miya");
		Student addStudent = new Student("nombre", "apellido");
		StudentController.insert(connection, addStudent);
		Student editStudent = StudentDAO.findByNameAndLastname(connection, addStudent.getName(), addStudent.getLastname());
		editStudent.setName("NOMBRE");
		StudentController.edit(connection, editStudent);
		StudentController.delete(connection, editStudent.getId());
	}
}
