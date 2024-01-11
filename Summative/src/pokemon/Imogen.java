package pokemon;

import moves.Harden;
import moves.Roost;
import moves.SwordsDance;
import moves.Tackle;

public class Imogen extends Pokemon {

	public Imogen() {
		super("Imogen", PokemonTypes.FIRE, 69, 800, 50, 50, new SwordsDance(), new Harden(), new Roost(), new Tackle());
			
	}

}
