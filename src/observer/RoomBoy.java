package observer;

import model.Room;

public class RoomBoy implements Observer {
	
	public RoomBoy() {

	}

	@Override
	public void update(Room room) {
		room.roomPreparationTemplate.prepare();
	}

}
