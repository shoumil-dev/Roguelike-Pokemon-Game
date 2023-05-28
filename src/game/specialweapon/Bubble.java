package game.specialweapon;

/**
 * A class of SpecialWeapon: Bubble
 *
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 *
 */
public class Bubble extends SpecialWeapon{
    private static final String NAME= "Bubble";
    private static final char DISPLAY_CHAR='l';
    private static final int DAMAGE=25;
    private static final String VERB="burbles";
    private static final int HITRATE =80;

    /**
     * Constructor.
     * Instantiates a new Bubble Weapon.
     */
    public Bubble(){
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HITRATE);
    }
}
