package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.ReservationManager;

import dagger.BindsInstance;
import dagger.Component;

@Component (modules = FileIOModule.class)
public interface ReservationManagerComponent {
	public ReservationManager getReservationManager();
	
	@Component.Builder
	interface Builder {
		
		@BindsInstance
		Builder scanner(Scanner sc);
		
		@BindsInstance
		Builder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService);
		
		ReservationManagerComponent build();
	}
}
