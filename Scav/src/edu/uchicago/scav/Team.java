package edu.uchicago.scav;

import java.util.List;

public class Team {
    public final String aTeamName;
    public final List<String> aUserNames;

    public Team(String aTeamName, List<String> aUserNames) {
        this.aTeamName = aTeamName;
        this.aUserNames = aUserNames;
    }
}
