package game.specialweapon;

/**
 * A class of SpecialWeapon: Ember
 *
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 *
 */
public class Ember extends SpecialWeapon{
    private static final String NAME= "Ember";
    private static final char DISPLAY_CHAR='l';
    private static final int DAMAGE=20;
    private static final String VERB="sparks";
    private static final int HITRATE =90;

    /**
     * Constructor.
     * Instantiates a new Ember Weapon.
     */
    public Ember(){
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HITRATE);
    }
}
