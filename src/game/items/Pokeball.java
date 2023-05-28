package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actors.Pokemon;
import game.enums.ItemType;

/**
 * The pokeball item which only stores one instance of a pokemon.
 * Created by:
 * @author Shoumil Guha (Std ID: 32700660)
 */
public class Pokeball extends Item {

  /**
   * The pokemon instance that is stored inside the pokeball.
   */
  private Pokemon pokemon;

  /***
   * Constructor of the pokeball instance, takes no parameters.
   */
  public Pokeball() {
    super("Pokeball", '0', false);
    this.addCapability(ItemType.POKEBALL);
  }

  /**
   * Function used to store the a pokemon instance inside a pokeball. Can only be done once.
   *
   * @param pokemon the pokemon instance to be stored.
   */
  public void catch_pokemon(Pokemon pokemon) {
    this.pokemon = pokemon;
  }

  /**
   * @return The pokemon stored in the pokeball.
   */
  public Pokemon getStoredPokemon() {
    return pokemon;
  }
}
