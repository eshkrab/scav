package edu.uchicago.scav;

/*
 * Item class
 * for items
 * use this class when you need items 
 * because it's an item class
 * for items
 */

public class Item {
	
	public final int number;
	public final String name;
	public final String description;
	public final String status;
	public final int points;

	public Item(int number, String name, String description, String status, int points)
	{
		this.number = number;
		this.name = name;
		this.description = description;
		this.status = status;
		this.points = points;
	}

}
