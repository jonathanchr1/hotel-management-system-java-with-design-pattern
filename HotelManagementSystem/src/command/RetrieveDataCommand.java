package command;

import view.Menu;

public class RetrieveDataCommand implements Command {
	private Menu menu;
	
	public RetrieveDataCommand(Menu menu) {
		this.menu = menu;
	}
	
	@Override
	public void execute() {
		menu.retrieveData();
	}
}