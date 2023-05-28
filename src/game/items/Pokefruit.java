package game.items;

import static game.items.GiveFruitName.getFruitName;

import edu.monash.fit2099.engine.items.Item;
import game.enums.Element;
import game.enums.ItemType;

/**
 * The Pokefruit item that can be bought and used.
 * Created by:
 * @author Shoumil Guha (Std ID: 32700660)
 */
public class Pokefruit extends Item {

  /***
   * Constructor of the Pokefruit.
   * @param element The element of the pokefruit.
   */
  public Pokefruit(Element element) {
    super(getFruitName(element),'f', true);
    this.addCapability(element);
    this.addCapability(ItemType.POKEFRUIT);
  }
}
