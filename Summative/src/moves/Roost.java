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
