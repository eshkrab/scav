package edu.uchicago.scav;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int number;
	private String name;
	private String description;
	private String status;
	private int points;
	private Date duedate;
	
	
	public Item(){
		
	}

	
	
	public Item(int number, String name, String description, String status, int points, Date due)
	{
		this.number = number;
		this.name = name;
		this.description = description;
		this.status = status;
		this.points = points;
		this.duedate = due;
	}
	
	//Get&Set
	public int  getNumber(){
		return number;
	}
	public void  setNumber(int num){
		this.number=num;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}

	public String  getName(){
		return name;
	}
	public void  setName(String nom){
		this.name=nom;
	}
	public String  getDescription(){
		return description;
	}
	public void  setDescription(String desc){
		this.description=desc;
	}
	public String  getStatus(){
		return status;
	}
	
	public void  setStatus( String stat){
		this.status=stat;
	}
	
	public int  getPoints(){
		return points;
	}
	public void  setPoints(int pts){
		this.points=pts;
	}
	public Date  getDuedate(){
		return duedate;
	}
	public void  setDuedate(Date dat){
		this.duedate=dat;
	}
}
