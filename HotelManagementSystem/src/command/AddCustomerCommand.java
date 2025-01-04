package command;

import view.Menu;

public class AddCustomerCommand implements Command {
	private Menu menu;
	
	public AddCustomerCommand(Menu menu) {
		this.menu = menu;
	}
	
	@Override
	public void execute() {
		menu.addCustomer();
	}
}