package model;

public class Reservation {
	
	public String name;
	public Room room;
	public int price;

	public Reservation() {
	}

	public Reservation(String name, Room room, int price) {
		super();
		this.name = name;
		this.room = room;
		this.price = price;
	}
	
	public void upgradeReservation() {
		if (this.room instanceof Regular) {
			this.room = new Family();
		} else if (this.room instanceof Family) {
			this.room = new Royal();
		}
	}
}
