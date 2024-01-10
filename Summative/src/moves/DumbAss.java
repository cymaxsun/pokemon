package moves;

import java.util.Random;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class DumbAss extends PokemonMove {

	public DumbAss() {
		super();
		setName("Dumbass");
		setType(PokemonTypes.FAIRY);
		setBaseAtk(100);
		setMaxCharges(5);
		setAcc(100);
		// TODO Auto-generated constructor stub
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub\

		
		if (charges <= 0) {
			struggled(attacker, target);
			return;
		}

		attack(attacker,target);
			

	}

}
