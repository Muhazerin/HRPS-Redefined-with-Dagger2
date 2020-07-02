package com.muhazerin.HRPS_Redefined_with_Dagger2.dagger;

import com.muhazerin.HRPS_Redefined_with_Dagger2.control.ReservationManager;

import dagger.Component;

@Component (modules = {ScannerModule.class, FileIOModule.class})
public interface ReservationManagerComponent {
	public ReservationManager getReservationManager();
}
