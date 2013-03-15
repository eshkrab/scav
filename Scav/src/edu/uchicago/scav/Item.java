package edu.uchicago.scav;

public class Item {
    public final int aNumber;
    public final String aDescription;
    public final String aStatus;
    public final String aPoints;
    public final String aDueDate;
    public final String aOwner;
    
    public Item(int aNumber, String aDescription, String aStatus, String string, String aDueDate, String aOwner)
    {
        this.aNumber = aNumber;
        this.aDescription = aDescription;
        this.aStatus = aStatus;
        this.aPoints = string;
        this.aDueDate = aDueDate;
        this.aOwner = aOwner;
    }
}
