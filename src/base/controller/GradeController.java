package base.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import base.DAO.GradeDAO;
import base.model.Course;
import base.model.Enrollment;
import base.model.Student;

public class GradeController {
	public static void getByStudentAndCourse(Connection connection, int id_student, int id_course) throws SQLException {
		Map<String, Object> gradesMap = GradeDAO.getByStudentAndCourse(connection, id_student, id_course);
		Student student = (Student) gradesMap.get("student");
		Enrollment enrollment = (Enrollment) gradesMap.get("enrollment");
		System.out.println(student);
		System.out.println("Nota 1: " + enrollment.getGrade1());
		System.out.println("Nota 2: " + enrollment.getGrade2());
		System.out.println("Promedio: " + enrollment.getAverage_grade());
		System.out.println("Estado de la cursada: " + enrollment.getCourseState());
	}
	
	public static void getByStudent(Connection connection, int id_student) throws SQLException {
		List<Map<String, Object>> gradesList = GradeDAO.getByStudent(connection, id_student);
		System.out.println(gradesList.get(0).get("student"));
		for(Map<String, Object> map: gradesList) {
			Course course = (Course) map.get("course");
			Enrollment enrollment = (Enrollment) map.get("enrollment");
			System.out.println("Materia: " + course.getName());
			System.out.print("Nota 1: " + enrollment.getGrade1());
			System.out.print(", Nota 2: " + enrollment.getGrade2());
			System.out.print(", Promedio: " + enrollment.getAverage_grade());
			System.out.print(", Estado de la cursada: " + enrollment.getCourseState());
			System.out.println();
		}
	}
}
