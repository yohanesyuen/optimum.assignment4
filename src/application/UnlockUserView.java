package application;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

public class UnlockUserView extends Form {

	public UnlockUserView() {
		// TODO Auto-generated constructor stub
	}

	public UnlockUserView(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void showView() {
		int userID = Integer.parseInt(getLine("Please enter the userID"));
		UserDAO dao = new UserDAOImpl();
		User userRef = dao.getUser(userID);
		userRef.setStatus("active");
		userRef.setLoginAttempts(0);
		dao.updateUserStatus(userRef);
	}

}
