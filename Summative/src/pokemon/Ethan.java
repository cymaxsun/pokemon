package pokemon;

import moves.DumbAss;
import moves.Harden;
import moves.Roost;
import moves.SwordsDance;

public class Ethan extends Pokemon{

	public Ethan() {
		super("Elthan", PokemonTypes.NORMAL, 69, 250, 5, 10, new SwordsDance(), new Harden(), new DumbAss(),new Roost());
	}

}
