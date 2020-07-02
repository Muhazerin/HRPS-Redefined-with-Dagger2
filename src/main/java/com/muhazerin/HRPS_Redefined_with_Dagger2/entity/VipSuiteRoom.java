package com.muhazerin.HRPS_Redefined_with_Dagger2.entity;

import java.io.Serializable;

/**
 * 
 * @author muhazerin
 *
 */

public class VipSuiteRoom extends Room implements Serializable{
	private static final long serialVersionUID = 1L;
	private double rate;
	private String roomType;
	
	// sets the rate of the room
	public VipSuiteRoom(BedType bedType, AvailabilityStatus availabilityStatus,
			boolean wifiEnabled, String facing, boolean smokingAllowed, int roomLevel, int roomNumber) {
		super(bedType, availabilityStatus, wifiEnabled, facing, smokingAllowed, roomLevel, roomNumber);
		rate = 100;
		roomType = "VIP Suite";
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
