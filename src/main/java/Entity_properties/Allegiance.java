package Entity_properties;

/**
 * Created by Chris on 09/04/2016.
 * Whose side are you on anyway?
 */
public class Allegiance {

    public static final int  Player=0;
    public static final int  HomeWorld=1;//player controlled, should only change orbit when ordered.//Will
    public static final int  Aliens=2;//AI controlled
    public static final int  Unocupied=3; //Just orbiting. possibly claimable, eg. empty wreck, asteroid, empty ship.
    public static final int  Debris=4;
    public static final int  Untouchable=5; //the sky.
    public static final int  None=6; //the sky.

}


