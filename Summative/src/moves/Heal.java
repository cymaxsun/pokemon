package moves;

import java.util.Random;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Heal extends PokemonMove{
	
	public Heal() {
		super();
		setName("Heal");
		setType(PokemonTypes.GRASS);
		setPower(60);
		setMaxCharges(20);
		setAcc(100);
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub

		if (charges <= 0) {
			useStruggle(attacker, target);
			return;
		}

		super.useMove(attacker, target);
		heal(attacker, power);
		attacker.setBaseAtk(attacker.getBaseAtk()+10);
		ApplicationData.animate.addTextAnimation(attacker.getName()+ " is reinvigorated!");
		ApplicationData.animate.addTextAnimation(attacker.getName() + "'s attack rose!");
	}
	
}
