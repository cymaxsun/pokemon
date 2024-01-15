package moves;

import java.util.Random;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class LeechSeed extends PokemonMove{
	
	public LeechSeed() {
		super();
		setName("Leech Seed");
		setType(PokemonTypes.GRASS);
		setPower(0);
		setMaxCharges(20);
		setAcc(90);
		setSFX(getClass().getResource("/sound/Leech Seed.wav"));
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
		dmg = target.getMaxHp()/8;
		return dmg;
		
	}
	
	@Override
	public void moveHit(Pokemon attacker) {
		heal(attacker, dmg);
	}
}
