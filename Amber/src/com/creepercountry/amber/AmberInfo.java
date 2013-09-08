package com.creepercountry.amber;

import com.creepercountry.amber.util.Version;

public class AmberInfo
{
	/**
	 * Amber Full Version
	 */
    public static Version FULL_VERSION;

    /**
     * set plugin version
     * 
     * @param version
     */
    public static void setVersion(String version)
    {
        String implementationVersion = AmberPlugin.class.getPackage().getImplementationVersion();
        FULL_VERSION = new Version(version + " " + implementationVersion);
    }
}
