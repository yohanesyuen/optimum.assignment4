package application;

public class AdminMenu extends Menu {

	public AdminMenu() {
		super();
		System.out.println("Welcome Admin!");
		this.addOption(new RegisterView("Register New User"));
		this.addOption(new ListUsersView("View Users"));
		this.addOption(new DummyView("Logout"));
	}

}
