package edu.uchicago.scav;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PhoneNumber;
import com.google.appengine.api.datastore.Email;

/*
 * Playa class. For Players. Not Haters. Basically, for Scavvies
 */

@Entity
public class Player {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
    private Key key;
	
    private String userID;
	private String pswd;
	public String name;
	public Email email;
	public PhoneNumber phoneNumber;
	public String about;
	public Team team;
	
	public Player(){
		
	}
	
	public Player(String user, String pass)
	{
		this.userID=user;
		this.pswd=pass;
	}
	
	//Get&Set

	public Key getKey() {
	    return key;
	  }

	  public void setKey(Key key) {
	    this.key = key;
	  }
	public String getUserID(){
		return userID;
	}
	public void setUserID(String us){
		this.userID=us;
	}

	public String getPswd(){
		return pswd;
	}
	public void  setPswd(String pass){
		this.pswd = pass;
	}
	
	public String getName(){
		return name;
	}
	public void  setName(String nom){
		this.name = nom;
	}
	
	public Email getEmail(){
		return email;
	}
	public void setEmail(Email address){
		this.email= address;
	}
	
	public PhoneNumber getNumber(){
		return phoneNumber;
	}
	public void setNumber(PhoneNumber number){
		this.phoneNumber=number;
	}
	
	public String getAbout(){
		return about;
	}
	public void  setAbout(String stuff){
		this.about = stuff;
	}
	
	public Team getTeam(){
		return team;
	}
	
	public void setTeam(Team teamname){
		this.team = teamname;
	}

}
