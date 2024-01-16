package moves;

import java.util.Random;

import main.ApplicationData;
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

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub

		super.useMove(attacker, target);
		if (charges < 0) {
			setCharges(0);
			return;
		}	
		
		attack(attacker, target);
		
	}
	
	@Override
	public int dmgCalc(Pokemon attacker, Pokemon target) {
		dmg = target.getMaxHp()/4;
		return dmg;
		
	}
	
	@Override
	public void moveHit(Pokemon attacker) {
		heal(attacker, dmg/2);
	}
}
