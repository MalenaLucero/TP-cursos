package base.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import base.DAO.EnrollmentDAO;
import base.model.Enrollment;
import base.model.Student;
import base.util.ResponseUtil;

public class EnrollmentController{
	public static void listAll(Connection connection, int limit) throws SQLException {
		System.out.println("Listado de inscripciones. Limite: " + limit);
		List<Enrollment> enrollments = EnrollmentDAO.getAll(connection, limit);
		if(enrollments.size() == 0) {
			System.err.println("No se encontraron inscripciones");
		} else {
			for(Enrollment enrollment: enrollments) {
				System.out.println(enrollment);
			}
		}
	}
	
	public static void getById(Connection connection, int id) throws SQLException {
		System.out.println("Buscar inscripcion por ID");
		Enrollment enrollment = EnrollmentDAO.findById(connection, id);
		if(enrollment == null) {
			System.err.println("No se encontro la inscripcion");
		} else {
			System.out.println(enrollment);
		}
	}
	
	public static void getByCourseAndStudent(Connection connection, int id_course, int id_student) throws SQLException {
		System.out.println("Buscar inscripcion por curso y alumno");
		Enrollment enrollment = EnrollmentDAO.findByCourseAndStudent(connection, id_course, id_student);
		if(enrollment == null) {
			System.err.println("No se encontraron inscripciones");
		} else {
			System.out.println(enrollment);
		}
	}
	
	public static void insert(Connection connection, Enrollment enrollment) throws SQLException {
		System.out.println("Agregar inscripcion");
		int res = EnrollmentDAO.insert(enrollment, connection);
		ResponseUtil.addMessage(res);
	}

	public static void update(Connection connection, Enrollment enrollment) throws SQLException {
		System.out.println("Editar inscripcion");
		int res = EnrollmentDAO.update(connection, enrollment);
		ResponseUtil.editMessage(res);
	}
	
	public static void delete(Connection connection, int id) throws SQLException {
		System.out.println("Eliminar inscripcion");
		int res = EnrollmentDAO.delete(id, connection);
		ResponseUtil.deleteMessage(res);
	}
	
	public static void getStudentsByCourse(Connection connection, int id_course) throws SQLException {
		System.out.println("Alumnos del curso con ID " + id_course);
		List<Student> students = EnrollmentDAO.getStudentsByCourse(connection, id_course);
		if(students.size() > 0) {
			for(Student student: students) System.out.println(student);
		} else {
			System.out.println("El curso no tiene alumnos");
		}
	}
	
	public static void getStudentsByCourseAndDivision(Connection connection, int id_course, String division) throws SQLException {
		System.out.println("Alumnos del curso con ID " + id_course + " comision " + division);
		List<Student> students = EnrollmentDAO.getStudentsByCourseAndDivision(connection, id_course, division);
		if(students.size() > 0) {
			for(Student student: students) System.out.println(student);
		} else {
			System.out.println("El curso no tiene alumnos");
		}
	}
}
