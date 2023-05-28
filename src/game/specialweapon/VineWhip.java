package game.specialweapon;

/**
 * A class of SpecialWeapon: VineWhip
 *
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 *
 */
public class VineWhip extends SpecialWeapon{
    private static final String NAME= "Vine Whip";
    private static final char DISPLAY_CHAR='l';
    private static final int DAMAGE= 30;
    private static final String VERB= "whips";
    private static final int HITRATE =70;

    /**
     * Constructor.
     * Instantiates a new Vine Whip Weapon.
     */
    public VineWhip(){
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HITRATE);
    }
}
