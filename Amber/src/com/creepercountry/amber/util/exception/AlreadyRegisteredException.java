package com.creepercountry.amber.util.exception;

public class AlreadyRegisteredException extends TownException
{
	private static final long serialVersionUID = 1651891354786416L;

	public AlreadyRegisteredException()
	{
		super("Not registered.");
	}

	public AlreadyRegisteredException(String message)
	{
		super(message);
	}
}
