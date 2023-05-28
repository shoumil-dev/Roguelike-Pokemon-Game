package game.grounds.spawningground;

import edu.monash.fit2099.engine.positions.Exit;

import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Element;
import game.grounds.Hay;
import game.items.Candy;
import game.items.Pokefruit;
import game.pokemon.Bulbasaur;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import game.time.TimePerception;
/**
 * Created by:
 * Abdul Harith Abdul Halim ID :(32871341)
 *
 */


public class Tree extends SpawningGround implements TimePerception {
    Random rand = new Random();
    ActorLocationsIterator a1 = new ActorLocationsIterator();
    Bulbasaur b1=new Bulbasaur();
    Location l1;
    Hay h1=new Hay();



    /**
     * Constructor.
     */
    public Tree() {
        super('T');
        this.addCapability(Element.GRASS);
        this.registerInstance();
    }

    @Override
    /**
     * a probability checker returning true if the target value is equal to the number generated.
     * Probability for bulbasaur too spawn is 15%
     * @return flag
     */

    public boolean probPokeSpawn() {
        boolean flag = false;


        int target = 3;
        int min = 1;
        int max = 15;

        int randomNum = rand.nextInt((max - min) + 1) + min;

        if (randomNum == target) {
            flag = true;
        }

        return flag;
    }

    @Override
    /**
     * a probability checker returning true if the target value is equal to the number generated.
     * Probability for pokefruit to be dropped is 15%
     * @return flag
     */

    public boolean probPokeFruit() {
        boolean flag = false;

        int target = 3;
        int min = 1;
        int max = 15;

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
    public void spawnPokemon( Location location) {

        if (probPokeSpawn() && surroundingChecker(location)) {
            if(location.canActorEnter(b1)){
                location.addActor(new Bulbasaur());

            }

        }
    }
    /**
     * checks to see if the surrounding contains a grass element
     * @return true if count is more than 1
     */

    public boolean surroundingChecker(Location location){
        boolean flag=false;
        List<Exit> allExits = location.getExits();
        for(Exit eachExit:allExits){
            Location checkLocation = eachExit.getDestination();
            Ground groundOfLocation = checkLocation.getGround();
            if (groundOfLocation.hasCapability(Element.GRASS)){
                flag=true;
            }
        }
        return flag;
    }

    /**
     * drops fruit at location
     */

    public void dropFruit(Location location) {
        if (probPokeFruit()){
            location.addItem(new Pokefruit(Element.GRASS));


        }
    }
    /**
     * performs the affect on tree during the day
     */

    @Override
    public void dayEffect() {
        if(l1!=null){
            if(probGroundDay()){
                l1.addItem(new Candy());
            }

        }


    }
    /**
     * performs the affect on tree during night
     */

    @Override
    public void nightEffect() {
        if(l1!=null){
            List<Exit> allExits = l1.getExits();
            for(Exit eachExit:allExits) {
                Location summonLocation = eachExit.getDestination();
                boolean elementCheck = !(summonLocation.getGround().hasCapability(Element.GRASS));
                if(probGroundNight() && elementCheck){
                summonLocation.setGround(new Hay());
                }
            }

        }
        }





        /**
         * a probability checker returning true if the target value is equal to the number generated.
         * Probability for tree to drop a candy is 5%
         * @return flag
         */


    public boolean probGroundDay(){
        boolean flag=false;
        int target=3;
        int min=1;
        int max = 5;

        int randomNum = rand.nextInt((max - min) + 1) + min;

        if (randomNum==target){
            flag=true;
        }


        return flag;
    }
    /**
     * a probability checker returning true if the target value is equal to the number generated.
     * Probability tree to expand or turn into hay is 10%
     * @return flag
     */
    public boolean probGroundNight(){
        boolean flag=false;
        int target=3;
        int min=1;
        int max = 10;

        int randomNum = rand.nextInt((max - min) + 1) + min;

        if (randomNum==target){
            flag=true;
        }


        return flag;
    }

}
