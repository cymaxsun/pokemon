package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Roost extends PokemonMove{

	
	public Roost() {
		super();
		setName("Roost");
		setType(PokemonTypes.FLYING);
		setPower(0);
		setMaxCharges(10);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Roost.wav"));
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub
		super.useMove(attacker, target);
		if (charges <= 0) {
			setCharges(0);
			return;
		}
		
		
		ApplicationData.eventQueue.add(() -> playSFX());
		heal(attacker, attacker.getMaxHp()/2);
	}
	

}
