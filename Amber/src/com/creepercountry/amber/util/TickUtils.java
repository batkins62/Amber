package com.creepercountry.amber.util;

public class TickUtils
{
	public enum TickUnit
	{
		SECOND, MINUTE, HOUR, DAY;
	}
	
	public static long convert(TickUnit unit, int amount)
	{
		int multiplier = 1;
		if (unit.equals(TickUnit.SECOND))
			multiplier = 20;
		if (unit.equals(TickUnit.MINUTE))
			multiplier = 20 * 60;
		if (unit.equals(TickUnit.HOUR))
			multiplier = 20 * 60 * 60;
		if (unit.equals(TickUnit.DAY))
			multiplier = 20 * 60 * 60 * 24;
		return multiplier * amount;
	}
}
