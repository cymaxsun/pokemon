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
	

	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker,target);
		if (target.getCurrentHp() <= 0) {
			return;
		}
		if (roll(30)) {
			target.setDefStage(target.getDefStage()-1);
			
		}
	}
	
}
