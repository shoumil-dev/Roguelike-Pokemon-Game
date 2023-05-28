package game.pokemoncenter;

import static game.enums.ItemType.CANDY;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Element;
import game.actors.Player;
import game.items.PokeEgg;
import game.items.Pokeball;
import game.items.Pokefruit;
import game.pokemon.Bulbasaur;
import game.pokemon.Charmander;
import game.pokemon.Squirtle;

/**
 * The NurseJoy class to buy items from.
 * Created by:
 * @author Shoumil Guha (Std ID: 32700660)
 * modified by :
 * Abdul Harith Bin Abdul Halim (Std :32871341)
 */
public class NurseJoy extends Actor {

  /**
   * List of allowable actions that the NurseJoy instance is allowed to perform.
   */
  ActionList allowableActions = new ActionList(new VisitShop());

  /**
   * Instantiates a new Nurse joy into the world.
   */
  public NurseJoy() {
    super("Nurse Joy", '%', 100);
  }

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    return allowableActions;
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }

  /**
   * The action visit shop that lets the player buy things.
   */
  public static class VisitShop extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
      int choice = MenuInput.getMenuItem();
      if (choice != 4) {
        int quantity = MenuInput.chooseQuantity();
        for (int i = 0; i < quantity; i++) {
          switch (choice) {
            case 1:
              NurseJoy.buyFruit((Player) actor, Element.FIRE);
              break;
            case 2:
              NurseJoy.buyFruit((Player) actor, Element.WATER);
              break;
            case 3:
              NurseJoy.buyFruit((Player) actor, Element.GRASS);
              break;
          }
        }
      }

      if (choice == 4) {
        NurseJoy.buyPokeEggs((Player) actor, Element.FIRE);
      }
      if (choice == 5) {
        NurseJoy.buyPokeEggs((Player) actor, Element.WATER);

      }
      if (choice == 6) {
        NurseJoy.buyPokeEggs((Player) actor, Element.GRASS);


      }
      return "";
    }

    @Override
    public String menuDescription(Actor actor) {
      return (actor + " visits the Pokemon Shop");
    }
  }

  /**
   * Adding a pokemon to the player's inventory while removing candy.
   *
   * @param player      the player instance
   * @param displayChar the display character of the pokemon to be bought
   */
//  private static void buyPokemon(Player player, char displayChar) {
//    Pokeball newBall = new Pokeball();
//    if (displayChar == 'c') {
//      int counter = 0;
//      for (Item item : player.getInventory()) {
//        if (item.hasCapability(CANDY)) {
//          counter++;
//        }
//      }
//      if (counter >= 5) {
//        for (int i = 0; i < 5; i++) {
//          NurseJoy.removeCandy(player);
//        }
//        newBall.catch_pokemon(new Charmander());
//        player.addItemToInventory(newBall);
//        System.out.printf("%s trades %s with 5 candies%n", player.toString(), newBall.getStoredPokemon().toString());
//      } else {
//        System.out.println("Not enough candy in inventory.");
//      }
//    }
//  }


  /**
   * Adding a fruit to the player's inventory while removing candy.
   *
   * @param player  the player instance
   * @param element the element of the pokefruit to be bought
   */
  private static void buyFruit(Player player, Element element) {
    if (NurseJoy.removeCandy(player)) {
      Pokefruit newPokefruit = new Pokefruit(element);
      player.addItemToInventory(newPokefruit);
      System.out.println(String.format("%s trades %s with 1 candies", player.toString(), newPokefruit.toString()));
    }
  }

  /**
   * Removes a candy from the inventory of the player
   *
   * @param player the player instance
   * @return a boolean value depending on whether the candy was successfully removed from the player's inventory
   */
  public static boolean removeCandy(Player player) {
    for (Item item : player.getInventory()) {
      if (item.hasCapability(CANDY)) {
        player.removeItemFromInventory(item);
        return true;
      }
    }
    System.out.println("Not enough candy in inventory.");
    return false;
  }


  /**
   * Adding a pokemon to the player's inventory while removing candy.
   *
   * @param player the player instance
   */
  private static void buyPokeEggs(Player player, Element element) {
    PokeEgg pokeEgg = new PokeEgg(element);
    String retElement = "";
    int counter = 0;

    for (Item item : player.getInventory()) {
      if (item.hasCapability(CANDY)) {
        counter++;
      }
    }
    if (counter >= 5) {
      for (int i = 0; i < 5; i++) {
        NurseJoy.removeCandy(player);
      }
      switch (element) {

        case FIRE:
          pokeEgg.storeUnbornedPokemon(new Charmander());
          retElement = "Fire";
          break;

        case WATER:
          pokeEgg.storeUnbornedPokemon(new Squirtle());
          retElement = "Water";
          break;
        case GRASS:
          pokeEgg.storeUnbornedPokemon(new Bulbasaur());
          retElement = "Grass";
          break;
      }
      player.addItemToInventory(pokeEgg);
      System.out.printf("%s trades %s Poke Egg with 5 candies%n", player.toString(), retElement);
    }
    else {
      System.out.println("Not enough candy in inventory.");


    }

  }
}

