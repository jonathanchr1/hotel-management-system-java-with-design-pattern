package command;

import view.Menu;

public class DisplayEmptyRoomsCommand implements Command {
	private Menu menu;
	
	public DisplayEmptyRoomsCommand(Menu menu) {
		this.menu = menu;
	}
	
	@Override
	public void execute() {
		menu.displayEmptyRooms();
	}
}