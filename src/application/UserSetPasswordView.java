package application;

import model.User;

public class UserSetPasswordView extends Form {
	
	User user;
	
	public UserSetPasswordView(User user) {
		this.user = user;
	}

	@Override
	protected void showView() {
		System.out.println("Welcome " + user.getName());
		if (user.isFirstLogin()) {
			checkTempPassword();
		}
		String newPassword = capturePassword();
		while (newPassword.equals(user.getPassword()) && user.isFirstLogin()) {
			newPassword = capturePassword();
		}
		
		this.user.setPassword(newPassword);
		View nextView = new UserSetSecurityQuestionView(this.user);
		nextView.display();
	}
	
	private void checkTempPassword() {
		String tempPassword = getLine("Enter Temp Password");
		if (!tempPassword.equals(user.getPassword())) {
			System.out.println("Please try again...");
			tempPassword = getLine("Enter Temp Password");
		}
	}
	
	private String capturePassword() {
		String newPassword = getLine("New Password");
		String confirmPassword = getLine("Retype Password");
		boolean passwordMatches = newPassword.equals(confirmPassword);
		while (!passwordMatches) {
			System.out.println("Please try again...");
			newPassword = getLine("New Password");
			confirmPassword = getLine("Retype Password");
			passwordMatches = newPassword.equals(confirmPassword);
		}
		return newPassword;
	}
}
