package moves;

import main.ApplicationData;
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

	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		heal(attacker, attacker.getMaxHp()/2);
	}

}
