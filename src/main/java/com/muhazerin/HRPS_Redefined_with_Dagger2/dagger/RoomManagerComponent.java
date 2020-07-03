package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import java.util.Scanner;

import javax.inject.Singleton;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.RoomManager;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component (modules = FileIOModule.class)
public interface RoomManagerComponent {
	public RoomManager getRoomManager();
	
	@Component.Builder
	interface Builder {
		
		@BindsInstance
		Builder scanner(Scanner sc);
		
		RoomManagerComponent build();
	}
}
