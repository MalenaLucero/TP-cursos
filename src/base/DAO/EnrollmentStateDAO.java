package base.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrollmentStateDAO {
	public static int changeByStudent(Connection connection, int id_student, String state) throws SQLException {
		String editString = "UPDATE inscripcion SET estado_inscripcion = ? WHERE id_alumno = ?";
		PreparedStatement editEnrollments = connection.prepareStatement(editString);
		editEnrollments.setString(1, state);
		editEnrollments.setInt(2, id_student);
		return editEnrollments.executeUpdate();
	}
}
