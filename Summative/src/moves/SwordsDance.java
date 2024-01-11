package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class SwordsDance extends PokemonMove{

	
	public SwordsDance() {
		super();
		setName("Swords Dance");
		setType(PokemonTypes.NORMAL);
		setPower(25);
		setMaxCharges(10);
		setAcc(100);
		setSFX(getClass().getResource("/sound/swordsDance.wav"));
		// TODO Auto-generated constructor stub
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub


		if (charges <= 0) {
			useStruggle(attacker, target);
			return;
		}
		

		super.useMove(attacker, target);
		ApplicationData.eventQueue.add(()->playSFX());	
		ApplicationData.animate.addTextAnimation(attacker.getName() + "'s attack rose greatly!");
		attacker.setBaseAtk(attacker.getBaseAtk()+power);
		
	}
	
}
