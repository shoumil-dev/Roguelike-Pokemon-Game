package game.pokemoncenter;

import java.util.Scanner;

/**
 * The type Menu input.
 */
public class MenuInput {

    /**
     * Used to pick a choice from a menu.
     *
     * @return the selected choice.
     */
    public static int getMenuItem() {
        int choice=0;
        Scanner sel = new Scanner(System.in);
        System.out.println("What would you like to buy?");
        System.out.println("1) Fire Pokefruit");
        System.out.println("2) Water Pokefruit");
        System.out.println("3) Grass Pokefruit");
        System.out.println("4) Fire Pokemon Egg");
        System.out.println("5) Water Pokemon Egg");
        System.out.println("6) Grass Pokemon Egg");
        System.out.print("Select one:");
        choice = Integer.parseInt(sel.nextLine());
        System.out.println("Your choice:"+choice);
        return choice;
    }

    /**
     * Choose the quantity of an item to be bought.
     *
     * @return the quantity chosen.
     */
    public static int chooseQuantity() {
        int choice=0;
        Scanner sel = new Scanner(System.in);
        System.out.println("How many?");
        choice = Integer.parseInt(sel.nextLine());
        System.out.println("Your choice:"+choice);
        return choice;
    }
}
