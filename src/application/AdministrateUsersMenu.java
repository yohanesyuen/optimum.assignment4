package application;

public class AdministrateUsersMenu extends Menu {

	public AdministrateUsersMenu() {
		this.addOption(new LockUserView("Lock User"));
		this.addOption(new UnlockUserView("Unlock User"));
		this.addOption(new DummyView("Return"));
	}

}
