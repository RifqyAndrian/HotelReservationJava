package observer;

import model.Room;

public interface DataStream {
	public void subscribe(Observer observer);
	public void unsubscribe(Observer observer);
	public void notifySubscribers(Room room);
}
