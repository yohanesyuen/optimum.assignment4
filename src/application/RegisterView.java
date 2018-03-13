package application;

import java.sql.Date;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

public class RegisterView extends Form{
	public RegisterView(String name) {
		super(name);
	}
	
	protected void showView() {
		System.out.println();
		String name   = getLine("Enter Name");
		String nric   = getNRIC("NRIC");
		Date dob      = getDate("DOB (DD/MM/YYYY)");
		String email  = getEmail("Email");
		String mobile = getMobile("Mobile");
		// Create a new instance of User
		User refUser  = new User();
		refUser.setName(name);
		refUser.setNRIC(nric);
		refUser.setDOB(dob);
		refUser.setEmail(email);
		refUser.setMobileNumber(mobile);
		UserDAO dao = new UserDAOImpl();
		dao.saveUser(refUser);
	}
}
