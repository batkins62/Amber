package com.creepercountry.amber.util.exception;

public class NotRegisteredException extends TownException
{
	private static final long serialVersionUID = 175945283391669005L;

	public NotRegisteredException()
	{
		super("Not registered.");
	}

	public NotRegisteredException(String message)
	{
		super(message);
	}
}