package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class SwordsDance extends PokemonMove{

	
	public SwordsDance() {
		super();
		setName("Swords Dance");
		setType(PokemonTypes.NORMAL);
		setBaseAtk(-60);
		setMaxCharges(10);
		setAcc(100);
		// TODO Auto-generated constructor stub
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub

		String prefix = "";
		if (charges <= 0) {
			struggled(attacker, target);
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
		
		attacker.setBaseAtk(attacker.getBaseAtk()+10);
		ApplicationData.animate.addTextAnimation(attacker.getName()+ " is reinvigorated!");
		ApplicationData.animate.addTextAnimation(attacker.getName() + " gains an attack bonus!");
		charges -= 1;
	}
	
}
