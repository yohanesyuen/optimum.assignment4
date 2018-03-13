package application;

import model.User;

public class UserHomeView extends Form {
	
	private User user;

	public UserHomeView() {
		// TODO Auto-generated constructor stub
	}

	public UserHomeView(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public UserHomeView(User user) {
		this.user = user;
	}

	@Override
	protected void showView() {
		if (user.isFirstLogin()) {
			View nextView = new UserSetPasswordView(user);
			nextView.display();
		} else {
			boolean toLogOut = false;
			while (!toLogOut) {
				System.out.println("Welcome " + user.getName());
				toLogOut = GetYesNo("Logout?");
			}
		}
	}
}
