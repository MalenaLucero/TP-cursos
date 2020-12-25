package base.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import base.DAO.TeacherDAO;
import base.model.Course;
import base.model.Enrollment;
import base.model.Teacher;
import base.util.PrintUtil;
import base.util.ResponseUtil;
import base.util.StringUtil;

public class TeacherController {
	public static void listAll(Connection connection) throws SQLException {
		System.out.println("Listado de docentes");
		List<Teacher> teachers = TeacherDAO.getAll(connection);
		printTeachers(teachers);
	}
	
	public static void getById(Connection connection, int id) throws SQLException {
		System.out.println("Buscar docente por ID");
		Teacher teacher = TeacherDAO.findById(connection, id);
		printTeacher(teacher);
	}
	
	public static void getByLastname(Connection connection, String name, String lastname) throws SQLException {
		System.out.println("Buscar docente por nombre y apellido");
		Teacher teacher = TeacherDAO.findByNameLastname(connection, name, lastname);
		printTeacher(teacher);
	}
	
	public static void insert(Connection connection, Teacher teacher) throws SQLException {
		System.out.println("Agregar docente");
		int res = TeacherDAO.insert(teacher, connection);
		ResponseUtil.addMessage(res);
	}

	public static void edit(Connection connection, Teacher teacher) throws SQLException {
		System.out.println("Editar docente");
		int res = TeacherDAO.edit(connection, teacher);
		ResponseUtil.editMessage(res);
	}
	
	public static void delete(Connection connection, int id) throws SQLException {
		System.out.println("Eliminar docente");
		int res = TeacherDAO.delete(id, connection);
		ResponseUtil.deleteMessage(res);
	}
	
	public static void getCoursesByTeacher(Connection connection, int id_teacher) throws SQLException {
		System.out.println("Cursos del docente con ID " + id_teacher);
		List<Map<String, Object>> list = TeacherDAO.getCoursesByTeacher(connection, id_teacher);
		System.out.println(list.get(0).get("teacher"));
		for(Map<String, Object> map: list) {
			Course course = (Course) map.get("course");
			Enrollment enrollment = (Enrollment) map.get("enrollment");
			System.out.print("Materia: " + course.getName());
			System.out.print(", Comision: " + enrollment.getDivision());
			System.out.println();
		}
	}
	
	public static void getCompleteProfile(Connection connection, int id) throws SQLException {
		Teacher teacher = TeacherDAO.findById(connection, id);
		System.out.println("ID " + teacher.getId());
		System.out.println("Nombre: " + teacher.getName());
		System.out.println("Apellido: " + teacher.getLastname());
		PrintUtil.printIfNotBlank("Nombre alternativo: ", teacher.getAlternative_name1());
		PrintUtil.printIfNotBlank("Segundo nombre alternativo: ", teacher.getAlternative_name2());
		PrintUtil.printIfNotBlank("Fecha de nacimiento: ", StringUtil.dateToString(teacher.getBirthdate()));
		PrintUtil.printIfNotBlank("Foto de perfil: ", teacher.getImage());
		if(!StringUtil.isBlank(teacher.getDescription())) {
			System.out.println("Descripcion:");
			System.out.println(teacher.getDescription());
		}
	}
	
	public static void getBySimilarity(Connection connection, String searchString) throws SQLException {
		System.out.println("Docentes con nombre similar a " + searchString);
		List<Teacher> teachers = TeacherDAO.getBySimilarity(connection, searchString);
		printTeachers(teachers);
	}
	
	private static void printTeacher(Teacher teacher) {
		if(teacher == null) {
			System.err.println("No se encontro el docente");
		} else {
			System.out.println(teacher);
		}
	}
	
	private static void printTeachers(List<Teacher> teachers) {
		if(teachers.size() == 0) {
			System.err.println("No se encontraron docentes");
		} else {
			for(Teacher teacher: teachers) {
				System.out.println(teacher);
			}
		}
	}
}
