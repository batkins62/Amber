package com.creepercountry.amber.storage;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.bukkit.scheduler.BukkitRunnable;

import com.creepercountry.amber.AmberPlugin;
import com.creepercountry.amber.Notifier;
import com.creepercountry.amber.Notifier.NotifierLevel;
import com.creepercountry.amber.objects.user.User;
import com.creepercountry.amber.storage.config.Config;
import com.creepercountry.amber.util.StopWatch;

public class Consumer extends BukkitRunnable
{
	private final Queue<Object> queue = new LinkedBlockingQueue<Object>();
	private final StopWatch sw;
	private final Lock lock = new ReentrantLock();

	public Consumer(AmberPlugin instance)
	{
		this.sw = instance.getStopWatch();
	}
	
	public void queueUserSave(User user)
	{
		queue.add(user);
	}
	
	public void queueUserListSave()
	{
		queue.add("UserList");
	}
	
	public void queueConfigSave()
	{
		queue.add(AmberPlugin.getInstance().getConf());
	}

	@Override
	public void run()
	{
		if (queue.isEmpty() || !lock.tryLock())
			return;	
		
		final long start = System.nanoTime();
		
		if (Config.queueWarningSize > 0 && queue.size() >= Config.queueWarningSize)
			Notifier.log(NotifierLevel.HIGHEST, "[Consumer] Queue overloaded. Size: " + Integer.toString(getQueueSize()));
		
		try
		{
			final long proccesstimestart = System.currentTimeMillis();
			int count = 0;
			while (!queue.isEmpty() && (System.currentTimeMillis() - proccesstimestart < Config.timePerRun || count < Config.forceToProccessAtLeast))
			{
				final Object obj = queue.poll();
				if (obj == null)
					continue;
				
				// TODO: prepared sql statement should be ran here.
				
				count++;
			}
			
			sw.setLoad("Consumer Run", System.nanoTime() - start);
		}
		catch (Exception e)
		{
			Notifier.logException(e);
		}
	}

	public int getQueueSize()
	{
		return queue.size();
	}
}