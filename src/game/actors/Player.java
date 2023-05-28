package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import game.enums.Status;
import game.actions.TrainerDisplayAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.enums.ItemType;
import game.actions.SummonPokemonAction;
import game.time.TimePerceptionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Player.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Player extends Actor {

	private final Menu menu = new Menu();

	private ArrayList<Actor> interactableNpcPlayers = new ArrayList<Actor>();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, ArrayList<Actor> interactableNpcPlayers) {
		super(name, displayChar, hitPoints);
		this.interactableNpcPlayers = interactableNpcPlayers;
		this.addCapability(Status.IMMUNE);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		List<Item> inventory= this.getInventory();
		System.out.println("inventory: "+inventory);
		TimePerceptionManager.getInstance().run();

		actions.add(checkSummon());
		actions.add(new TrainerDisplayAction(interactableNpcPlayers));


		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar() {
		return super.getDisplayChar();
	}

	/**
	 * Checks if player's inventory has a Pokeball and calls Summon Action.
	 * @return Action to summon Pokemon
	 */
	public Action checkSummon(){
		List<Item> playerInventory = this.getInventory();
		for(Item items:playerInventory){
			if (items.hasCapability(ItemType.POKEBALL)){
				return new SummonPokemonAction(items);
			}
		}
		return null;
	}
}
