package application;

import java.util.ArrayList;

public class Menu extends	 View {
	private ArrayList<View> refSubViews; // To store the subviews of the application
	
	public Menu() {
		refSubViews = new ArrayList<>();
	}
	
  protected void showView() {
  	boolean running = true;
		boolean valid_option = false;
		do {
			// Format and display all the sub views we have.
			for (int i = 0; i < refSubViews.size(); i++) {
				View refView = refSubViews.get(i);
				String display_line = String.format("%d. %s", i+1, refView.getName());
				System.out.println(display_line);
			}
			// Capture the requested view from the user.
			System.out.print("Enter Option: ");
			int option = sc.nextInt();
			sc.nextLine();

			// Perform validation on user input.
			// On success, proceed to next view.
			// On fail, ask for user to submit input again.
			valid_option = option > 0 && option <= refSubViews.size();
			if (valid_option) {
				View nextView = refSubViews.get(option - 1);
				if (nextView instanceof DummyView) {
					running = false;
				}
				nextView.display();
			} else {
				System.out.println("Invalid Option...");
			}
		} while (!valid_option || running == true);		
	}
	
	public void addOption(View refView) {
		refSubViews.add(refView);
	}
}
