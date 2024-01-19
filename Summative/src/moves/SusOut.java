package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class SusOut extends PokemonMove{
	public SusOut() {
		super();
		setName("Sus Out");
		setType(PokemonTypes.PSYCHIC);
		setPower(90);
		setMaxCharges(10);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Sus Out.wav"));
	}
	

	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker,target);
		if (roll(20)) {
			target.setDefStage(target.getDefStage()-1);
			
		}
	}
	
	@Override
	public void dmgApplied(Pokemon attacker, Pokemon target) {
		super.dmgApplied(attacker, target);
		ApplicationData.animate.addTextAnimation(getAllied(target) + target.getName() + " was sussed out!");
	}
	
}
