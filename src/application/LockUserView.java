package application;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

public class LockUserView extends Form {

	public LockUserView() {
		// TODO Auto-generated constructor stub
	}

	public LockUserView(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void showView() {
		int userID = Integer.parseInt(getLine("Please enter the userID"));
		UserDAO dao = new UserDAOImpl();
		User userRef = dao.getUser(userID);
		userRef.setStatus("locked");
		userRef.setLoginAttempts(0);
		dao.updateUserStatus(userRef);
	}

}
