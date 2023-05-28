package game.specialweapon;

import game.enums.Element;

/**
 * The type Blaze.
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 */
public class Blaze extends SpecialWeapon{

    private static final String NAME= "Blaze";
    private static final char DISPLAY_CHAR='l';
    private static final int DAMAGE=60;
    private static final String VERB="sparks";
    private static final int HITRATE =90;

    /**
     * Constructor.
     * Instantiates a new Blaze Weapon.
     */
    public Blaze(){
        super(NAME, DISPLAY_CHAR, DAMAGE, VERB, HITRATE);
        this.addCapability(Element.FIRE);
    }
}
