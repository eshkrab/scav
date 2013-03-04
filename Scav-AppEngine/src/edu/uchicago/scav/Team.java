package edu.uchicago.scav;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * Team class
 * for Teams
 * use this class when you need Teams 
 * because it's a Team class
 * for Teams
 */

@Entity
public class Team {
	
	@Id
	public String name;
	public String description;
	public Player captain;
	public List<Player> members = new ArrayList<Player>();

	
	
	
	public Team(String name, String description, Player cap)
	{
		this.name = name;
		this.description = description;
		this.captain = cap;
	}
	
	//Getters
	public String  getName(){
		return name;
	}

	public String  getDescription(){
		return description;
	}
	public Player  getCaptain(){
		return captain;
	}
	public List<Player>  getMembers(){
		return members;
	}
	
	//Setters

		public void  setDescription(String desc){
			this.description=desc;
		}
		public void  setCaptain(Player cap){
			this.captain=cap;
		}
	

	//Add member method
		public void addMember(Player member){
			this.members.add(member);
		}
}
