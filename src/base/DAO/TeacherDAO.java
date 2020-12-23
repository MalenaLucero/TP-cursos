package base.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.model.Teacher;

public class TeacherDAO {
	public static List<Teacher> getAll(Connection connection) throws SQLException {
		String listString = "SELECT * FROM docente";
		PreparedStatement listTeachers = connection.prepareStatement(listString);
		ResultSet res = listTeachers.executeQuery();
		List<Teacher> teachers = new ArrayList<Teacher>();
		while(res.next()) {
			teachers.add(generateTeacher(res));
		}
		return teachers;
	}
	
	public static Teacher findById(Connection connection, int id) throws SQLException {
		String listString = "SELECT * FROM docente WHERE id= ?";
		PreparedStatement findTeacher = connection.prepareStatement(listString);
		findTeacher.setInt(1, id);
		ResultSet res = findTeacher.executeQuery();
		Teacher teacher = null;
		if(res.next()) {
			teacher = generateTeacher(res);
		}
		return teacher;
	}
	
	public static Teacher findByNameLastname(Connection connection, String name, String lastname) throws SQLException {
		String listString = "SELECT * FROM docente WHERE nombre = ? AND apellido = ?";
		PreparedStatement findTeachers = connection.prepareStatement(listString);
		findTeachers.setString(1, name);
		findTeachers.setString(2, lastname);
		ResultSet res = findTeachers.executeQuery();
		Teacher teacher = null;
		if(res.next()) {
			teacher = generateTeacher(res);
		}
		return teacher;
	}
	
	public static int insert(Teacher teacher, Connection connection) throws SQLException {
		String insertString = "INSERT INTO docente (nombre, apellido, nombre_alternativo1, nombre_alternativo2, descripcion, imagen)"
								+ "values (?, ?, ?, ?, ?, ?)";
		PreparedStatement addTeacher = connection.prepareStatement(insertString);
		addTeacher.setString(1, teacher.getName());
		addTeacher.setString(2, teacher.getLastname());
		addTeacher.setString(3, teacher.getAlternative_name1());
		addTeacher.setString(4, teacher.getAlternative_name2());
		addTeacher.setString(5, teacher.getDescription());
		addTeacher.setString(6, teacher.getImage());
		return addTeacher.executeUpdate();
	}
	
	public static int edit(Connection connection, Teacher teacher) throws SQLException {
		String editString = "UPDATE docente SET nombre = ?, apellido = ?, nombre_alternativo1 = ?,"
							+ "nombre_alternativo2 = ?, descripcion = ?, imagen = ? WHERE id = ?";
		PreparedStatement editTeacher = connection.prepareStatement(editString);
		editTeacher.setString(1, teacher.getName());
		editTeacher.setString(2, teacher.getLastname());
		editTeacher.setString(3, teacher.getAlternative_name1());
		editTeacher.setString(4, teacher.getAlternative_name2());
		editTeacher.setString(5, teacher.getDescription());
		editTeacher.setString(6, teacher.getImage());
		editTeacher.setInt(7, teacher.getId());
		return editTeacher.executeUpdate();
	}
	
	public static int delete(int id, Connection connection) throws SQLException {
		String deleteString = "DELETE FROM docente WHERE id = ?";
		PreparedStatement deleteEnrollment = connection.prepareStatement(deleteString);
		deleteEnrollment.setInt(1, id);
		return deleteEnrollment.executeUpdate();
	}
	
	private static Teacher generateTeacher(ResultSet res) throws SQLException {
		return new Teacher(res.getInt("id"), res.getString("nombre"), res.getString("apellido"),
				res.getString("nombre_alternativo1"), res.getString("nombre_alternativo2"),
				res.getString("descripcion"), res.getString("imagen"));
	}
}
