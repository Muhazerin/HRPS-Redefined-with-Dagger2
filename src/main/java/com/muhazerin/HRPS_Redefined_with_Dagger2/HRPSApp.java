package com.muhazerin.HRPS_Redefined_with_Dagger2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.GuestManager;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.MenuItemManager;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.ReservationManager;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.RoomManager;
import com.muhazerin.HRPS_Redefined_with_Dagger2.dagger.DaggerGuestManagerComponent;
import com.muhazerin.HRPS_Redefined_with_Dagger2.dagger.DaggerMenuItemManagerComponent;
import com.muhazerin.HRPS_Redefined_with_Dagger2.dagger.DaggerReservationManagerComponent;
import com.muhazerin.HRPS_Redefined_with_Dagger2.dagger.DaggerRoomManagerComponent;
import com.muhazerin.HRPS_Redefined_with_Dagger2.dagger.GuestManagerComponent;
import com.muhazerin.HRPS_Redefined_with_Dagger2.dagger.MenuItemManagerComponent;
import com.muhazerin.HRPS_Redefined_with_Dagger2.dagger.ReservationManagerComponent;
import com.muhazerin.HRPS_Redefined_with_Dagger2.dagger.RoomManagerComponent;
import com.muhazerin.HRPS_Redefined_with_Dagger2.entity.Guest;
import com.muhazerin.HRPS_Redefined_with_Dagger2.entity.MenuItem;
import com.muhazerin.HRPS_Redefined_with_Dagger2.entity.Payment;
import com.muhazerin.HRPS_Redefined_with_Dagger2.entity.Reservation;
import com.muhazerin.HRPS_Redefined_with_Dagger2.entity.Room;
import com.muhazerin.HRPS_Redefined_with_Dagger2.entity.RoomService;

public class HRPSApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		GuestManagerComponent guestManagerComponent = DaggerGuestManagerComponent.create();
        GuestManager guestManager = guestManagerComponent.getGuestManager();
        RoomManagerComponent roomManagerComponent = DaggerRoomManagerComponent.create();
        RoomManager roomManager = roomManagerComponent.getRoomManager();
        
		// creating a thread pool with size = total number of rooms
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(roomManager.getTotalNumberOfRooms());

		MenuItemManagerComponent menuItemManagerComponent = DaggerMenuItemManagerComponent.create();
		MenuItemManager menuItemManager = menuItemManagerComponent.getMenuItemManager();
		ReservationManagerComponent reservationManagerComponent = DaggerReservationManagerComponent.create();
		ReservationManager reservationManager = reservationManagerComponent.getReservationManager();
		reservationManager.adjustObject(guestManager.getList());
		reservationManager.adjustObject(roomManager.getList());
		reservationManager.addService(scheduledExecutorService);
		
		int option = -1, option2 = -1;
		do {
			menu();
			option = intInputChecker(option, sc);
			switch (option) {
			case 0:
				scheduledExecutorService.shutdownNow();
				System.out.println("Exiting the program...");
				System.out.println("Bye bye...");
				break;
			case 1:
				option2 = -1;
				do {
					guestMenu();
					option2 = intInputChecker(option2, sc);
					guestOption(option2, guestManager);
				} while (option2 != 0);
				break;
			case 2:
				option2 = -1;
				do {
					roomMenu();
					option2 = intInputChecker(option2, sc);
					roomOption(option2, roomManager);
				} while (option2 != 0);
				break;
			case 3:
				option2 = -1;
				do {
					reservationMenu();
					option2 = intInputChecker(option2, sc);
					reservationOption(option2, guestManager, roomManager, reservationManager, menuItemManager);
				} while (option2 != 0);
				break;
			case 4:
				option2 = -1;
				do {
					menuItemMenu();
					option2 = intInputChecker(option2, sc);
					menuItemOption(option2, menuItemManager);
				} while (option2 != 0);
				break;
			case 5:
				Guest guest = (Guest) guestManager.selectObject();
				Room room = roomManager.selectRoom(true);
				
				reservationManager.addReservation(guest, room, true);
				break;
			case 6:
				reservationManager.checkInReservation();
				break;
			case 7:
				Reservation reservation = reservationManager.checkOutReservation();
				if (!Objects.equals(reservation, null)) {
					Payment payment = new Payment(reservation, sc);
					payment.billReport();
					payment.pay();
				}
				
				break;
			default:
				System.out.println("Invalild choice");
				break;
			}
		} while (option != 0);
		
		sc.close();
	}

	/**
	 * This function prints the overview menu
	 */
	private static void menu() {
		System.out.println("\n+-----------------------------------------------------+");
		System.out.println("| Welcome to the Hotel Reservation and Payment System |");
		System.out.println("| What would you like to do today?                    |");
		System.out.println("+-----------------------------------------------------+");
		System.out.println("| 0. Exit the program                                 |");
		System.out.println("| 1. Guest Options                                    |");
		System.out.println("| 2. Room Options                                     |");
		System.out.println("| 3. Reservation Options                              |");
		System.out.println("| 4. Menu Items Options                               |");
		System.out.println("| 5. Guest Walk-In                                    |");
		System.out.println("| 6. Guest Check In                                   |");
		System.out.println("| 7. Guest Check Out                                  |");
		System.out.println("+-----------------------------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This function prints the guest menu
	 */
	private static void guestMenu() {
		System.out.println("\n+---------------------------+");
		System.out.println("| Guest Options             |");
		System.out.println("+---------------------------+");
		System.out.println("| 0. Go back                |");
		System.out.println("| 1. Modify guest           |");
		System.out.println("| 2. Search and print guest |");
		System.out.println("| 3. Prints all guests      |");
		System.out.println("+---------------------------+");
		System.out.print("Enter choice: ");
	}
		
	/*
	 * This method contains the menu for roomOption
	 */
	private static void roomMenu() {
		System.out.println("\n+------------------------------------------+");
		System.out.println("| What would you like to do ?              |");
		System.out.println("| 0. Go back                               |");
		System.out.println("| 1. Create new room                       |");
		System.out.println("| 2. Update room details                   |");
		System.out.println("| 3. List Room                             |");
		System.out.println("| 4. Check room details                    |");
		System.out.println("+------------------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/* 
	 * This method contains the menu for reservationOption
	 */
	private static void reservationMenu() {
		System.out.println("\n+-------------------------------+");
		System.out.println("| What would you like to do ?   |");
		System.out.println("| 0. Go back                    |");
		System.out.println("| 1. Create reservation         |");
		System.out.println("| 2. Update reservation detail  |");
		System.out.println("| 3. Cancel a reservation       |");
		System.out.println("| 4. Print a reservation detail |");
		System.out.println("| 5. Print all reservation      |");
		System.out.println("| 6. Add room service           |");
		System.out.println("| 7. Print room services        |");
		System.out.println("+-------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/* 
	 * This method contains the menu for menuItemOption
	 */
	private static void menuItemMenu() {
		System.out.println("\n+-------------------------------+");
		System.out.println("| What would you like to do ?   |");
		System.out.println("| 0. Go back                    |");
		System.out.println("| 1. Add menu item              |");
		System.out.println("| 2. Modify menu item           |");
		System.out.println("| 3. Remove menu item           |");
		System.out.println("| 4. Print all menu items       |");
		System.out.println("+-------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/**
	 * This function handles all the operations related to guest
	 * @param 	option		the guest option
	 * @param 	guestManager	the guestManager to handle the guest operation
	 */
	private static void guestOption(int option, GuestManager guestManager) {
		switch (option) {
			case 0:
				System.out.println("Going back...");
				break;
//			case 1:
//				//guestManager.add();
//				break;
			case 1:
				guestManager.modify();
				break;
			case 2:
				guestManager.printSingle();
				break;
			case 3:
				guestManager.printAll();
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}
	
	/**
	 * This function handles all the operations related to room
	 * @param 	option		the guest option
	 * @param 	roomManager		the roomManager to handle the guest operation
	 */
	private static void roomOption(int option, RoomManager roomManager) {
		switch (option) {
			case 0:
				System.out.println("Going back...");
				break;
			case 1:
				roomManager.addObject();
				break;
			case 2:
				roomManager.modify();
				break;
			case 3:
				roomManager.printAll();
				break;
			case 4:
				roomManager.printSingle();
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}
	
	private static void reservationOption(int option, GuestManager guestManager, RoomManager roomManager, ReservationManager reservationManager, MenuItemManager menuUtemManager) {
		switch (option) {
			case 0:
				System.out.println("Going back...");
				break;
			case 1:
				Guest guest = (Guest) guestManager.selectObject();
				Room room = roomManager.selectRoom(false);
				
				reservationManager.addReservation(guest, room, false);
				break;
			case 2:
				reservationManager.modify();
				break;
			case 3:
				break;
			case 4:
				reservationManager.printSingle();
				break;
			case 5:
				reservationManager.printAll();
				break;
			case 6:
				Reservation reservation = (Reservation) reservationManager.selectObject();
				if (!Objects.equals(reservation, null) ) {
					ArrayList<MenuItem> menuItemList = menuUtemManager.selectMenuItems();
					if (menuItemList.size() == 0) {
						System.out.println("Room service is not added");
					}
					else {
						RoomService roomService = new RoomService(menuItemList);
						reservationManager.addRoomService(reservation, roomService);
						System.out.println("Room service is added");
					}
				}
				break;
			case 7:
				reservationManager.printRoomServices();
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}
	
	private static void menuItemOption(int option, MenuItemManager menuItemManager) {
		switch (option) {
		case 0:
			System.out.println("Going back...");
			break;
		case 1:
			menuItemManager.addObject();
			break;
		case 2:
			menuItemManager.modify();
			break;
		case 3:
			menuItemManager.removeObject();
			break;
		case 4:
			menuItemManager.printAll();
			break;
		default:
			System.out.println("Invalid option");
			break;
	}
	}
	
	/**
	* Checks the input for integer
	* @param	option		the variable to store integer and return the integer (if valid)
	* @param 	sc 			Scanner object to retrieve the input
	* @return 				returns the option back (-1 for invalid option)
	*/
	private static int intInputChecker(int option, Scanner sc) {
		try {
			option = sc.nextInt();
			sc.nextLine();	// clear the buffer
			return option;
		}
		catch (Exception e){
			System.out.println("Input is not an integer");
			sc.nextLine();	// clear the buffer
			return -1;
		}
	}
	
}
