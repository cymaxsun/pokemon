package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class DragonPulse extends PokemonMove {

	public DragonPulse() {
		super();
		setName("Dragon Pulse");
		setType(PokemonTypes.DRAGON);
		setPower(85);
		setMaxCharges(10);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Dragon Pulse.wav"));
	}
	
	
	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker, target);
	}
}
