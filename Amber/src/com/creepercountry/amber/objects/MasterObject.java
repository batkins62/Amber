package com.creepercountry.amber.objects;

public abstract class MasterObject
{
	private String name;

	public void setName(String name)
	{
        this.name = name;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return getName();
	}
}
