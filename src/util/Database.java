package util;

import java.util.ArrayList;
import java.util.List;

import model.Reservation;
import model.Room;
import observer.DataStream;
import observer.Observer;
import observer.RoomBoy;
import util.Database;

public final class Database implements DataStream {

private static Database instance;
	
	private ArrayList<Observer> observers = new ArrayList<>();
	public List<Reservation> reservations = new ArrayList<>();

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
            instance.subscribe(new RoomBoy());
        }
        return instance;
    }

    @Override
	public void subscribe(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void unsubscribe(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifySubscribers(Room room) {
		if (observers.isEmpty()) {
			return;
		}
		
		for (Observer observer : observers) {
			observer.update(room);
		}
	}
	
	public void prepareRoom(Reservation reservation) {
		System.out.println("Preparing Room...");
		notifySubscribers(reservation.room);
	}


}
