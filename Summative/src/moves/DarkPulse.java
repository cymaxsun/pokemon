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
		if (charges < 0) {
			setCharges(0);
			return;
		}
		attack(attacker, target);
		
		if (target.getCurrentHp() <= 0) {
			return;
		}
	}
}
	