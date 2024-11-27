package com.moxer_mmh.hotelmanagement.Controller;

public class NoAvailableRoomException extends Exception {
	public NoAvailableRoomException(String message) {
		super(message);
	}
}