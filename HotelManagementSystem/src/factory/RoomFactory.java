package factory;

import model.Room;

public class RoomFactory {
	public static Room createRoom() {
		return new Room();
	}
}