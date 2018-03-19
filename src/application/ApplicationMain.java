package application;

import java.sql.Connection;
import connection.DatabaseConnection;

public class ApplicationMain {
	public static void main(String[] args) {
		View refRootView = new MainMenu();
		refRootView.display();
		DatabaseConnection dconn = DatabaseConnection.getInstance();
		Connection conn = dconn.getConnection();
		DatabaseConnection.closeConnection(conn);
	}
}
