package game.specialweapon;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * The parent SpecialWeapon class
 *
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 *
 */
public abstract class SpecialWeapon extends WeaponItem {

    /**
     * Constructor.
     * Instantiates a new Special Weapon
     */
    public SpecialWeapon(String name,char displayChar,int damage,String verb,int hitRate){
        super(name, displayChar, damage, verb, hitRate);
    }
}
