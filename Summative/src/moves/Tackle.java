package moves;

import java.util.Random;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Tackle extends PokemonMove {

	public Tackle() {
		super();
		setName("Tackle");
		setType(PokemonTypes.DARK);
		setBaseAtk(70);
		setMaxCharges(10);
		setAcc(100);
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub\


		if (charges <= 0) {
			struggled(attacker, target);
			return;
		}

		attack(attacker, target);

	}

}
