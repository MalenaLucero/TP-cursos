package base.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import base.DAO.StudentDAO;
import base.model.Student;
import base.util.ResponseUtil;

public class StudentController {
	public static void listAll(Connection connection) throws SQLException {
		System.out.println("Listado de alumnos");
		List<Student> students = StudentDAO.getAll(connection);
		if(students.size() == 0) {
			System.err.println("No se encontraron alumnos");
		} else {
			for(Student student: students) {
				System.out.println(student);
			}
		}
	}
	
	public static void getById(Connection connection, int id) throws SQLException {
		System.out.println("Buscar alumno por ID");
		Student student = StudentDAO.findById(connection, id);
		if(student == null) {
			System.err.println("No se encontro el alumno");
		} else {
			System.out.println(student);
		}
	}
	
	public static void getByLastname(Connection connection, String lastname) throws SQLException {
		System.out.println("Buscar alumno por apellido");
		List<Student> students = StudentDAO.findByLastname(connection, lastname);
		if(students.size() == 0) {
			System.err.println("No se encontraron alumnos");
		} else {
			for(Student student: students) {
				System.out.println(student);
			}
		}
	}
	
	public static void insert(Connection connection, Student student) throws SQLException {
		System.out.println("Agregar alumno");
		Student overlappingStudent = StudentDAO.findByNameAndLastname(connection, student.getName(), student.getLastname());
		if(overlappingStudent == null) {
			int res = StudentDAO.insert(student, connection);
			ResponseUtil.addMessage(res);
		} else {
			System.err.println("Ya existe un alumno con ese nombre y apellido");
		}	
	}

	public static void edit(Connection connection, Student student) throws SQLException {
		System.out.println("Editar alumno");
		int res = StudentDAO.edit(connection, student);
		ResponseUtil.editMessage(res);
	}
	
	public static void delete(Connection connection, int id) throws SQLException {
		System.out.println("Eliminar alumno");
		int res = StudentDAO.delete(id, connection);
		ResponseUtil.deleteMessage(res);
	}
}
