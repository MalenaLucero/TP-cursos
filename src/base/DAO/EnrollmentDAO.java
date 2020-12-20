package base.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.model.Enrollment;
import base.model.Student;

public class EnrollmentDAO {
	public static List<Enrollment> getAll(Connection connection, int limit) throws SQLException {
		String listString = "SELECT * FROM inscripcion LIMIT ?";
		PreparedStatement listEnrollments = connection.prepareStatement(listString);
		listEnrollments.setInt(1, limit);
		ResultSet res = listEnrollments.executeQuery();
		List<Enrollment> enrollments = new ArrayList<Enrollment>();
		while(res.next()) {
			Enrollment enrollment = new Enrollment(res.getInt("id"), res.getInt("id_curso"), res.getInt("id_alumno"),
					res.getString("estado_inscripcion"), res.getInt("id_docente"), res.getString("comision"), res.getInt("nota1"),
					res.getInt("nota2"), res.getInt("promedio"), res.getString("estado_cursada"), res.getInt("ciclo_lectivo"));
			enrollments.add(enrollment);
		}
		return enrollments;
	}
	
	public static Enrollment findById(Connection connection, int id) throws SQLException {
		String listString = "SELECT * FROM inscripcion WHERE id= ?";
		PreparedStatement findEnrollment = connection.prepareStatement(listString);
		findEnrollment.setInt(1, id);
		ResultSet res = findEnrollment.executeQuery();
		Enrollment enrollment = null;
		if(res.next()) {
			enrollment = new Enrollment(res.getInt("id"), res.getInt("id_curso"), res.getInt("id_alumno"),
					res.getString("estado_inscripcion"), res.getInt("id_docente"), res.getString("comision"), res.getInt("nota1"),
					res.getInt("nota2"), res.getInt("promedio"), res.getString("estado_cursada"), res.getInt("ciclo_lectivo"));
		}
		return enrollment;
	}
	
	public static Enrollment findByCourseAndStudent(Connection connection, int id_course, int id_student) throws SQLException {
		String listString = "SELECT * FROM inscripcion WHERE id_curso = ? AND id_alumno = ?";
		PreparedStatement findEnrollments = connection.prepareStatement(listString);
		findEnrollments.setInt(1, id_course);
		findEnrollments.setInt(2, id_student);
		ResultSet res = findEnrollments.executeQuery();
		Enrollment enrollment = null;
		if(res.next()) {
			enrollment = new Enrollment(res.getInt("id"), res.getInt("id_curso"), res.getInt("id_alumno"),
					res.getString("estado_inscripcion"), res.getInt("id_docente"), res.getString("comision"), res.getInt("nota1"),
					res.getInt("nota2"), res.getInt("promedio"), res.getString("estado_cursada"), res.getInt("ciclo_lectivo"));
		}
		return enrollment;
	}
	
	public static int insert(Enrollment enrollment, Connection connection) throws SQLException {
		String insertString = "INSERT INTO inscripcion (id_curso, id_alumno, estado_inscripcion, id_docente, comision, nota1, nota2, promedio, estado_cursada, ciclo_lectivo)"
								+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement addEnrollment = connection.prepareStatement(insertString);
		addEnrollment.setInt(1, enrollment.getId_course());
		addEnrollment.setInt(2, enrollment.getId_student());
		addEnrollment.setString(3, enrollment.getEnrollment_state());
		Util.setPossibleNullInt(addEnrollment, 4, enrollment.getId_teacher());
		addEnrollment.setString(5, enrollment.getDivision());
		Util.setPossibleNullInt(addEnrollment, 6, enrollment.getGrade1());
		Util.setPossibleNullInt(addEnrollment, 7, enrollment.getGrade2());
		Util.setPossibleNullInt(addEnrollment, 8, enrollment.getAverage_grade());
		addEnrollment.setString(9, enrollment.getCourseState());
		addEnrollment.setInt(10, enrollment.getYear());
		return addEnrollment.executeUpdate();
	}
	
	public static int edit(Connection connection, Enrollment enrollment) throws SQLException {
		String editString = "UPDATE inscripcion SET id_curso = ?, id_alumno = ?, estado_inscripcion = ?,"
				+ "id_docente = ?, comision = ?, nota1 = ?, nota2 = ?, promedio = ?,"
				+ "estado_cursada = ?, ciclo_lectivo = ? WHERE id = ?";
		PreparedStatement editEnrollment = connection.prepareStatement(editString);
		editEnrollment.setInt(1, enrollment.getId_course());
		editEnrollment.setInt(2, enrollment.getId_student());
		editEnrollment.setString(3, enrollment.getEnrollment_state());
		Util.setPossibleNullInt(editEnrollment, 4, enrollment.getId_teacher());
		editEnrollment.setString(5, enrollment.getDivision());
		Util.setPossibleNullInt(editEnrollment, 6, enrollment.getGrade1());
		Util.setPossibleNullInt(editEnrollment, 7, enrollment.getGrade2());
		Util.setPossibleNullInt(editEnrollment, 8, enrollment.getAverage_grade());
		editEnrollment.setString(9, enrollment.getCourseState());
		editEnrollment.setInt(10, enrollment.getYear());
		editEnrollment.setInt(11, enrollment.getId());
		return editEnrollment.executeUpdate();
	}
	
	public static int delete(int id, Connection connection) throws SQLException {
		String deleteString = "DELETE FROM inscripcion WHERE id = ?";
		PreparedStatement deleteEnrollment = connection.prepareStatement(deleteString);
		deleteEnrollment.setInt(1, id);
		return deleteEnrollment.executeUpdate();
	}
	
	public static List<Student> getStudentsByCourse(Connection connection, int id_course) throws SQLException{
		String listString = "SELECT a.* FROM inscripcion i, alumno a, curso c"
				+ " WHERE i.id_alumno = a.id and i.id_curso = c.id and c.id = ?"
				+ " ORDER BY a.nombre ASC";
		PreparedStatement listStudents = connection.prepareStatement(listString);
		listStudents.setInt(1, id_course);
		ResultSet res = listStudents.executeQuery();
		List<Student> students = new ArrayList<Student>();
		while(res.next()) {
			Student student = new Student(res.getInt("id"), res.getString("nombre"), res.getString("apellido"), res.getString("nombre_alternativo"));
			students.add(student);
		}
		return students;
	}
	
	public static List<Student> getStudentsByCourseAndDivision(Connection connection, int id_course, String division) throws SQLException{
		String listString = "SELECT a.* FROM inscripcion i, alumno a, curso c"
				+ " WHERE i.id_alumno = a.id and i.id_curso = c.id and c.id = ? AND i.comision = ?"
				+ " ORDER BY a.nombre ASC";
		PreparedStatement listStudents = connection.prepareStatement(listString);
		listStudents.setInt(1, id_course);
		listStudents.setString(2, division);
		ResultSet res = listStudents.executeQuery();
		List<Student> students = new ArrayList<Student>();
		while(res.next()) {
			Student student = new Student(res.getInt("id"), res.getString("nombre"), res.getString("apellido"), res.getString("nombre_alternativo"));
			students.add(student);
		}
		return students;
	}
	
	public static List<Enrollment> getByCatedra(Connection connection, int id_catedra) throws SQLException{
		String listString = "SELECT i.* FROM INSCRIPCION i, curso c"
				+ " WHERE i.id_curso = c.id and c.id_catedra = ? ORDER BY id_alumno ASC";
		PreparedStatement listEnrollments = connection.prepareStatement(listString);
		listEnrollments.setInt(1, id_catedra);
		ResultSet res = listEnrollments.executeQuery();
		List<Enrollment> enrollments = new ArrayList<Enrollment>();
		while(res.next()) {
			Enrollment enrollment = new Enrollment(res.getInt("id"), res.getInt("id_curso"), res.getInt("id_alumno"),
					res.getString("estado_inscripcion"), res.getInt("id_docente"), res.getString("comision"), res.getInt("nota1"),
					res.getInt("nota2"), res.getInt("promedio"), res.getString("estado_cursada"), res.getInt("ciclo_lectivo"));
			enrollments.add(enrollment);
		}
		return enrollments;
	}
	
	public static Map<String, Object> getGrades(Connection connection, int id_enrollment) throws SQLException{
		String listString = "SELECT a.nombre, a.apellido, a.nombre_alternativo, i.*"
				+ " FROM inscripcion i, alumno a WHERE i.id_alumno = a.id and i.id = ?";
		PreparedStatement listEnrollments = connection.prepareStatement(listString);
		listEnrollments.setInt(1, id_enrollment);
		ResultSet res = listEnrollments.executeQuery();
		Map<String, Object> gradesMap = new HashMap<String, Object>();
		Student student = null;
		Enrollment enrollment = null;
		if(res.next()) {
			student = new Student(res.getInt("id_alumno"), res.getString("nombre"),
					res.getString("apellido"), res.getString("nombre_alternativo"));
			enrollment = new Enrollment(res.getInt("id"), res.getInt("id_curso"), res.getInt("id_alumno"),
					res.getString("estado_inscripcion"), res.getInt("id_docente"), res.getString("comision"), res.getInt("nota1"),
					res.getInt("nota2"), res.getInt("promedio"), res.getString("estado_cursada"), res.getInt("ciclo_lectivo"));
		}
		gradesMap.put("student", student);
		gradesMap.put("enrollment", enrollment);
		return gradesMap;
	}
}
