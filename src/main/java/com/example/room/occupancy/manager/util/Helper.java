package com.example.room.occupancy.manager.util;

import java.text.DecimalFormat;

public class Helper {
	
	public static String formatPrice(double price) {
		DecimalFormat format = new DecimalFormat("0.#");
		return format.format(price);
	}

}
