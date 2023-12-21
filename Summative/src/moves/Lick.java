package moves;
import java.util.Random;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Lick extends PokemonMove {

	public Lick() {
		super("Lick", PokemonTypes.GRASS, 50, 20, 100);
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
		ApplicationData.sfx.playFile(3,1.0f);
		Random random = new Random();
		if (random.nextInt(101)<=acc) {
			ApplicationData.eventQueue.add(()->playSFX());
			target.setCurrentHp(target.getCurrentHp()-(baseAtk+attacker.getBonusAtk()));
			ApplicationData.animate.addHpAnimation(target);
			
		} else {
			ApplicationData.animate.addTextAnimation(prefix + attacker.getName() + " tripped and fell...");
			ApplicationData.animate.addTextAnimation("The move had no effect!");
		}
		charges -= 1;
	}


}
