package pokemon;

import moves.Eruption;
import moves.Harden;
import moves.Recover;
import moves.SwordsDance;

public class Imogen extends Pokemon {

	public Imogen() {
		super("Imogen", PokemonTypes.FIRE, 69, 800, 100, 100, new SwordsDance(), new Harden(), new Recover(), new Eruption ());
			
	}

}
