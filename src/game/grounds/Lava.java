package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Element;
import game.time.TimePerception;

import java.util.List;
import java.util.Random;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 * Abdul Harith Abdul Halim ID :(32871341)
 *
 */
public class Lava extends Ground implements TimePerception {
    /**
     * Constructor.
     */
    Location l1;
    public Lava() {
        super('^');
        this.addCapability(Element.FIRE);
        this.registerInstance();
    }

    Random rand = new Random();



    /**
     * performs the affect on lava during the day
     */

    @Override
    public void dayEffect() {
        if(l1!=null){
            List<Exit> allExits = l1.getExits();
            for(Exit eachExit:allExits) {
                Location summonLocation = eachExit.getDestination();
                boolean elementCheck = !(summonLocation.getGround().hasCapability(Element.FIRE));
                if(probGroundDay() && elementCheck){
                    summonLocation.setGround(this);

                }

                }
            }


        }




    /**
     * performs the affect on lava during night
     */

    @Override
    public void nightEffect() {
        if (l1 != null) {
            if (probGroundNight() && !l1.containsAnActor()) {
                l1.setGround(new Dirt());


            }
        }
    }




    /**
     * a probability checker returning true if the target value is equal to the number generated.
     * Probability for lava to expand is 10%
     * @return flag
     */

    public boolean probGroundDay() {
        boolean flag = false;
        int target = 3;
        int min = 1;
        int max = 10;

        int randomNum = rand.nextInt((max - min) + 1) + min;

        if (randomNum == target) {
            flag = true;
        }
        return flag;
    }
    /**
     * a probability checker returning true if the target value is equal to the number generated.
     * Probability for lava to be destroyed is 10%
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
    @Override
    public void tick(Location location){
        l1=location;

    }
}
