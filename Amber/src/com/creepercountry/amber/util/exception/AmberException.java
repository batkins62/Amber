package com.creepercountry.amber.util.exception;

public class AmberException extends Exception
{
	private static final long serialVersionUID = 468165862468418661L;
	
	public AmberException()
	{
		super("unknown");
	}

	public AmberException(String message)
	{
		super(message);
	}
}
