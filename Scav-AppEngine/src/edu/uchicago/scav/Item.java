package edu.uchicago.scav;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * Item class
 * for items
 * use this class when you need items 
 * because it's an item class
 * for items
 */

@Entity
public class Item {
	
	@Id
	public final int number;
	public final String name;
	public final String description;
	public  String status;
	public final int points;

	
	
	
	public Item(int number, String name, String description, String status, int points)
	{
		this.number = number;
		this.name = name;
		this.description = description;
		this.status = status;
		this.points = points;
	}
	
	//Getters
	public int  getNumber(){
		return number;
	}

	public String  getName(){
		return name;
	}
	public String  getDescription(){
		return description;
	}
	public String  getStatus(){
		return status;
	}
	public int  getPoints(){
		return points;
	}
	
	//Setters

		public void  getStatus( String stat){
			this.status=stat;
		}

}
