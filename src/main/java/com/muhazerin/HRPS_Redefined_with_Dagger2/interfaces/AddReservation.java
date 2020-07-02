package com.muhazerin.HRPS_Redefined_with_Dagger2.interfaces;

import com.muhazerin.HRPS_Redefined_with_Dagger2.entity.Guest;
import com.muhazerin.HRPS_Redefined_with_Dagger2.entity.Room;

public interface AddReservation {
	public void addReservation(Guest guest, Room room, boolean walkIn);
}
