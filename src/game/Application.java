package game;

import game.items.Candy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.actors.Player;
import game.grounds.*;
import game.grounds.spawningground.Crater;
import game.grounds.spawningground.Tree;
import game.grounds.spawningground.Waterfall;
import game.enums.Element;
import game.items.Pokefruit;
import game.pokemon.AffectionManager;
import game.pokemon.Charmander;
import game.pokemoncenter.NurseJoy;
import game.actors.Trainer;
import game.maps.MapList;
import game.maps.TeleportManager;

/**
 * The main class to start the game.
 * Created by:
 *
 * @author Riordan D. Alfredo
 * Modified by:
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());




        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(),
                new Floor(), new Tree(),
                new Lava(), new Puddle(),
                new Crater(),new Waterfall(),
                new Hay());

        List<String> map = Arrays.asList(
                ".............................................^^^^^^^^^^^^^^",
                "...........,T,................................,T,..^^^^O^^^",
                ".....................................................^^^^^^",
                "........................................................^^^",
                "............................................,,...........^^",
                "..............................###...........,T............^",
                "....................,T........#_#..........................",
                "..,T,......~...............................................",
                "...~~~~~~~~................................................",
                "....~~~~~..................................................",
                "~~W~~~~.,............................,,,...................",
                "~~~~~~.,T,...........................,T,...................",
                "~~~~~~~~~..................................................");
        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        AffectionManager affectionManager = AffectionManager.getInstance();
        ArrayList<Actor> npcPlayers = new ArrayList<Actor>();

        //Add player - Goh
        Actor t = new Trainer("Goh");
        gameMap.at(24, 3).addActor(t);
        npcPlayers.add(t);

        //Add pokefruits to goh's inventory
        t.addItemToInventory(new Pokefruit(Element.FIRE));
        t.addItemToInventory(new Pokefruit(Element.FIRE));
        t.addItemToInventory(new Pokefruit(Element.FIRE));
        t.addItemToInventory(new Pokefruit(Element.FIRE));

        //Add candies and a charmander to the map
        gameMap.at(24,4).addActor(new Charmander());
        gameMap.at(24, 2).addItem(new Candy());
        gameMap.at(25, 3).addItem(new Candy());
        gameMap.at(23, 3).addItem(new Candy());


        //Add player - Ash
        Player ash = new Player("Ash", '@', 1, npcPlayers);
        affectionManager.registerTrainer(ash);
        world.addPlayer(ash, gameMap.at(32, 10));


        FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor());

        List<String> map2 = Arrays.asList(
                "##################",
                "#______....._____#",
                "#______....._____#",
                "#_______...._____#",
                "#_______...._____#",
                "########___#######");

        GameMap pokeCentre = new GameMap(groundFactory2, map2);
        world.addGameMap(pokeCentre);

        Ground doorTown = new Door(MapList.POKEMON_CENTRE);
        gameMap.at(31,6).setGround(doorTown);
        TeleportManager.getInstance().addLocation(MapList.PALLET_TOWN,gameMap.at(31,6));

        Actor nj = new NurseJoy();
        pokeCentre.at(9,2).addActor(nj);

        Ground doorCentre = new Door(MapList.PALLET_TOWN);
        pokeCentre.at(9,5).setGround(doorCentre);
        TeleportManager.getInstance().addLocation(MapList.POKEMON_CENTRE,pokeCentre.at(9,5));




        Ground e3=new Incubator();
        pokeCentre.at(9,4).setGround(e3);







        world.run();

    }
}
