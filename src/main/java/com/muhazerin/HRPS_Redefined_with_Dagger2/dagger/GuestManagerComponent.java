package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.GuestManager;

import dagger.Component;

@Component (modules = {FileIOModule.class, ScannerModule.class})
public interface GuestManagerComponent {
	public GuestManager getGuestManager();
}
