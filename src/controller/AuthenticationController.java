package controller;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

public class AuthenticationController {
	private UserDAO refUserDAO;
	private User    refUser;
	
	public User authenticate(String login, String password) {
		refUserDAO = new UserDAOImpl();		
		refUser = refUserDAO.authenticatePassword(login, password);
		
		if (refUser == null) {
			return null;
		}
		if (refUser.getStatus().equals("active")) {
			return refUser;
		} else if (refUser.getStatus().equals("locked")){
			System.out.println("Your account has been locked. Please contact your administrator");
			return null;
		} else {
			System.out.println("Your account is in a weird state...");
			return null;
		}
	}
}
