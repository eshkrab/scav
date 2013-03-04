package edu.uchicago.scav;

import javax.persistence.*;


import com.google.appengine.api.datastore.PhoneNumber;
import com.google.appengine.api.datastore.Email;

/*
 * Playa class. For Players. Not Haters. Basically, for Scavvies
 */

@Entity
public class Player {
	

    @Id 
    private String userID;
	private String pswd;
	public String name;
	public Email email;
	public PhoneNumber phoneNumber;
	public String about;
	public String team;
	
	public Player(String user, String pass)
	{
		this.userID=user;
		this.pswd=pass;
	}
	
	//Getters

	public String getUserID(){
		return userID;
	}

	public String getPswd(){
		return pswd;
	}
	
	public String getName(){
		return name;
	}
	public Email getEmail(){
		return email;
	}
	public PhoneNumber getNumber(){
		return phoneNumber;
	}
	public String getAbout(){
		return about;
	}
	public String getTeam(){
		return team;
	}
	
	//Setters

	public void  setPswd(String pass){
		this.pswd = pass;
	}
	public void  setName(String nom){
		this.name = nom;
	}
	public void setEmail(Email address){
		this.email= address;
	}
	public void setNumber(PhoneNumber number){
		this.phoneNumber=number;
	}
	public void  setAbout(String stuff){
		this.about = stuff;
	}
	public void  setTeam(String teamname){
		this.team = teamname;
	}

}
