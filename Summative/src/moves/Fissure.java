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
		setAcc(30);
		setSFX(getClass().getResource("/sound/Fissure.wav"));
		ignoreEffectiveness = true;
	}


	@Override
	public int dmgCalc(Pokemon attacker, Pokemon target) {
		dmg = target.getMaxHp();
		return dmg;
		
	}
	@Override
	public void moveHitText(Pokemon attacker) {
		super.moveHitText(attacker);
		ApplicationData.animate.addTextAnimation("It's a one-hit KO!");
	}
	
	
	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker,target);
	}
}
