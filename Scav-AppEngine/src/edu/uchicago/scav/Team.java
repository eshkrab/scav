package edu.uchicago.scav;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.datastore.Key;

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
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String description;
	@Persistent(mappedBy = "team")
	private Player captain;
	@Persistent(mappedBy = "team")
	public List<Player> members;

	
	public Team()
	{
		
	}
	
	public Team(String name, String description, Player cap)
	{
		this.name = name;
		this.description = description;
		this.captain = cap;
	}
	
	//Get&Set
	public int getId() {
	    return id;
	  }

	  public void setId(int id) {
	    this.id = id;
	  }
	public String  getName(){
		return name;
	}

	public String  getDescription(){
		return description;
	}
	public void  setDescription(String desc){
		this.description=desc;
	}
	
	public Player  getCaptain(){
		return captain;
	}
	public void  setCaptain(Player cap){
		this.captain=cap;
	}
	
	public List<Player>  getMembers(){
		return members;
	}
			
	@ApiMethod
	//Add member method
		public void addMember(Player member){
			this.members.add(member);
		}
}
