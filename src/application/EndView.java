package application;

public class EndView extends DummyView {


	public EndView(String name) {
		super(name);
	}

	@Override
	protected void showView() {
		System.out.println("Goodbye!");
	}

}
