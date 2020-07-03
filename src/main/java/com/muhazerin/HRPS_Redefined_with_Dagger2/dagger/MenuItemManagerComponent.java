package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import java.util.Scanner;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.MenuItemManager;

import dagger.BindsInstance;
import dagger.Component;

@Component (modules = FileIOModule.class)
public interface MenuItemManagerComponent {
	public MenuItemManager getMenuItemManager();
	
	@Component.Builder
	interface Builder {
		
		@BindsInstance
		Builder scanner(Scanner sc);
		
		MenuItemManagerComponent build();
	}
}
