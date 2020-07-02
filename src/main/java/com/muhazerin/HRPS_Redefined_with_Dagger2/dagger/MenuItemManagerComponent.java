package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.MenuItemManager;

import dagger.Component;

@Component (modules = {FileIOModule.class, ScannerModule.class})
public interface MenuItemManagerComponent {
	public MenuItemManager getMenuItemManager();
}
