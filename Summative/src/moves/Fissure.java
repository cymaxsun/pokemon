package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Fissure extends PokemonMove {
	public Fissure() {
		super();
		setName("Fissure");
		setType(PokemonTypes.GROUND);
		setPower(0);
		setMaxCharges(5);
		setAcc(50);
		setSFX(getClass().getResource("/sound/Fissure.wav"));
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub\	
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
		dmg = target.getMaxHp();
		return dmg;
		
	}
	@Override
	public void moveHit(Pokemon attacker) {
		ApplicationData.animate.addTextAnimation("It's a one-hit KO!");
	}
	
}
