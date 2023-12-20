package moves;

import java.util.Random;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class PhoPhrenzy extends PokemonMove{
	
	public PhoPhrenzy() {
		super("Pho Phrenzy", PokemonTypes.GRASS, -60, 20, 100);
		// TODO Auto-generated constructor stub
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub

		String prefix = "";
		if (charges <= 0) {
			ApplicationData.animate.addTextAnimation("Cannot use that move");
			return;
		}
		
		if (!attacker.isAllied()) {
			prefix = "The enemy ";
		}
		ApplicationData.animate.addTextAnimation(prefix + attacker.getName() + " used " + name + "!");
		if (attacker.getCurrentHp()-baseAtk > attacker.getMaxHp()) {
			attacker.setCurrentHp(attacker.getMaxHp());
		} else {
			attacker.setCurrentHp(attacker.getCurrentHp()-baseAtk);
		}
		ApplicationData.animate.addHpAnimation(attacker);
		ApplicationData.animate.addTextAnimation(prefix + attacker.getName() + " restored its HP!");
		attacker.setCurrentAtk(attacker.getCurrentAtk()+10);
		ApplicationData.animate.addTextAnimation("Brian is high on pho! \nHe gains an attack bonus!");

		charges -= 1;
	}
}