package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.behaviours.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * The type Trainer.
 * Created by:
 * @author Shoumil Guha (Std ID: 32700660)
 */
public class Trainer extends Actor {
    /**
     * The Map.
     */
    GameMap map;
    /**
     * TreeMap of the Trainer's behaviours
     * The TreeMap orders the behaciours according to it's priority
     */
    protected final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * Constructor.
     *
     * @param name the name of the Actor
     */
    public Trainer(String name) {
        super(name, 'G', 100);
        this.addCapability(Status.IMMUNE);
        this.behaviours.put(1, new CatchBehaviour());
        this.behaviours.put(2, new FeedBehaviour());
        this.behaviours.put(3, new PickUpBehaviour());
        this.behaviours.put(4, new WanderBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        this.map = map;
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();


    }

    public String toString() {
        return this.name;
    }


}
