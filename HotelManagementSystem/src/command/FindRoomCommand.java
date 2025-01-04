package command;

import view.Menu;

public class FindRoomCommand implements Command {
	private Menu menu;
	
	public FindRoomCommand(Menu menu) {
		this.menu = menu;
	}
	
	@Override
	public void execute() {
		menu.findRoom();
	}
}