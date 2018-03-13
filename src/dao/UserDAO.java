package dao;

import java.util.ArrayList;

import model.User;

public interface UserDAO {
	public User getUser(int userID);
	public boolean saveUser(User refUser);
	public User authenticatePassword(String email, String password);
	public User authenticateSecurityAnswer(String email, String answer);
	public User getUserSecurityQuestion(String email);
	public ArrayList<User> getUsers();
	public boolean saveAuthDetails(User refUser);
	public void updateUserStatus(User user);
}
