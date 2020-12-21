package base.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Util {
	public static void setPossibleNullInt(PreparedStatement statement, int index, int number) throws SQLException {
		if(number == 0) {
			statement.setNull(index, java.sql.Types.NULL);
		} else {
			statement.setInt(index, number);
		}
	}
}
