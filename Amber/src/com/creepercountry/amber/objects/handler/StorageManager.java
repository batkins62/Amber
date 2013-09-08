package com.creepercountry.amber.objects.handler;

import com.creepercountry.amber.AmberPlugin;
import com.creepercountry.amber.util.StopWatch;

public class StorageManager
{
	private StopWatch sw = AmberPlugin.getInstance().getStopWatch();
	private UserHandler uh;
	
	public StorageManager()
	{
		// Get the current time for StopWatch
		long start = System.nanoTime();
				
		// Register commands
		uh = new UserHandler();
				
		// log to StopWatch
		sw.setLoad("StorageManager", System.nanoTime() - start);
	}
	
	public UserHandler getUserHandler()
	{
		return uh;
	}
}
