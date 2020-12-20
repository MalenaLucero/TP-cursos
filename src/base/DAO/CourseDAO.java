package base.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.model.Course;

public class CourseDAO {
	public static List<Course> getAll(Connection connection) throws SQLException {
		String listString = "SELECT * from curso";
		PreparedStatement listCourses = connection.prepareStatement(listString);
		ResultSet res = listCourses.executeQuery();
		List<Course> courses = new ArrayList<Course>();
		while(res.next()) {
			Course curso = new Course(res.getInt("id"), res.getString("nombre"), res.getInt("id_catedra"));
			courses.add(curso);
		}
		return courses;
	}
	
	public static Course findById(Connection connection, int id) throws SQLException {
		String listString = "SELECT * from curso where id=?";
		PreparedStatement listCourses = connection.prepareStatement(listString);
		listCourses.setInt(1, id);
		ResultSet res = listCourses.executeQuery();
		Course course = null;
		if(res.next()) {
			course = new Course(res.getInt("id"), res.getString("nombre"), res.getInt("id_catedra"));
		}
		return course;
	}
	
	public static Course findByName(Connection connection, String name) throws SQLException {
		String listString = "SELECT * from curso where nombre=?";
		PreparedStatement listCourses = connection.prepareStatement(listString);
		listCourses.setString(1, name);
		ResultSet res = listCourses.executeQuery();
		Course course = null;
		if(res.next()) {
			course = new Course(res.getInt("id"), res.getString("nombre"), res.getInt("id_catedra"));
		}
		return course;
	}
	
	public static int insert(Course course, Connection connection) throws SQLException {
		String insertString = "INSERT INTO curso (nombre, id_catedra) values (?, ?)";
		PreparedStatement addCourse = connection.prepareStatement(insertString);
		addCourse.setString(1, course.getName());
		Util.setPossibleNullInt(addCourse, 2, course.getCatedra());
		return addCourse.executeUpdate();
	}
	
	public static int edit(Connection connection, Course course) throws SQLException {
		String editString = "UPDATE curso SET nombre = ?, id_catedra = ? WHERE id = ?";
		PreparedStatement editCourse = connection.prepareStatement(editString);
		editCourse.setString(1, course.getName());
		Util.setPossibleNullInt(editCourse, 2, course.getCatedra());
		editCourse.setInt(3, course.getId());
		return editCourse.executeUpdate();
	}
	
	public static int delete(int id, Connection connection) throws SQLException {
		String deleteString = "DELETE FROM curso WHERE id = ?";
		PreparedStatement deleteCourse = connection.prepareStatement(deleteString);
		deleteCourse.setInt(1, id);
		return deleteCourse.executeUpdate();
	}
}
