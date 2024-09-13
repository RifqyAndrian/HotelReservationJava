package util;

import java.util.Scanner;

import facade.GeneratorFacade;
import model.Family;
import model.Regular;
import model.Reservation;
import model.Room;
import model.Royal;

public class MenuHelper {
	
	static Scanner scanner = new Scanner(System.in);
	static Database database = Database.getInstance();
	static GeneratorFacade generatorFacade = new GeneratorFacade();

	public static void clearScreen() {
		for (int x=0;x<30;x++) {
			System.out.println("");
		}
	}
	
	public static int printMenu() {
		int choice = 0;
		clearScreen();
		System.out.println("1. Insert New Reservation");
		System.out.println("2. Prepare Reservation");
		System.out.println("3. Manage Reservation");
		System.out.println("4. Exit");
		
		choice = scanner.nextInt();
		scanner.nextLine();
		
		return choice;
	}
	
	public static void chooseMenu(int choice) {
		clearScreen();
		switch (choice) {
			case 1:
				insertReservation();
				scanner.nextLine();
				break;
			case 2:
				prepareReservation();
				scanner.nextLine();
				break;
			case 3:
				manageReservation();
				scanner.nextLine();
				break;
		}
	}
	
	public static void insertReservation() {
		String name, roomType = "";
		int price = 0;
		do {
			System.out.print("Insert Name : ");
			name = scanner.nextLine();
		} while (name.equals(""));
		do {
			System.out.print("Insert Room Type : ");
			roomType = scanner.nextLine();
		} while (!roomType.equals("Royal")&& !roomType.equals("Family") && !roomType.equals("Reguler"));
		do {
			System.out.print("Insert Room Price : ");
			price = scanner.nextInt();
		} while (validateRoomPrice(roomType, price));
		database.reservations.add(new Reservation(name, getRoom(roomType), price));
		System.out.println("Success");
	}
	
	public static boolean validateRoomPrice(String roomType, int price) {
		switch (roomType) {
			case "Royal":
				return price < 4000000;
			case "Family":
				return price < 2000000;
			case "Reguler":
				return price < 1000000;
			default:
				return false;
		}
	}
	
	public static Room getRoom(String roomType) {
		if (roomType.equals("Royal")) {
			return new Royal();
		} else if (roomType.equals("Family")) {
			return new Family();
		} else if (roomType.equals("Reguler")) {
			return new Regular();
		}
		
		return null;
	}
	
	public static void prepareReservation() {
		int choice = 0;
		if (database.reservations.size() == 0) {
			System.out.println("Belum ada Reservasi");
			return;
		}
		for (int x=0;x<database.reservations.size();x++) {
			System.out.println(x + 1 + ". " + database.reservations.get(x).name + "-" + database.reservations.get(x).room.getClass().getSimpleName() + "-" + database.reservations.get(x).price);
		}
		do {
			System.out.print("Choose Room : ");
			choice = scanner.nextInt();
			scanner.nextLine();
		} while (choice < 1 || choice > database.reservations.size());
		database.prepareRoom(database.reservations.get(choice - 1));
	}
	
	public static void manageReservation() {
		int reservationChoice = 0;
		if (database.reservations.size() == 0) {
			System.out.println("Belum ada Reservasi");
			return;
		}
		for (int x=0;x<database.reservations.size();x++) {
			System.out.println(x + 1 + ". " + database.reservations.get(x).name + "-" + database.reservations.get(x).room.getClass().getSimpleName() + "-" + database.reservations.get(x).price);
		}
		do {
			System.out.print("Choose Room : ");
			reservationChoice = scanner.nextInt();
			scanner.nextLine();
		} while (reservationChoice < 1 || reservationChoice > database.reservations.size());
		int choice = 0;
		do {
			System.out.println("What do you want to do ? ");
			System.out.println("1. Cancel Reservation");
			System.out.println("2. Upgrade Reservation");
			choice = scanner.nextInt();
			scanner.nextLine();	
		} while (choice < 1 || choice > 2);
		if (choice == 1) {
			database.reservations.remove(reservationChoice - 1);
			generatorFacade.generateCancelPDF();
			System.out.println("Sending Email...");
		} else if (choice == 2) {
			database.reservations.get(reservationChoice - 1).upgradeReservation();
			generatorFacade.generateUpgradePDF();
			System.out.println("Sending Email...");
		}
		
		System.out.println("Success");
	}

}
