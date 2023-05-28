package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

public class Fire extends Item {

    public Fire(){
        super("Fire",'v',true);
    }

    @Override
    public void tick(Location currentLocation) {
       if(currentLocation.containsAnActor()){
           Actor actor =currentLocation.getActor();
           if(actor.hasCapability(Status.HOSTILE)){
               actor.hurt(10);
           }
       }
    }
}
