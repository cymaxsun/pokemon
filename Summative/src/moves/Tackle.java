package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Tackle extends PokemonMove {

	public Tackle() {
		super();
		setName("Tackle");
		setType(PokemonTypes.NORMAL);
		setPower(70);
		setMaxCharges(10);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Tackle.wav"));
	}


	
	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker, target);
	}

}
