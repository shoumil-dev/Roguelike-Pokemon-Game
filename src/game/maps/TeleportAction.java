package game.maps;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.*;


public class TeleportAction extends Action {

    private MapList teleportMap;


    public TeleportAction(MapList teleportMap){
        this.teleportMap = teleportMap;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location teleportLocation = TeleportManager.getInstance().getLocation(teleportMap);
        GameMap teleportGameMap = teleportLocation.map();
        teleportGameMap.moveActor(actor,teleportLocation);
        String ret="";

        switch (teleportMap){
            case PALLET_TOWN:
                ret= "Ash has entered Pallet Town";
                break;
            case POKEMON_CENTRE:
                ret= "Ash has entered Pokemon Center";
                break;
        }
        return ret;
    }

    @Override
    public String menuDescription(Actor actor) {
        String ret="";
        switch (teleportMap){
            case PALLET_TOWN:
                ret= "Ash has enters Pallet Town";
                break;
            case POKEMON_CENTRE:
                ret= "Ash has enters Pokemon Center";
                break;
        }
        return ret;
    }
}
