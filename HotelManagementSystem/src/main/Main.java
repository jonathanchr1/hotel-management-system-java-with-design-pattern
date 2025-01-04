package main;

import view.Menu;

public class Main {
	public Main() {
		Menu start = Menu.getInstance();
		start.initialize();
		start.menu();
	}
	
	public static void main(String[] args) {
		new Main();
	}
}