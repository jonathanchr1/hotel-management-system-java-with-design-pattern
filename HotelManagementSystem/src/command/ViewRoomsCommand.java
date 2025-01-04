package command;

import view.Menu;

public class ViewRoomsCommand implements Command {
	private Menu menu;
	
	public ViewRoomsCommand(Menu menu) {
		this.menu = menu;
	}
	
	@Override
	public void execute() {
		menu.viewRooms();
	}
}