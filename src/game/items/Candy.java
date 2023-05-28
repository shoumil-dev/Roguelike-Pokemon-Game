package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.enums.ItemType;

/**
 * The candy class. Used to trade for items from the shop.
 * Created by:
 * @author Shoumil Guha (Std ID: 32700660)
 */
public class Candy extends Item {

  /***
   * Constructor of the candy instance, takes no parameters.
   */
  public Candy() {
    super("Candy", '*', true);
    this.addCapability(ItemType.CANDY);
  }
}
