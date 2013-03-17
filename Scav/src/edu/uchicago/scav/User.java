package edu.uchicago.scav;

class User
{
    public final String aCnetID;
    public final String aPassword;
    public String aTeamName;
	public String aAbout;
	public String aPhoneNumber;
	public boolean cnetVerified;

    public User(String aCnetID, String aPassword, String aTeamName, String aPhoneNumber, String aAbout, boolean cnetVerified)
    {
        this.aCnetID = aCnetID;
        this.aPassword = aPassword;
        this.aTeamName = aTeamName;
        this.aPhoneNumber = aPhoneNumber;
        this.aAbout = aAbout;
        this.cnetVerified = cnetVerified;
    }
}
