package pokemon;

import moves.BulkUp;
import moves.Eruption;
import moves.Fissure;
import moves.Recover;

public class Imogen extends Pokemon {

	public Imogen() {
		super("Imogen", PokemonTypes.FIRE, 69, 800, 100, 100, new Fissure(), new BulkUp(), new Recover(), new Eruption ());
	}

}
