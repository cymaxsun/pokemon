package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class GigaDrain extends PokemonMove{
	
	public GigaDrain() {
		super();
		setName("Giga Drain");
		setType(PokemonTypes.GRASS);
		setPower(75);
		setMaxCharges(20);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Giga Drain.wav"));
	}
	
	@Override
	public int dmgCalc(Pokemon attacker, Pokemon target) {
		dmg = (int) (target.getMaxHp()/4 * effectiveness * STAB);
		return dmg;
		
	}
	
	@Override
	public void moveHitText(Pokemon attacker) {
		super.moveHitText(attacker);
		heal(attacker, dmg/2);
	}
	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker, target);
	}
}
