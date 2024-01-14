package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Recover extends PokemonMove{

	
	public Recover() {
		super();
		setName("Recover");
		setType(PokemonTypes.NORMAL);
		setPower(0);
		setMaxCharges(5);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Recover.wav"));
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub

		if (charges <= 0) {
			useStruggle(attacker, target);
			return;
		}
		
		super.useMove(attacker, target);
		heal(attacker, attacker.getMaxHp()/2);
	}
	

}
