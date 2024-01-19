package moves;

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

	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		heal(attacker, attacker.getMaxHp()/2);
	}
}
