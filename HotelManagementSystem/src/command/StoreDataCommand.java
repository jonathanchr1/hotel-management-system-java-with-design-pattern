package command;

import view.Menu;

public class StoreDataCommand implements Command {
	private Menu menu;
	
	public StoreDataCommand(Menu menu) {
		this.menu = menu;
	}
	
	@Override
	public void execute() {
		menu.storeData();
	}
}