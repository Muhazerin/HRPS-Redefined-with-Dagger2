package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import java.util.Scanner;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.GuestManager;

import dagger.BindsInstance;
import dagger.Component;

@Component (modules = FileIOModule.class)
public interface GuestManagerComponent {
	public GuestManager getGuestManager();
	
	@Component.Builder
	interface Builder {
		
		@BindsInstance
		Builder scanner(Scanner sc);
		
		GuestManagerComponent build();
	}
}
