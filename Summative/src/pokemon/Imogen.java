package pokemon;

import java.io.IOException;

import javax.imageio.ImageIO;

import moves.PizzaToss;
import moves.Roost;
import moves.SwordsDance;
import moves.Tackle;

public class Imogen extends Pokemon {

	public Imogen() {
		super("Imogen", PokemonTypes.FAIRY, 69, 800, 50, 50, new SwordsDance(), new PizzaToss(), new Roost(), new Tackle());
			
	}

}
