package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Pokemon;
import game.enums.Element;
import game.enums.ItemType;
import game.grounds.GroundType;
import game.time.TimePerceptionManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokeEgg extends Item {
    private Pokemon pokemon;
    private int turns= TimePerceptionManager.turn1;
    private Location l1;
    Random randomizer = new Random();

    /**
     * The pokemon instance that is stored inside the pokeegg
     */


    public PokeEgg(Element element) {
        super(element.toString() + " Pokemon Egg",'g', true);
        this.addCapability(element);
        this.addCapability(ItemType.POKEEGG);
    }

    /**
     * Function used to store the a pokemon instance inside a pokeegg. Can only be done once.
     *
     * @param pokemon the pokemon instance to be stored.
     */
    public void storeUnbornedPokemon(Pokemon pokemon){
        this.pokemon=pokemon;
    }


    /**
     * @return The pokemon stored in the pokeegg.
     */
    public Pokemon getStoredUnbornedPokemon(){
        return pokemon;

    }

    /**
     * checks on the turn as well as the element type
     */


    public void checkTurn() {
        if (turns >= 4 && this.hasCapability(Element.FIRE)) {
            hatchPokemon();
        }
        if (turns >= 3 && this.hasCapability(Element.WATER)) {
            hatchPokemon();
        }
        if (turns >= 2 && this.hasCapability(Element.GRASS)) {
            hatchPokemon();
        }
    }

    /**
     * hatches the pokeegg that was placed on the incubator according to the given conditions
     */

    public void hatchPokemon(){
        ArrayList<Location> gfg = new ArrayList<Location>();
        Location alternative;
        if(l1 !=null && !l1.containsAnActor()){
            l1.addActor(pokemon);
            l1.removeItem(this);
        }
        else if (l1 != null && l1.containsAnActor()) {
            List<Exit> allExits = l1.getExits();
            for (Exit eachExit : allExits) {
                Location summonLocation = eachExit.getDestination();
                boolean canSummon = summonLocation.canActorEnter(pokemon);
                if (canSummon) {
                    gfg.add(summonLocation);

                }
            }
            if(gfg.size()!=0) {
                alternative = gfg.get(randomizer.nextInt(gfg.size()));

                alternative.addActor(pokemon);
                l1.removeItem(this);
            }

        }




    }


    @Override
    /**
     * allows the passage of time to take affect
     */
    public void tick(Location currentLocation){
        l1=currentLocation;
        Ground pokeEggGround= currentLocation.getGround();
        boolean checkGround =pokeEggGround.hasCapability(GroundType.INCUBATOR);
        if (checkGround) {
            System.out.println(turns);
            checkTurn();
        }
        this.turns++;
    }
}
