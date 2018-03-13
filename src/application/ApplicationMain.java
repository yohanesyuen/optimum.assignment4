package application;

import java.sql.Connection;
import connection.DatabaseConnection;

public class ApplicationMain {
	private static boolean running = true;
	public static void main(String[] args) {
		View refRootView = new MainMenu();
		refRootView.display();
		DatabaseConnection dconn = DatabaseConnection.getInstance();
		Connection conn = dconn.getConnection();
		DatabaseConnection.closeConnection(conn);
	}
	
	public static void end() {
		running = true;
	}
}
