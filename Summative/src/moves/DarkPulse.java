package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class DarkPulse extends PokemonMove {
	public DarkPulse() {
		super();
		setName("Dark Pulse");
		setType(PokemonTypes.DARK);
		setPower(100);
		setMaxCharges(30);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Dark Pulse.wav"));
	}
	
	
	public void useMove(Pokemon attacker, Pokemon target) {
		super.useMove(attacker, target);

	}
	
	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		
		attack(attacker, target);
	}
}
	