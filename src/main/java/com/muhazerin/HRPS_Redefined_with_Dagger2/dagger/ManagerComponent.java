package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;

import javax.inject.Singleton;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.GuestManager;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.MenuItemManager;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.ReservationManager;
import com.muhazerin.HRPS_Redefined_with_Dagger2.control.RoomManager;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component (modules = FileIOModule.class)
public interface ManagerComponent {
	public GuestManager getGuestManager();
	public RoomManager getRoomManager();
	public MenuItemManager getMenuItemManager();
	public ReservationManager getReservationManager();
	
	@Component.Builder
	public interface Builder {
		
		@BindsInstance
		public Builder scanner(Scanner sc);
		
		@BindsInstance
		public Builder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService);
		
		public ManagerComponent build();
	}
}
