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
		setPower(70);
		setMaxCharges(10);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Tackle.wav"));
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		


		if (charges <= 0) {
			useStruggle(attacker, target);
			return;
		}
		
		super.useMove(attacker, target);
		attack(attacker,target, dmgCalc(attacker, target));
		if (target.getCurrentHp() <= 0) {
			return;
		}
		
	}

}
