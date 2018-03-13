package application;

import java.util.Scanner;

public abstract class View {
	private String name;
	protected static Scanner sc;
	protected abstract void showView();

	public View() {
		this("View");
	}
	
	public View(String name) {
		this.setName(name);
		sc = getScanner();
	}
	
	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}
	
	protected static Scanner getScanner() {
		if (sc == null) {
			sc = new Scanner(System.in);
		}
		return sc;
	}

	final void display() {
		showView();
	}
}
