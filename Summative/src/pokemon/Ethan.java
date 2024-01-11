package pokemon;

import java.io.IOException;

import javax.imageio.ImageIO;

import moves.DumbAss;
import moves.Lick;
import moves.Roost;
import moves.SwordsDance;

public class Ethan extends Pokemon{

	public Ethan() {
		super("Elthan", PokemonTypes.NORMAL, 69, 250, 0, 40, new SwordsDance(), new DumbAss(), new Lick(),new Roost());
	}

}
