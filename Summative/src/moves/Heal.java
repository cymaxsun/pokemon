package moves;

import java.util.Random;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Heal extends PokemonMove{
	
	public Heal() {
		super("Heal", PokemonTypes.GRASS, -60, 20, 100);
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
		ApplicationData.eventQueue.add(()->playSFX());	
		ApplicationData.animate.addHpAnimation(attacker);
		ApplicationData.animate.addTextAnimation(prefix + attacker.getName() + " restored its HP!");
		
		attacker.setBonusAtk(attacker.getBonusAtk()+10);
		ApplicationData.animate.addTextAnimation("Brian is reinvigorated! \nHe gains an attack bonus!");

		charges -= 1;
	}
	
	public void playSFX() {
		ApplicationData.sfx.playFile(2,1.0f);
		ApplicationData.eventQueue.pop().run();
	}
}
