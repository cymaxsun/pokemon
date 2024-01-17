package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class EerieImpulse extends PokemonMove {

	
	public EerieImpulse() {
		super();
		setName("Eerie Impulse");
		setType(PokemonTypes.ELECTRIC);
		setPower(0);
		setMaxCharges(15);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Eerie Impulse.wav"));
	}
	
	
	public void useMove(Pokemon attacker, Pokemon target) {
		super.useMove(attacker, target);

	}
	
	
	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		target.setAtkStage(target.getAtkStage()-2);
	}
}
