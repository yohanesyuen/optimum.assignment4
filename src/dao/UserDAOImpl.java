package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.DatabaseConnection;
import model.User;

public class UserDAOImpl implements UserDAO {

//	Database Layout
//	+-------------------+--------------+------+-----+---------+----------------+
//	| Field             | Type         | Null | Key | Default | Extra          |
//	+-------------------+--------------+------+-----+---------+----------------+
//	| userID            | int(10)      | NO   | PRI | NULL    | auto_increment |
//	| name              | varchar(30)  | YES  |     | NULL    |                |
//	| nric              | varchar(9)   | YES  | UNI | NULL    |                |
//	| email             | varchar(255) | YES  | UNI | NULL    |                |
//	| dob               | date         | YES  |     | NULL    |                |
//	| mobile            | varchar(8)   | YES  |     | NULL    |                |
//	| password          | varchar(255) | YES  |     | NULL    |                |
//	| role              | varchar(6)   | YES  |     | User    |                |
//	| security_question | varchar(255) | YES  |     | NULL    |                |
//	| security_answer   | varchar(255) | YES  |     | NULL    |                |
//	| first_login       | tinyint(1)   | YES  |     | 1       |                |
//	| status            | varchar(10)  | YES  |     | active  |                |
//	| login_attempts    | int(10)      | YES  |     | 0       |                |
//	+-------------------+--------------+------+-----+---------+----------------+
	
	@Override
	public User getUser(int userID) {
		Connection con = DatabaseConnection.getInstance().getConnection();
		ResultSet rs;
		try {
			String selectSQL = "SELECT * FROM USERS WHERE userID=?";
			PreparedStatement pst = con.prepareStatement(selectSQL);
			pst.setInt(1, userID);
			rs = pst.executeQuery();
			if (rs.next()) {
				User userRef = createUserFromResultSet(rs);
				return userRef;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean saveUser(User refUser) {
		Connection con = DatabaseConnection.getInstance().getConnection();
		try {
			String insertSQL = "INSERT INTO `USERS`";
			insertSQL       += " (name, nric, email, dob, mobile, password)";
			insertSQL       += " VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(insertSQL);
			pst.setString(1, refUser.getName());
			pst.setString(2, refUser.getNRIC());
			pst.setString(3, refUser.getEmail());
			pst.setDate(4, refUser.getDOB());
			pst.setString(5, refUser.getMobileNumber());
			String nric = refUser.getNRIC();
			String mobile = refUser.getMobileNumber();
			String password = nric.substring(1, 5) + mobile.substring(4, 8);
			pst.setString(6, password);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean saveAuthDetails(User refUser) {
		Connection con = DatabaseConnection.getInstance().getConnection();
		try {
			String updateSQL = "UPDATE `USERS`";
			updateSQL       += " SET password=?, security_question=?, security_answer=?, first_login=?";
			updateSQL       += " WHERE userID=?";
			PreparedStatement pst = con.prepareStatement(updateSQL);
			pst.setString(1, refUser.getPassword());
			pst.setString(2, refUser.getSecurityQuestion());
			pst.setString(3, refUser.getSecurityAnswer());
			pst.setBoolean(4, false);
			pst.setInt(5, refUser.getUserID());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public User authenticatePassword(String email, String password) {
		Connection con = DatabaseConnection.getInstance().getConnection();
		ResultSet rs;
		try {
			String selectSQL = "SELECT * FROM USERS WHERE email=?";
			PreparedStatement pst = con.prepareStatement(selectSQL);
			pst.setString(1, email);
			rs = pst.executeQuery();
			if (rs.next()) {
				User userRef = createUserFromResultSet(rs);
				int login_attempts = userRef.getLoginAttempts();
				// Check user password
				if (userRef.getPassword().equals(password)) {
					// Reset login attempts
					login_attempts=0;
					userRef.setLoginAttempts(login_attempts);
					updateUserStatus(userRef);
					return userRef;
				} else if(login_attempts < 3) {
					// Increment login attempts. Lock if > 3.
					login_attempts++;
					userRef.setLoginAttempts(login_attempts);
					updateUserStatus(userRef);
					if (login_attempts >=3) {
						userRef.setLoginAttempts(login_attempts);
						userRef.setStatus("locked");
						updateUserStatus(userRef);
					}
				}				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<User> getUsers() {
		ArrayList<User> refUsers = new ArrayList<User>();
		Connection con = DatabaseConnection.getInstance().getConnection();
		ResultSet rs;
		try {
			String selectSQL = "SELECT * FROM USERS WHERE NOT role='Admin'";
			PreparedStatement pst = con.prepareStatement(selectSQL);
			rs = pst.executeQuery();
			while (rs.next()) {
				refUsers.add(createUserFromResultSet(rs));
			}
			return refUsers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private User createUserFromResultSet(ResultSet rs) throws SQLException {
		User refUser= new User();
		refUser.setUserID(rs.getInt("userID"));
		refUser.setPassword(rs.getString("password"));
		refUser.setName(rs.getString("name"));
		refUser.setNRIC(rs.getString("nric"));
		refUser.setEmail(rs.getString("email"));
		refUser.setDOB(rs.getDate("dob"));
		refUser.setMobileNumber(rs.getString("mobile"));
		refUser.setRole(rs.getString("role"));
		refUser.setSecurityQuestion(rs.getString("security_question"));
		refUser.setSecurityAnswer(rs.getString("security_answer"));
		refUser.setFirstLogin(rs.getBoolean("first_login"));
		refUser.setStatus(rs.getString("status"));
		refUser.setLoginAttempts(rs.getInt("login_attempts"));
		return refUser;
	}
	
	@Override
	public void updateUserStatus(User user) {
		Connection con = DatabaseConnection.getInstance().getConnection();
		try {
			String updateSQL = "UPDATE `USERS`";
			updateSQL       += " SET login_attempts=?, status=?";
			updateSQL       += " WHERE userID=?";
			PreparedStatement pst = con.prepareStatement(updateSQL);
			pst.setInt(1, user.getLoginAttempts());
			pst.setString(2, user.getStatus());
			pst.setInt(3, user.getUserID());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User authenticateSecurityAnswer(String email, String answer) {
		Connection con = DatabaseConnection.getInstance().getConnection();
		User userRef;
		try {
			String selectSQL = "SELECT * FROM USERS WHERE email=?";
			PreparedStatement pst = con.prepareStatement(selectSQL);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				userRef = createUserFromResultSet(rs);
				if (userRef.getSecurityAnswer().equals(answer)) {
					return userRef;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserSecurityQuestion(String email) {
		Connection con = DatabaseConnection.getInstance().getConnection();
		try {
			String selectSQL = "SELECT * FROM USERS WHERE email=?";
			PreparedStatement pst = con.prepareStatement(selectSQL);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return createUserFromResultSet(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
