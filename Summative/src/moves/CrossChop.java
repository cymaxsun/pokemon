package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class CrossChop extends PokemonMove {

	int crit;
	
	public CrossChop() {

		super();
		setName("Cross Chop");
		setType(PokemonTypes.FIGHTING);
		setPower(100);
		setMaxCharges(5);
		setAcc(80);
		setSFX(getClass().getResource("/sound/Cross Chop.wav"));

	}

	@Override 
	public void useMove(Pokemon attacker, Pokemon target) {
		if (roll(25)) {
			crit = 2;
		} else {
			crit = 1;
		}
		super.useMove(attacker, target);
	}
	
	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);

		attack(attacker, target);
	}
	
	@Override
	public int dmgCalc(Pokemon attacker, Pokemon target) {
		dmg = super.dmgCalc(attacker, target) * crit;
		return dmg;
		
	}
	
	@Override
	public void dmgApplied(Pokemon attacker,Pokemon target) {
		super.dmgApplied(attacker, target);
		if (crit == 2 && effectiveness != 0) {
			ApplicationData.animate.addTextAnimation("It was a critical hit!");
		}
	}
}
