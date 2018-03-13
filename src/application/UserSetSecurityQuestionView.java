package application;

import java.util.ArrayList;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

public class UserSetSecurityQuestionView extends Form {

	private User user;

	public UserSetSecurityQuestionView(User user) {
		this.user = user;
	}

	@Override
	protected void showView() {
		// Create a list of questions for easy display and capturing inputs
		ArrayList<String> questions = new ArrayList<String>();
		questions.add("What is your favorite color?");
		questions.add("What is your favorite food?");
		questions.add("What is your mother's maiden name?");
		
		for (int i=0; i < questions.size(); i++) {
			String question = questions.get(i);
			System.out.println(Character.toString((char)('A' + i)) + ". " + question);
		}
		
		// Convert text input from user into lookup index to use.
		// TODO:	Refactor this into a generic function that can accept both int and
		// 				character options?
		int choice=getLine("Choice").toCharArray()[0] - 'A';
		boolean valid = choice > -1 && choice < questions.size();
		while (!valid) {
			System.out.println("Please try again...");
			choice=getLine("Choice").toCharArray()[0];
			valid = choice > -1 && choice < questions.size();
		}
		String question = questions.get(choice);
		String answer=getLine("Ans");
		user.setSecurityQuestion(question);
		user.setSecurityAnswer(answer);
		
		UserDAO dao = new UserDAOImpl();
		dao.saveAuthDetails(user);
	}

}
