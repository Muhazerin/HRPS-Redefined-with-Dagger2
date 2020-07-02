package com.muhazerin.HRPS_Redefined_with_Dagger2.control;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import javax.inject.Inject;

import com.muhazerin.HRPS_Redefined_with_Dagger2.entity.CreditCard;
import com.muhazerin.HRPS_Redefined_with_Dagger2.entity.Guest;
import com.muhazerin.HRPS_Redefined_with_Dagger2.interfaces.DataAccess;
import com.muhazerin.HRPS_Redefined_with_Dagger2.interfaces.ModifyObject;
import com.muhazerin.HRPS_Redefined_with_Dagger2.interfaces.PrintAllObjects;
import com.muhazerin.HRPS_Redefined_with_Dagger2.interfaces.PrintSingleObject;
import com.muhazerin.HRPS_Redefined_with_Dagger2.interfaces.SelectObject;

/**
 * 
 * @author muhazerin
 *
 */

public class GuestManager implements SelectObject, ModifyObject, PrintSingleObject, PrintAllObjects{	
	private ArrayList<Guest> guestList;
	private DataAccess dataAccess; 
	private Scanner sc;
	
	@Inject
	public GuestManager(Scanner sc, DataAccess dataAccess) {
		this.dataAccess = dataAccess;
		this.sc = sc;
		
		guestList = new ArrayList<Guest>();
		Object[] objArray = dataAccess.readObject(Guest.class);
		for (Object obj : objArray) {
			if (obj instanceof Guest) {
				Guest g = (Guest) obj;
				guestList.add(g);
			}
			else {
				System.out.println("Object is not instance of Guest");
				break;
			}
		}
	}
	
	public Object[] getList() {
		return guestList.toArray();
	}
	@Override
	public Object selectObject() {
		Guest guest = null;
		if (guestList.size() == 0) {
			// guestList is empty
			add();
			guest = guestList.get(guestList.size() - 1);
		}
		else {
			// there are items in the guestList,
			// so, must check whether the name is already inside guestList
			ArrayList<Guest> tempList = searchGuest();
			if (tempList.size() == 1) {
				// Guest exist in the guestList
				// but must still show info and ask for confirmation
				System.out.println("Guest found in the guestList");
				print(tempList.get(0));
				String choice = "";
				do {
					System.out.print("Is it this guest? (Y/n): ");
					choice = sc.nextLine();
					if (choice.equalsIgnoreCase("y")) {
						guest = tempList.get(0);
					}
					else if (choice.equalsIgnoreCase("n")) {
						System.out.println("Ok. Creating new guest");
						add();
						guest = guestList.get(guestList.size() - 1);
					}
					else {
						System.out.println("Invalid Choice");
					}
				} while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
			}
			else if (tempList.size() == 0) {
				// guest does not exist in the guestList
				add();
				guest = guestList.get(guestList.size() - 1);
			}
			else {
				// if there is multiple guests found in the guestList,
				// ask the user if the guest is in the guestList
				// if yes, specify the guest
				// if no, create new guest
				System.out.println("Multiple guests found in the guestList");
				for (Guest g : tempList) {
					print(g);
				}
				String choice = "";
				do {
					System.out.print("Is the guest you are seaching for in this list? (Y/n): ");
					choice = sc.nextLine();
					if (choice.equalsIgnoreCase("y")) {
						// specify the guest
						do {
							System.out.print("Enter guest's full name: ");
							String name = sc.nextLine();
							for (Guest g : tempList) {
								if (g.getName().equals(name)) {
									guest = g;
									break;
								}
							}
							if (Objects.equals(guest, null)) {
								System.out.println("Invalid Name");
							}
						} while (Objects.equals(guest, null));
					}
					else if (choice.equalsIgnoreCase("n")) {
						System.out.println("Ok. Creating new guest");
						add();
						guest = guestList.get(guestList.size() - 1);
					}
					else {
						System.out.println("Invalid Choice");
					}
				} while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
			}
		}
		return guest;
	}
	@Override
	public void modify() {
		ArrayList<Guest> tempList = searchGuest();
		if (tempList.size() == 0) {
			System.out.println("Name is not found in the guest list");
			return;
		}
		if (tempList.size() > 1) {
			System.out.println("Multiple guest found. Please refine your search query");
			for (Guest g : tempList) {
				System.out.println("Name: " + g.getName());
			}
			return;
		}
		Guest g =  tempList.get(0);
		
		int option = -1;
		String answer = "";
		do {
			System.out.println("\n+------------------------------+");
			System.out.println("| What you you like to modify? |");
			System.out.println("+------------------------------+");
			System.out.println("| 0. Nothing                   |");
			System.out.println("| 1. NRIC                      |");
			System.out.println("| 2. Name                      |");
			System.out.println("| 3. Gender                    |");
			System.out.println("| 4. Nationality               |");
			System.out.println("| 5. Country                   |");
			System.out.println("| 6. Address                   |");
			System.out.println("| 7. Credit Card               |");
			System.out.println("+------------------------------+");
			System.out.print("Enter choice: ");
			option = validateChoice(option, "Enter choice: ");
			
			switch(option) {
				case 0:
					System.out.println("Going back...");
					dataAccess.writeObject(guestList.toArray(), Guest.class);
					break;
				case 1:
					System.out.print("Enter new NRIC: ");
					g.setNRIC(sc.nextLine());
					System.out.println("NRIC changed");
					break;
				case 2:
					System.out.print("Enter new name: ");
					String name = sc.nextLine();
					g.setName(name);
					System.out.println("Name changed");
					answer = "";
					while (answer == "") {
						System.out.print("Change guest's credit card name with new name (Y/n): ");
						answer = sc.nextLine();
						if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
							if (answer.equalsIgnoreCase("y")) {
								g.getCreditCard().setName(name);
								System.out.println("Credit Card Name changed");
							}
						}
						else {
							answer = "";
							System.out.println("Invalid input. Please enter Y/n");
						}
					}
					break;
				case 3:
					System.out.print("Enter new gender: ");
					g.setGender(sc.nextLine());
					System.out.println("Gender changed");
					break;
				case 4:
					System.out.print("Enter new nationality: ");
					g.setNationality(sc.nextLine());
					System.out.println("Nationality changed");
					break;
				case 5:
					System.out.print("Enter new country: ");
					String country = sc.nextLine();
					g.setCountry(country);
					System.out.println("Country changed");
					answer = "";
					while (answer == "") {
						System.out.print("Change guest's credit card country with new name (Y/n): ");
						answer = sc.nextLine();
						if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
							if (answer.equalsIgnoreCase("y")) {
								g.getCreditCard().setName(country);
								System.out.println("Credit Card Country changed");
							}
						}
						else {
							answer = "";
							System.out.println("Invalid input. Please enter Y/n");
						}
					}
					break;
				case 6:
					System.out.print("Enter new address: ");
					String address = sc.nextLine();
					g.setAddress(address);
					System.out.println("Address changed");
					answer = "";
					while (answer == "") {
						System.out.print("Change guest's credit card address with new name (Y/n): ");
						answer = sc.nextLine();
						if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
							if (answer.equalsIgnoreCase("y")) {
								g.getCreditCard().setName(address);
								System.out.println("Credit Card Address changed");
							}
						}
						else {
							answer = "";
							System.out.println("Invalid input. Please enter Y/n");
						}
					}
					break;
				case 7:
					modifyCreditCard(g);
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
			
		} while (option != 0);
		
	}
	@Override
	public void printSingle() {
		ArrayList<Guest> tempList = searchGuest();
		if (tempList.size() == 0) {
			System.out.println("Name is not found in the guest list");
			return;
		}
		if (tempList.size() > 1) {
			System.out.println("Multiple guest found. Please refine your search query");
			for (Guest g : tempList) {
				System.out.println("Name: " + g.getName());
			}
			return;
		}
		print(tempList.get(0));
	}
	@Override
	public void printAll() {
		if (guestList.size() > 0) {
			for (Guest g : guestList) {
				print(g);
			}
		}
		else {
			System.out.println("There's no guest in the guest list");
		}
	}
	
	private void add() {
		String nric, name, gender, nationality, address, country;
		String cName, cAddress, cCountry, cExp, option;
		long cCardNo = 0;
		int cCvv = 0;
		CreditCard.CardType cCardType = null;
		
		// Add Guest Details
		System.out.print("Enter nric: ");
		nric = sc.nextLine();
		
		if (guestList.size() > 0) {
			for (Guest g : guestList) {
				if (g.getNRIC().equalsIgnoreCase(nric)) {
					System.out.println("Guest found in guest list");
					System.out.println("Guest is not added");
					System.out.printf("NRIC: %s, Name: %s, Gender: %s, Nationality: %s\n", g.getNRIC(), g.getName(), g.getGender(), g.getNationality());
					return;
				}
			}
		}
		
		System.out.print("Enter name: ");
		name = sc.nextLine();
		System.out.print("Enter gender: ");
		gender = sc.nextLine();
		System.out.print("Enter country: ");
		country = sc.nextLine();
		System.out.print("Enter nationality: ");
		nationality = sc.nextLine();
		System.out.print("Enter address: ");
		address = sc.nextLine();
		
		// Add Credit Card Details
		cName = cAddress = cCountry = cExp = option = "";
		while (option == "") {
			System.out.print("Use the same name, country, and address for Credit Card? (Y/n): ");
			option = sc.nextLine();
			if (option.equalsIgnoreCase("y") || option.equalsIgnoreCase("n")) {
				if (option.equalsIgnoreCase("y")) {
					cName = name;
					cAddress = address;
					cCountry = country;
				}
				else {
					System.out.print("Enter name: ");
					cName = sc.nextLine();
					System.out.print("Enter country: ");
					cCountry = sc.nextLine();
					System.out.print("Enter address: ");
					cAddress = sc.nextLine();
				}
				cCardType = getCardType();
				while (cCardNo == 0) {
					try {
						System.out.print("Enter card number: ");
						cCardNo = sc.nextLong();
						sc.nextLine();	// clear the "\n" in the buffer
					}
					catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter an integer");
						cCardNo = 0;
						sc.nextLine();	// clear the input in the buffer
					}
					catch (Exception e) {
						System.out.println("Error!! Error message: " + e.getMessage());
						cCardNo = 0;
						sc.nextLine();	// clear the input in the buffer
					}
				}
				
				System.out.print("Enter exp (mm/yy): ");
				cExp = validateExp(cExp, "Enter exp (mm/yy): ");
				
				while (cCvv == 0) {
					try {
						System.out.print("Enter cvv: ");
						cCvv = sc.nextInt();
						sc.nextLine();	// clear the "\n" in the buffer
					}
					catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter an integer");
						cCvv = 0;
						sc.nextLine();	// clear the input in the buffer
					}
					catch (Exception e) {
						System.out.println("Error!! Error message: " + e.getMessage());
						cCvv = 0;
						sc.nextLine();	// clear the input in the buffer
					}
				}
				
			}
			else {
				option = "";
				System.out.println("Invalid input. Please enter Y/n");
			}
		}
		
		Guest g = new Guest(nric, name, gender, nationality, address, country, cName, cAddress, cCountry, cExp, cCardNo, cCvv, cCardType);
		guestList.add(g);
		dataAccess.writeObject(guestList.toArray(), Guest.class);
		System.out.println("Guest added");
	}
	private void print(Guest g) {
		System.out.printf("NRIC: %s, Name: %s, Gender: %s, Country: %s, Nationality: %s, Address: %s\n", g.getNRIC(), g.getName(), g.getGender(), g.getCountry(), g.getNationality(), g.getAddress());
		System.out.println("Credit Card Details: ");
		System.out.printf("Card Type: %s, Name: %s, Country: %s, Address: %s, Card No: %d, Exp: %s, CVV: %d\n", g.getCreditCard().getCardType().toString(), g.getCreditCard().getName(), g.getCreditCard().getCountry(), g.getCreditCard().getAddress(), g.getCreditCard().getCardNo(), g.getCreditCard().getExp(), g.getCreditCard().getCvv());
	}
	private ArrayList<Guest> searchGuest() {
		ArrayList<Guest> tempGuest = new ArrayList<Guest>();
		System.out.print("Enter guest name: ");
		String name = sc.nextLine();
		
		if (guestList.size() == 0) {
			return tempGuest;
		}
		for (Guest g : guestList) {
			if (g.getName().contains(name)) {
				tempGuest.add(g);
			}
		}
		return tempGuest;
	}
	private int validateChoice(int choice, String inputText) {
		boolean valid = false;
		
		while (!valid) {
			if (!sc.hasNextInt()) {
				System.out.println("Invalid Input. Please enter an integer");
				sc.nextLine();	// clear the input in the buffer
				System.out.print(inputText);
			}
			else {
				valid = true;
				choice = sc.nextInt();
				sc.nextLine();	// clear the "\n" in the buffer
			}
		}
		
		return choice;
	}
	private CreditCard.CardType getCardType() {
		int choice = -1;
		CreditCard.CardType cardType = null;
		
		do {
			System.out.println("\n+-------------------+");
			System.out.println("| Select card type: |");
			System.out.println("| 1. Mastercard     |");
			System.out.println("| 2. Visa           |");
			System.out.println("+-------------------+");
			System.out.print("Enter choice: ");
			choice = validateChoice(choice, "Enter choice: ");
			switch (choice) {
				case 1:
					cardType = CreditCard.CardType.MASTER;
					break;
				case 2:
					cardType = CreditCard.CardType.VISA;
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
		} while (choice != 1 && choice != 2);
		return cardType;
	}
	private String validateExp(String exp, String inputText) {
		boolean valid = false, isNum1 = false, isNum2 = false;
		
		while (!valid) {
			exp = sc.nextLine();
			if (!exp.contains("/")) {
				System.out.println("Invalid expiry date. Please enter an expiry date");
				System.out.print(inputText);
			}
			else {
				String[] parts = exp.split("/");
				if (parts.length == 2) {
					isNum1 = isInteger(parts[0]);
					isNum2 = isInteger(parts[1]);
				}
				if (!isNum1 || !isNum2) {
					System.out.println("Invalid expiry date. Please enter an expiry date");
					System.out.print(inputText);
				}
				else {
					valid = true;
				}
			}
		}
		
		return exp;
	}
	private boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	private void modifyCreditCard(Guest guest) {
		int option = -1;
		String answer = "";
		
		do {
			System.out.println("\n+------------------------------+");
			System.out.println("| What you you like to modify? |");
			System.out.println("+------------------------------+");
			System.out.println("| 0. Nothing                   |");
			System.out.println("| 1. Name                      |");
			System.out.println("| 2. Address                   |");
			System.out.println("| 3. Country                   |");
			System.out.println("| 4. Expiry Date               |");
			System.out.println("| 5. Card Number               |");
			System.out.println("| 6. CVV                       |");
			System.out.println("| 7. Card Type                 |");
			System.out.println("+------------------------------+");
			System.out.print("Enter choice: ");
			option = validateChoice(option, "Enter choice: ");
			
			switch (option) {
				case 1:
					System.out.print("Enter new name: ");
					String name = sc.nextLine();
					guest.getCreditCard().setName(name);
					System.out.println("Name changed");
					answer = "";
					while (answer == "") {
						System.out.print("Change guest's name with new name (Y/n): ");
						answer = sc.nextLine();
						if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
							if (answer.equalsIgnoreCase("y")) {
								guest.setName(name);
								System.out.println("Guest's name changed");
							}
						}
						else {
							answer = "";
							System.out.println("Invalid input. Please enter Y/n");
						}
					}
					break;
				case 2:
					System.out.print("Enter new address: ");
					String address = sc.nextLine();
					guest.getCreditCard().setAddress(address);
					System.out.print("Address changed");
					answer = "";
					while (answer == "") {
						System.out.print("Change guest's address with new name (Y/n): ");
						answer = sc.nextLine();
						if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
							if (answer.equalsIgnoreCase("y")) {
								guest.setName(address);
								System.out.println("Guest's address changed");
							}
						}
						else {
							answer = "";
							System.out.println("Invalid input. Please enter Y/n");
						}
					}
					break;
				case 3:
					System.out.print("Enter new country: ");
					String country = sc.nextLine();
					guest.getCreditCard().setCountry(country);
					System.out.println("Country changed");
					answer = "";
					while (answer == "") {
						System.out.print("Change guest's country with new name (Y/n): ");
						answer = sc.nextLine();
						if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")) {
							if (answer.equalsIgnoreCase("y")) {
								guest.setName(country);
								System.out.println("Guest's country changed");
							}
						}
						else {
							answer = "";
							System.out.println("Invalid input. Please enter Y/n");
						}
					}
					break;
				case 4:
					String exp = "";
					System.out.print("Enter exp (mm/yy): ");
					exp = validateExp(exp, "Enter exp (mm/yy): ");
					guest.getCreditCard().setExp(exp);
					System.out.println("Expiry date changed");
					break;
				case 5:
					long cardNo = 0;
					while (cardNo == 0) {
						try {
							System.out.print("Enter card number: ");
							cardNo = sc.nextLong();
							sc.nextLine();	// clear the "\n" in the buffer
						}
						catch (InputMismatchException e) {
							System.out.println("Invalid input. Please enter an integer");
							cardNo = 0;
							sc.nextLine();	// clear the input in the buffer
						}
						catch (Exception e) {
							System.out.println("Error!! Error message: " + e.getMessage());
							cardNo = 0;
							sc.nextLine();	// clear the input in the buffer
						}
					}
					guest.getCreditCard().setCardNo(cardNo);
					System.out.println("Card number changed");
					break;
				case 6:
					int cvv = 0;
					while (cvv == 0) {
						try {
							System.out.print("Enter cvv: ");
							cvv = sc.nextInt();
							sc.nextLine();	// clear the "\n" in the buffer
						}
						catch (InputMismatchException e) {
							System.out.println("Invalid input. Please enter an integer");
							cvv = 0;
							sc.nextLine();	// clear the input in the buffer
						}
						catch (Exception e) {
							System.out.println("Error!! Error message: " + e.getMessage());
							cvv = 0;
							sc.nextLine();	// clear the input in the buffer
						}
					}
					guest.getCreditCard().setCvv(cvv);
					System.out.println("CVV changed");
					break;
				case 7:
					CreditCard.CardType cardType = null;
					cardType = getCardType();
					guest.getCreditCard().setCardType(cardType);
					System.out.println("Card Type changed");
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		} while (option != 0);
	}
}
