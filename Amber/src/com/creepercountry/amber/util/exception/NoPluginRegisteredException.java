package com.creepercountry.amber.util.exception;

public class NoPluginRegisteredException extends Exception
{
	private static final long serialVersionUID = 3942893048209384209L;
	
	public NoPluginRegisteredException()
	{
		super("unknown");
	}

	public NoPluginRegisteredException(String message)
	{
		super(message);
	}
}
