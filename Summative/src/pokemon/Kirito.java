package pokemon;

import moves.DumbAss;
import moves.Heal;
import moves.Lick;
import moves.Tackle;

public class Kirito extends Pokemon {

	public Kirito() {
		super("Kirito", PokemonTypes.NORMAL, 69, 200, 5, 50, new Tackle(), new DumbAss(), new Lick(),new Heal());

	}
	
	
}
