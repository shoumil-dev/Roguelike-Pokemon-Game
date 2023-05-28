package game.items;

import game.enums.Element;

/**
 * The type Give fruit name.
 */
public class GiveFruitName {

  /**
   * Gets fruit name.
   *
   * @param element the element
   * @return the fruit name
   */
  public static String getFruitName(Element element) {
    switch (element) {
      case WATER:
        return "Water Fruit";
      case GRASS:
        return "Grass Fruit";
      case FIRE:
        return "Fire Fruit";
    }
    return "No fruit name given";
  }
}
