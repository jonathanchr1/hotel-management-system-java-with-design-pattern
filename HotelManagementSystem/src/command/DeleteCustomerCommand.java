package command;

import view.Menu;

public class DeleteCustomerCommand implements Command {
	private Menu menu;
	
	public DeleteCustomerCommand(Menu menu) {
		this.menu = menu;
	}
	
	@Override
	public void execute() {
		menu.deleteCustomer();
	}
}