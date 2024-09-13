package main;

import util.MenuHelper;

public class Main {

	public Main() {
		int choice = 0;
		do {
			choice = MenuHelper.printMenu();
			MenuHelper.chooseMenu(choice);
		} while (choice != 4);
	}

	public static void main(String[] args) {
		new Main();
	}

}
