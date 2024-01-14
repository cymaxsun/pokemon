package pokemon;

import moves.DumbAss;
import moves.Harden;
import moves.Recover;
import moves.SwordsDance;

public class Ethan extends Pokemon{

	public Ethan() {
		super("Elthan", PokemonTypes.NORMAL, 69, 250, 25, 25, new SwordsDance(), new Harden(), new DumbAss(),new Recover());
	}

}
