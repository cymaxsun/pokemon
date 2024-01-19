package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Psyshock extends PokemonMove {

	public Psyshock() {
		super();
		setName("Psyshock");
		setType(PokemonTypes.PSYCHIC);
		setPower(65);
		setMaxCharges(10);
		setAcc(100);
		setSFX(getClass().getResource("/sound/PsySchock.wav"));
	}
	
	
	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker, target);
	}
	
	@Override
	public int dmgCalc(Pokemon attacker, Pokemon target) {
		dmg = (int) (((((2 * attacker.getLvl() / 5.0) + 2) * power * attacker.getAtk() / (target.getDef()*3/4)) / 50 + 2)
				* STAB * effectiveness);
		if (dmg <= 0 && effectiveness != 0) {
			dmg = 1;
		}
		return dmg;
	}
}