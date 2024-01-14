package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class ShadowBall extends PokemonMove{
	public ShadowBall() {
		super();
		setName("Shadow Ball");
		setType(PokemonTypes.GHOST);
		setPower(80);
		setMaxCharges(15);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Shadow Ball.wav"));
	}
	
	
	public void useMove(Pokemon attacker, Pokemon target) {
		if (charges <= 0) {
			useStruggle(attacker, target);
			return;
		}
		
		super.useMove(attacker, target);
		attack(attacker,target,dmgCalc(attacker,target));
		if (target.getCurrentHp() <= 0) {
			return;
		}
		if (roll(100)) {
			target.setDefStage(target.getDefStage()-1);
			
		}
		
	}
	
}
