package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.CatchPokemonAction;
import game.actions.FeedPokemonAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Element;
import game.enums.Status;
import game.enums.ItemType;
import game.pokemon.AffectionManager;
import game.actions.EvolveAction;
import game.specialweapon.BackupWeapons;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.*;
import game.specialweapon.*;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * The parent Pokemon class
 *
 * Created by:
 * @author Andrew Miller Prince (Std ID: 32795467)
 *
 */
public abstract class Pokemon extends Actor{

    /**
     * TreeMap of Pokemon behaviours
     * The TreeMap orders the behaciours according to it's priority
     */
    protected final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * The initial hitPoints of each Pokemon
     */
    private static final int hitPoints=100;

    private int turn = 0;

    /**
     * Constructor.
     * Instantiates a new Pokemon.
     * @param name        the name
     * @param displayChar the display char
     */
    public Pokemon(String name,char displayChar){
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE);
        this.addCapability(Status.CANFEED);
        addAffection();
        this.behaviours.put(20, new AttackBehaviour());
        this.behaviours.put(40, new WanderBehaviour());

    }

    /**
     * Overrides the allowableActions method from Actor class
     * List the actions the Player can perform on the Pokemon
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(checkCatchable());
        actions.add(checkFeed(otherActor, Element.WATER));
        actions.add(checkFeed(otherActor,Element.FIRE));
        actions.add(checkFeed(otherActor,Element.GRASS));
        actions.add(checkEvolveAP());
        return actions;
    }

    /**
     * By using behaviour loops, it will decide what will be the next action automatically.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        turn++;
        checkEvolveTurns();
        createFollowBehavior();
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Adds a Pokemon's Special Weapon to the HashMap in BackupWeapons.
     */
    public abstract void createSpecialWeapon();

    /**
     * Checks if Pokemon and Ground has the same element and returns a boolean.
     *
     * @param actor the actor
     * @param map   the map
     * @return the boolean
     */
    public abstract boolean checkGround(Actor actor,GameMap map);

    /**
     * Toggles Pokemon's weapon to Special or Intrinsic using Boolean value passed in.
     *
     * @param isEquipping to activate toggle
     */
    public void toggleWeapon(boolean isEquipping,GameMap map) {
        List<SpecialWeapon> specialWeaponList= BackupWeapons.getInstance().getSpecialWeaponList(this);
        Random rand = new Random();
        int maxValue = specialWeaponList.size();
        int randomIndex = rand.nextInt(maxValue);
        Item specialWeaponAdd = specialWeaponList.get(randomIndex);
        List<Item> inventory = this.getInventory();
        boolean checkInventory = inventory.isEmpty();

        if (!checkInventory){
            for (int i = 0; i <inventory.size(); i++) {
                Item eachItem = inventory.get(i);
                this.removeItemFromInventory(eachItem);
            }
        }
        if (isEquipping) {
            addItemToInventory(specialWeaponAdd);
        }

        if(specialWeaponAdd.hasCapability(WeaponType.SPECIAL_ACTION_WEAPON)){
            SpecialWeaponsInterface specialWeaponAdd1 = (FireSpin) specialWeaponAdd;
            specialWeaponAdd1.specialAction(this,map);
        }

    }

    /**
     * Check's if the Catchable and returns CatchPokemonAction
     *
     * @return the action
     */
    public Action checkCatchable(){
        boolean checkIfPokemon = this.hasCapability(Status.CATCHABLE);
        if(checkIfPokemon){
            return new CatchPokemonAction(this);
        }
        return null;
    }

    /**
     * Checks if Pokefruit is in the player inventory and returns FeedPokemonAction
     *
     * @param player the player
     * @param element Element of the Pokefruit
     * @return Action FeedPokemonAction
     */
    public Action checkFeed(Actor player,Element element){
        List<Item> playerInventory = player.getInventory();
        for(Item items:playerInventory){
            if (items.hasCapability(ItemType.POKEFRUIT)&&items.hasCapability(element)){
                return new FeedPokemonAction(this,items);
            }
        }
        return null;
    }

    /**
     * Adds FollowBehavior to the behaviours TreeMap when AP is greater than 75 and removes it when it gets below 75
     */
    public void createFollowBehavior(){
        AffectionManager affectionManager=getAffectionManager();
        int affectionPoints =affectionManager.getAffectionPoint(this);
        Actor player = affectionManager.getTrainer();
        if(affectionPoints>=75){
            behaviours.put(30,new FollowBehaviour(player));
        }
        else if(affectionPoints<75){
            behaviours.remove(30);
        }
    }


    /**
     * Get affection manager instance
     *
     * @return the affection manager
     */
    public AffectionManager getAffectionManager(){
        AffectionManager affectionManager = AffectionManager.getInstance();
        return affectionManager;
    }

    /**
     * Adds Pokemon to Affection Manager's HashMap
     */
    public void addAffection(){
        AffectionManager affectionManager=getAffectionManager();
        affectionManager.registerPokemon(this);
    }

    public Action checkEvolveAP(){
        AffectionManager affectionManager=getAffectionManager();
        int affectionPoints =affectionManager.getAffectionPoint(this);
        if(affectionPoints>=100 && this.hasCapability(Status.CANEVOLVE) ){
            return new EvolveAction(this);
        }
        return null;
    }

    public void checkEvolveTurns(){
        if (turn>=20 && this.hasCapability(Status.CANEVOLVE)){
            behaviours.put(10,new EvolveBehavior());
            turn=0;
        }
    }

    public abstract boolean evolvePokemon(Location pokemonLocation);


    /**
     * Pokemon's toString()
     * @return String
     */
    @Override
    public String toString() {
        String ret ="";
        ret+=name;
        ret+=printHp();
        ret+=getAffectionManager().printAp(this);
        return ret;
    }
}
