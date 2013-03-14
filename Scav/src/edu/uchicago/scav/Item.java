package edu.uchicago.scav;

public class Item {
    public final int aNumber;
    public final String aItemName;
    public final String aDescription;
    public final String aStatus;
    public final int aPoints;
    public final String aDueDate;
    

    public Item(int aNumber, String aItemName, String aDescription, String aStatus, int aPoints, String aDueDate)
    {
        this.aNumber = aNumber;
        this.aItemName = aItemName;
        this.aDescription = aDescription;
        this.aStatus = aStatus;
        this.aPoints = aPoints;
        this.aDueDate = aDueDate;
    }
}
