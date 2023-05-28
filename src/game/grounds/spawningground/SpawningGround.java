package game.grounds.spawningground;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Created by:
 * @author
 *
 * Abdul Harith Abdul Halim ID :(32871341)
 *
 */

public abstract class SpawningGround extends Ground {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGround(char displayChar) {
        super(displayChar);
    }



    public abstract boolean probPokeSpawn();

    public abstract boolean probPokeFruit();


    public abstract void spawnPokemon(Location location);

    public abstract void dropFruit(Location location);



    @Override
    public void tick(Location location){
        spawnPokemon(location);
        dropFruit(location);
    }
}
