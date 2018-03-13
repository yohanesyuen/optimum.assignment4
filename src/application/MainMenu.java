package application;

public class MainMenu extends Menu {
	public MainMenu() {
		this.addOption(new LoginView("Login"));
		this.addOption(new ForgetPasswordView("Forget Password"));
		this.addOption(new EndView("Quit"));
	}
}
