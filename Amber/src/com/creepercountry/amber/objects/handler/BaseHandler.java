package com.creepercountry.amber.objects.handler;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.creepercountry.amber.AmberPlugin;
import com.creepercountry.amber.objects.user.User;
import com.creepercountry.amber.storage.FileMgmt;

/**
 * Abstract class representing a handler. When run by the storage manager
 * , it pre-processes all the data into more useful forms.
 * Extending classes should adjust required fields in their constructor
 */
public abstract class BaseHandler
{
	protected AmberPlugin plugin = AmberPlugin.getInstance();	
	protected final String newLine = System.getProperty("line.separator");
	protected String rootFolder = plugin.getDataFolder().getPath();
	protected String dataFolder = FileMgmt.fileSeparator() + "data";
	
	protected List<String> userlist = new ArrayList<String>();
	protected Hashtable<String, User> users = new Hashtable<String, User>();
	protected Hashtable<String, List<String>> watchedcmds = new Hashtable<String, List<String>>();
	
	/**
	 * purge all objects, clear them all
	 */
	public synchronized void purgeObjects()
	{
		this.watchedcmds.clear();
		this.users.clear();
		this.userlist.clear();
	}
	
	/**
	 * @return Hashtable of watchedcmds
	 */
	public Hashtable<String, List<String>> getWatchedCmdsMap()
	{
		return this.watchedcmds;
	}
}