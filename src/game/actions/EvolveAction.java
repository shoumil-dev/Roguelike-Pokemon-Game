package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Pokemon;

public class EvolveAction extends Action {
    private Pokemon pokemon;
    public EvolveAction(Pokemon pokemon){
        this.pokemon=pokemon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location pokemonLocation = map.locationOf(pokemon);
        pokemon.evolvePokemon(pokemonLocation);
        return pokemon+"has evolved";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Ash evolves "+pokemon;
    }

}
