package game.grounds.spawningground;

import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Element;
import game.items.Pokefruit;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import game.pokemon.Squirtle;

/**
 * Created by:
 * Abdul Harith Abdul Halim ID : 32871341
 *
 */
public class Waterfall extends SpawningGround {
    Random rand = new Random();
    ActorLocationsIterator a1 = new ActorLocationsIterator();
    Squirtle s1 = new Squirtle();


    /**
     * Constructor.
     */
    public Waterfall() {
        super('W');
        this.addCapability(Element.WATER);
    }
    /**
     * a probability checker returning true if the target value is equal to the number generated.
     * Probability for squirtle to spawn is 20%
     * @return flag
     */

    @Override

    public boolean probPokeSpawn() {
        boolean flag = false;

        int target = 3;
        int min = 1;
        int max = 5;

        int randomNum = rand.nextInt((max - min) + 1) + min;

        if (randomNum == target) {
            flag = true;
        }

        return flag;
    }


    @Override
    /**
     * a probability checker returning true if the target value is equal to the number generated.
     * Probability to drop pokefruit is 20%
     * @return flag
     */

    public boolean probPokeFruit() {
        boolean flag = false;

        int target = 3;
        int min = 1;
        int max = 5;

        int randomNum = rand.nextInt((max - min) + 1) + min;

        if (randomNum == target) {
            flag = true;
        }
        return flag;

    }
    /**
     * spawn pokemon at location if probPokeSpawn is true and surroundingChecker is true
     */



    @Override
    public void spawnPokemon(Location location) {

        if (probPokeSpawn() && surroundingChecker(location) && location.canActorEnter(s1)) {
            location.addActor(new Squirtle());
        }
    }
    /**
     * drops pokefruit at location
     */



    public void dropFruit(Location location) {
        if (probPokeFruit()) {
            location.addItem(new Pokefruit(Element.WATER));


        }
    }
    /**
     * checks to see if the surrounding contains a grass element
     * @return true if count is more than 2
     */


    public boolean surroundingChecker(Location location) {
        boolean flag=false;
        int counter = 0;
        List<Exit> allExits = location.getExits();
        for(Exit eachExit:allExits){
            Location checkLocation = eachExit.getDestination();
            Ground groundOfLocation = checkLocation.getGround();
            if (groundOfLocation.hasCapability(Element.WATER)){
                counter+=1;
            }
        }
        if (counter >= 2){
            flag = true;
        }
        return flag;
    }



}
