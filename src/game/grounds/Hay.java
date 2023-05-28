package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Element;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Hay extends Ground {
    /**
     * Constructor.
     */
    public Hay() {
        super(',');
        this.addCapability(Element.GRASS);
    }
}
