package view;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import command.*;
import factory.RoomFactory;
import model.Queue;
import model.Room;
import strategy.BubbleSortStrategy;
import strategy.SortingStrategy;

public class Menu {
	private static Menu instance;
	
	private Menu() {}
	
	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu();
		}
		return instance;
	}
	
	Scanner input = new Scanner(System.in);
	Room[] myHotel = new Room[11];
	Queue queueObj = Queue.getQueueInstance();
	
	static String choice;
    static String roomName;
    static int roomNum = 1;
    static String answer;
    
    private Map<String, Command> commands = new HashMap<>();
    
    public void registerCommands() {
    	commands.put("v", new ViewRoomsCommand(this));
    	commands.put("a", new AddCustomerCommand(this));
    	commands.put("e", new DisplayEmptyRoomsCommand(this));
    	commands.put("d", new DeleteCustomerCommand(this));
    	commands.put("f", new FindRoomCommand(this));
    	commands.put("s", new StoreDataCommand(this));
    	commands.put("l", new RetrieveDataCommand(this));
    	commands.put("o", new AlphabeticalOrderCommand(this));
    }
    
    public void menu() {
    	do {
        	registerCommands();
        	
            System.out.println("======================================================");
            System.out.println("*            Hotel Management System                 *");
            System.out.println("======================================================");
            System.out.println("* V. View all the rooms                              *");
            System.out.println("* A. Add customer to room                            *");
            System.out.println("* E. Display Empty rooms                             *");
            System.out.println("* D. Delete customer from room                       *");
            System.out.println("* F. Find room from customer name                    *");
            System.out.println("* S. Store program array data into a text file       *");
            System.out.println("* L. Load program data back from the file            *");
            System.out.println("* O. View rooms Ordered alphabetically by name       *");
            System.out.println("* 3. Display the names of the first 3 customers      *");
            System.out.println("* Q. Quit Program                                    *");
            System.out.println("======================================================");
            System.out.println("Choose one of the options from above. (E.g: Type 'V' to view all the rooms)");
        	
            System.out.println();
            System.out.print("Choice : ");
            choice = input.nextLine();
            String selection = choice.toLowerCase();
            
            if (selection.equals("3")) {
            	queueObj.displayQueue();
	            menu();
			} else if (selection.equals("q")) {
				System.out.println("Thanks");
				break;
			} else {
				Command command = commands.get(selection);
	            if (command != null) {
					command.execute();
				} else {
					System.out.println("Invalid input! Please Enter one of these letters: V,A,E,D,F,S,L,O,3,Q");
				}
			}
        }
        while (!(choice.equalsIgnoreCase("v") || choice.equalsIgnoreCase("a") || choice.equalsIgnoreCase("e") || choice.equalsIgnoreCase("d") ||
                choice.equalsIgnoreCase("f") || choice.equalsIgnoreCase("s") || choice.equalsIgnoreCase("l") || choice.equalsIgnoreCase("o") ||
                choice.equals("3") || choice.equalsIgnoreCase("q")));
    }
    
    public void initialize() {
        for (int x = 1; x < 11; x++) {
            myHotel[x] = RoomFactory.createRoom();
        }
    }
    
    public void viewRooms() {
        for (int x = 1; x < 11; x++) {
            if (!myHotel[x].getName().equals("e")) {
                System.out.println("Room No. " + x + " is occupied by " + myHotel[x].getName());
            } else {
                System.out.println("Room No. " + x + " is empty");
            }

        }
        System.out.println("");
        menu();
    }
    
    public void addCustomer() {
        boolean invalidRoomNumber;

        do {
            try {
                System.out.println("Enter room number (1-10)");
                roomNum = input.nextInt();
                if (!myHotel[roomNum].getName().equals("e")) {
                    invalidRoomNumber = true;
                    System.out.println("This room is occupied by: " + myHotel[roomNum].getName());
                    System.out.println("");
                } else if (roomNum >= 1 && roomNum < 11) {
                    invalidRoomNumber = false;
                } else {
                    invalidRoomNumber = true;
                    System.out.println("Invalid input! Please Enter a value between 1-10");
                    System.out.println("");
                }
            } catch (IndexOutOfBoundsException e) {
                invalidRoomNumber = true;
                System.out.println("Invalid input! Please Enter a value between 1-10");
                System.out.println("");
            } catch (NullPointerException e) {
                invalidRoomNumber = true;
                System.out.println("Invalid input! Please Enter a value between 1-10");
                System.out.println("");
            } catch (InputMismatchException e) {
                invalidRoomNumber = true;
                System.out.println("Invalid input! Please Enter a value between 1-10");
                System.out.println("");
                input.next();
            }
        } while (invalidRoomNumber);
        System.out.println("Enter the name of the customer :");
  
        roomName = input.next();

        myHotel[roomNum].setName(roomName);
        queueObj.addToQueue(roomName);

        try {
            do {
                System.out.println("Do you want to continue adding records?(Y/N)");
                answer = input.next();
                String selection = answer.toLowerCase();

                switch (selection) {
                    case "y":
                        addCustomer();
                        break;
                    case "n":
                        System.out.println("");
                        menu();
                }

            } while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")));

        } catch (InputMismatchException e) {
            System.out.println("");
        }
    }
    
    public void displayEmptyRooms() {
        for (int x = 1; x < 11; x++) {
            if (myHotel[x].getName().equals("e")) {
                System.out.println("room " + x + " is empty");
            }
        }
        System.out.println("");
        menu();
    }
    
    public void deleteCustomer() {
        boolean invalidInput;

        do {
            invalidInput = false;
            try {
                System.out.println("Please enter the Room's number which you want to vacate");
                roomNum = input.nextInt();
                if (!(myHotel[roomNum].getName().equals("e"))) {
                    invalidInput = false;
                    myHotel[roomNum].setName("e");
                } else {
                    invalidInput = true;
                    System.out.println("Room " + roomNum + " is already Empty");
                    System.out.println("");
                }
            } catch (InputMismatchException e) {
                invalidInput = true;
                System.out.println("Invalid input! Please Enter a value between 1-10");
                System.out.println("");
                input.next();
            } catch (IndexOutOfBoundsException e) {
                invalidInput = true;
                System.out.println("Invalid room number. Please enter a value between 1-10");
                input.next();
            }
        } while (invalidInput);
        System.out.println("Room " + roomNum + " has successfully been vacated");

        System.out.println("");
        menu();
    }
    
    public void findRoom() {
        System.out.println("Please enter the name of the customer");
        boolean found = false;
        String find = input.next();

        for (int n = 1; n < 11; n++) {
            if (myHotel[n].getName().equalsIgnoreCase(find)) {
                found = true;
                System.out.println(find + " is staying in room No. " + n);
                System.out.println("");
                menu();
            }
        }
        if (found == false) {
            System.out.println(find + " doesn't exist on our database");
            System.out.println("");
            menu();
        }
    }
    
    public void storeData() {
        try {
            String[] storage = new String[11];

            for (int y = 1; y < 11; y++) {
                storage[y] = myHotel[y].getName();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt", false)); //used false to overwrite the file instead of appending
            for (int x = 1; x < storage.length; x++) {
                String file;
                file = storage[x];
                if (file.equals("e")) {
                    bw.write("Empty Room " + x);
                } else {
                    bw.write(file);
                }
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            System.err.println("File not found!");
        }

        System.out.println("Data successfully saved!");
        System.out.println("");
        menu();
    }
    
    public void retrieveData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));

            for (int x = 1; x < 11; x++) {
                String read = reader.readLine();
                if (read.equals("Empty Room " + x)) {
                    read = "e";
                }
                myHotel[x].setName(read);
            }
        } catch (IOException e) {
            System.err.println("File not found!");
        }
        
        System.out.println("File successfully loaded");
        System.out.println("");
        menu();
    }
    
    private SortingStrategy sortingStrategy = new BubbleSortStrategy();
    
    public void alphabeticalOrder() {
        int index = 0;

        String[] copy = new String[11];
        String[] names = new String[11];

        for (int y = 1; y < 11; y++) {
            copy[y] = myHotel[y].getName();
        }

        for (int x = 1; x < 11; x++) {
            names[x] = myHotel[x].getName().toLowerCase();
        }

        sortingStrategy.sort(names);

        for (int x = 1; x < names.length; x++) {
            if (!(names[x].equals("e"))) {

                for (int i = 1; i < copy.length; i++) {
                    if (copy[i].toLowerCase().equals(names[x])) {
                        index = i;
                    }
                }
                System.out.println(names[x] + " is staying in room No. " + index);
            }
        }
        System.out.println("");
        menu();
    }
}