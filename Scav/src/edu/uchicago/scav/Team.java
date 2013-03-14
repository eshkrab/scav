package edu.uchicago.scav;

import java.util.List;

public class Team {
    public final String aTeamName;
    public final String aCaptain;
    public final List<String> aUserNames;

    public Team(String aTeamName, String aCaptain, List<String> aUserNames)
    {
        this.aTeamName = aTeamName;
        this.aCaptain = aCaptain;
        this.aUserNames = aUserNames;
    }
}
