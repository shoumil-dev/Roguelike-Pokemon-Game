package game.grounds.spawningground;

import java.util.Random;

import game.enums.Element;
import game.items.Pokefruit;
import game.pokemon.Charmander;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;


/**
 * Created by:
 * @author
 * Abdul Harith Abdul Halim  ID :32871341
 *
 */


public class Crater extends SpawningGround {


    Random rand = new Random();
    ActorLocationsIterator a1=new ActorLocationsIterator();
    Charmander c1=new Charmander();




    /**
     * Constructor.
     */
    public Crater() {
        super('O');
        this.addCapability(Element.FIRE);


    }

    /**
     * a probability checker returning true if the target value is equal to the number generated.
     * Probability for squirtle to spawn is 10%
     * @return flag
     */


    @Override
    public boolean probPokeSpawn() {
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
    /**
     * a probability checker returning true if the target value is equal to the number generated.
     * Probability to drop pokefruit is 25%
     * @return flag
     */

    @Override

    public boolean probPokeFruit(){
        boolean flag=false;

        int target=3;
        int min=1;
        int max = 4;

        int randomNum = rand.nextInt((max - min) + 1) + min;

        if (randomNum==target){
            flag=true;
        }

        return flag;

    }
    /**
     * spawn pokemon at location if probPokeSpawn is true and surroundingChecker is true
     */


    @Override
    public void spawnPokemon(Location location) {

        if(probPokeSpawn() && location.canActorEnter(c1)){
            location.addActor(new Charmander());
        }


        }
    /**
     * drops pokefruit at location
     */


    public void dropFruit(Location location) {
        if (probPokeFruit()){
            location.addItem(new Pokefruit(Element.FIRE));
        }
    }




}


