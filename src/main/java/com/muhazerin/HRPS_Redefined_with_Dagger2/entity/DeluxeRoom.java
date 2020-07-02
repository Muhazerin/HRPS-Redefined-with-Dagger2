package com.muhazerin.HRPS_Redefined_with_Dagger2.entity;

import java.io.Serializable;

/**
 * 
 * @author muhazerin
 *
 */

public class DeluxeRoom extends Room implements Serializable{
	private static final long serialVersionUID = 1L;
	private double rate;
	private String roomType;
	
	public DeluxeRoom(BedType bedType, AvailabilityStatus availabilityStatus, boolean wifiEnabled,
			String facing, boolean smokingAllowed, int roomLevel, int roomNumber) {
		super(bedType, availabilityStatus, wifiEnabled, facing, smokingAllowed, roomLevel, roomNumber);
		rate = 125;
		roomType = "Deluxe";
	}

	@Override
	public double getRate() {
		return rate;
	}

	@Override
	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String getRoomType() {
		return roomType;
	}
}
