package command;

import view.Menu;

public class AlphabeticalOrderCommand implements Command {
	private Menu menu;
	
	public AlphabeticalOrderCommand(Menu menu) {
		this.menu = menu;
	}
	
	@Override
	public void execute() {
		menu.alphabeticalOrder();
	}
}