package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Eruption extends PokemonMove {

	public Eruption() {
		super();
		// TODO Auto-generated constructor stub
		setName("Eruption");
		setType(PokemonTypes.FIRE);
		setPower(200);
		setMaxCharges(10);
		setAcc(90);
		setSFX(getClass().getResource("/sound/Eruption.wav"));
	}

	@Override
	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub

		super.useMove(attacker, target);
		if (charges < 0) {
			setCharges(0);
			return;
		}
		attack(attacker,target);
		if (target.getCurrentHp() <= 0) {
			return;
		}
	}

	@Override
	public int dmgCalc(Pokemon attacker, Pokemon target) {
		power = 200	* attacker.getCurrentHp()/attacker.getMaxHp();
		super.dmgCalc(attacker, target);	
		return dmg;
		
	}
	
}
