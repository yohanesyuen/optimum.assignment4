package application;

import controller.AuthenticationController;
import model.User;

public class LoginView extends Form {

	public LoginView(String name) {
		super (name);
	}
	@Override
	protected void showView() {
		View nextView;
		User userRef = attemptLogin();
		while (userRef == null) {
			System.out.println("Please try again!");
			userRef = attemptLogin();
		}
		if (userRef != null) {
			if (userRef.getRole().equals("Admin")) {
				nextView = new AdminMenu();
			} else {
				if (userRef.isFirstLogin()) {
					nextView = new UserSetPasswordView(userRef);
				} else {
					nextView = new UserHomeView(userRef);
				}
			}
			nextView.display();
		}
	}

	private User attemptLogin() {
		String login    = getLine("Login ID");
		String password = getLine("Password");
		AuthenticationController ctrl = new AuthenticationController();
		return ctrl.authenticate(login, password);
	}
}
