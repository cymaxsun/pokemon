package moves;

import java.util.Random;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class DumbAss extends PokemonMove {

	public DumbAss() {
		super();
		setName("Dumbass");
		setType(PokemonTypes.NORMAL);
		setPower(50);
		setMaxCharges(10);
		setAcc(100);
		// TODO Auto-generated constructor stub
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub\	
		if (charges <= 0) {
			useStruggle(attacker, target);
			return;
		}
		super.useMove(attacker, target);
		attack(attacker,target);
		if (target.getCurrentHp() <= 0) {
			return;
		}
		
			

	}

}
