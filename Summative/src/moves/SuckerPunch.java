package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class SuckerPunch extends PokemonMove {
	public SuckerPunch() {
		super();
		setName("Sucker Punch");
		setType(PokemonTypes.DARK);
		setPower(70);
		setMaxCharges(30);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Sucker Punch.wav"));
	}
	
	
	public void useMove(Pokemon attacker, Pokemon target) {
		if (charges <= 0) {
			useStruggle(attacker, target);
			return;
		}
		
		super.useMove(attacker, target);
		attack(attacker, target, dmgCalc(attacker, target));
		
		if (target.getCurrentHp() <= 0) {
			return;
		}
	}
}
