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
	
	public String name;
	public String description;
	public String status;
	public int points;
	public int duedate;
	
	@Id public int number;
	


	
	
	
	public Item(int number, String name, String description, String status, int points, int due)
	{
		this.number = number;
		this.name = name;
		this.description = description;
		this.status = status;
		this.points = points;
		this.duedate = due;
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
	public int  getDuedate(){
		return duedate;
	}
	
	//Setters

		public void  getStatus( String stat){
			this.status=stat;
		}

}
