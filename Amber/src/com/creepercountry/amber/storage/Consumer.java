package com.creepercountry.amber.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.bukkit.scheduler.BukkitRunnable;

import com.creepercountry.amber.AmberPlugin;
import com.creepercountry.amber.Notifier;
import com.creepercountry.amber.Notifier.NotifierLevel;
import com.creepercountry.amber.objects.handler.UserHandler;
import com.creepercountry.amber.objects.user.User;
import com.creepercountry.amber.objects.user.UserBuilder;
import com.creepercountry.amber.storage.config.Config;
import com.creepercountry.amber.storage.config.ConfigBuilder;
import com.creepercountry.amber.util.StopWatch;

public class Consumer extends BukkitRunnable
{
	private final Queue<Object> queue = new LinkedBlockingQueue<Object>();
	private final StopWatch sw;
	private final Lock lock = new ReentrantLock();
	private final String newLine = System.getProperty("line.separator");
	private UserHandler uh;

	public Consumer(AmberPlugin instance)
	{
		this.uh = instance.getStorageManager().getUserHandler();
		this.sw = instance.getStopWatch();
		this.uh = instance.getStorageManager().getUserHandler();
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
		BufferedWriter fout = null;
		File file;
		
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
				
				if (obj instanceof User)
				{
					User user = (User) obj;
					file = new File(uh.getUserFilename(user.getName()));		
					file = FileMgmt.CheckYMLExists(file);
					
					fout = new BufferedWriter(new FileWriter(file));
					LinkedHashMap<Short, String> userconfig = UserBuilder.buildConfig(user);
					for (short line=1; line<=UserBuilder.getSize(); line++)
						fout.write(userconfig.get(line));
				}
				
				if (obj instanceof Config)
				{
					Config config = (Config) obj;
					file = FileMgmt.CheckYMLExists(config.getFile());
					
					fout = new BufferedWriter(new FileWriter(file));
					LinkedHashMap<Short, String> mainconfig = ConfigBuilder.buildConfig();
					for (short line=1; line<=ConfigBuilder.getSize(); line++)
						fout.write(mainconfig.get(line));
				}
				
				if (obj instanceof String)
				{
					String string = (String) obj;
					
					if (string.equals("UserList"))
					{
						file = new File(uh.getUserListFilename());		
						file = FileMgmt.CheckYMLExists(file);
						
						fout = new BufferedWriter(new FileWriter(file));
						for (String name : uh.getUserList())
							fout.write(name + newLine);
					}
				}
				
				fout.close();
				count++;
			}
			
			sw.setLoad("Consumer Run", System.nanoTime() - start);
		}
		catch (Exception e)
		{
			Notifier.logException(e);
		}
		finally
		{
			try { fout.close(); } catch (IOException e) {}
			lock.unlock();
		}
	}

	public int getQueueSize()
	{
		return queue.size();
	}
}