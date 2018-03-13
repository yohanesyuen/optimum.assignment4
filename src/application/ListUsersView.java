package application;

import java.util.ArrayList;
import java.util.Iterator;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

public class ListUsersView extends Form{

	protected void showView() {
		UserDAO dao = new UserDAOImpl();
		ArrayList<User> Users = dao.getUsers();
		System.out.printf("%-5s %-30s %-30s %-20s\n","User ID", "Name", "Email", "Status");
		for (Iterator<User> itr = Users.iterator(); itr.hasNext();) {
			User user = (User) itr.next();
			System.out.printf("%-5d %-30s %-30s %-20s\n",user.getUserID(), user.getName(), user.getEmail(), user.getStatus());
		}
		Menu nextView = new AdministrateUsersMenu();
		nextView.display();
	}

	public ListUsersView(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

}
