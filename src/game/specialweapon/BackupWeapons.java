package game.specialweapon;

import edu.monash.fit2099.engine.actors.Actor;
import game.actions.AttackAction;
import game.actors.Pokemon;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by:
 *
 * @author Riordan D. Alfredo Modified by: Andrew Miller Prince (Std ID: 32795467) If a Pokemon needs to use a weapon, put it into that Pokemon's inventory.
 * @see Actor #getWeapon() Actor#getWeapon()Actor#getWeapon()method.
 * @see AttackAction uses getWeapon() in the execute() method.
 */
public class BackupWeapons {

    private static BackupWeapons instance;

    /**
     * A HashMap that stores Pokemon's SpecialWeapons
     */
    private final Map<Pokemon, List<SpecialWeapon>> backupWeaponsMap;


    private BackupWeapons() {
        this.backupWeaponsMap = new HashMap<>();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static BackupWeapons getInstance() {
        if (instance == null) {
            instance = new BackupWeapons();
        }
        return instance;
    }

    /**
     * Add weapon.
     *
     * @param pokemon           the pokemon
     * @param specialWeaponList the special weapon list
     */
    public void addWeapon(Pokemon pokemon, List<SpecialWeapon> specialWeaponList){
        backupWeaponsMap.put(pokemon,specialWeaponList);
    }

    /**
     * Get special weapon list list.
     *
     * @param pokemon the pokemon
     * @return the list
     */
    public List<SpecialWeapon> getSpecialWeaponList(Pokemon pokemon){
        return backupWeaponsMap.get(pokemon);
    }


}
