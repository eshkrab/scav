package edu.uchicago.scav.rest;

class User
{
    public final String aCnetID;
    public final String aPassword;
    public final String aTeamName;

    public User(String aCnetID, String aPassword, String aTeamName)
    {
        this.aCnetID = aCnetID;
        this.aPassword = aPassword;
        this.aTeamName = aTeamName;
    }
}
