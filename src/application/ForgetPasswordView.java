package application;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

public class ForgetPasswordView extends Form {

	public ForgetPasswordView() {
		// TODO Auto-generated constructor stub
	}

	public ForgetPasswordView(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void showView() {
		String login = getLine("Login");
		UserDAO dao = new UserDAOImpl();
		User userRef = dao.getUserSecurityQuestion(login);
		if (userRef != null) {
			if (!userRef.getStatus().equals("locked")) {
				String answer = getLine(userRef.getSecurityQuestion());
				userRef = dao.authenticateSecurityAnswer(login, answer);
				View nextView = new UserSetPasswordView(userRef);
				nextView.display();
			} else {
				System.out.println("Your account has been locked. Please contact your administrator...");
			}
		} else {
			System.out.println("User not found!");
		}
	}

}
