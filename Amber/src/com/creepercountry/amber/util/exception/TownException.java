package com.creepercountry.amber.util.exception;

public class TownException extends Exception
{
	private static final long serialVersionUID = -6821768221748544277L;

	public TownException() {
		super("unknown");
	}

	public TownException(String message)
	{
		super(message);
	}
}