package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Eruption extends PokemonMove {

	public Eruption() {
		super();
		// TODO Auto-generated constructor stub
		setName("Eruption");
		setType(PokemonTypes.FIRE);
		setPower(150);
		setMaxCharges(5);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Lick.wav"));
	}

	@Override
	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub

		if (charges <= 0) {
			useStruggle(attacker, target);
			return;
		}

		super.useMove(attacker, target);
		attack(attacker,target);
		if (target.getCurrentHp() <= 0) {
			return;
		}
	}

	@Override
	public int dmgCalc(Pokemon attacker, Pokemon target) {
		dmg = (power	* attacker.getCurrentHp()/attacker.getMaxHp()) + attacker.getAtk() - target.getDef();
		System.out.println((power	* attacker.getCurrentHp()/attacker.getMaxHp()));
		return dmg;
		
	}
	
}
