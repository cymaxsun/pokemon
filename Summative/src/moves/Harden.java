package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Harden extends PokemonMove{

	
	public Harden() {
		super();
		setName("Harden");
		setType(PokemonTypes.NORMAL);
		setPower(0);
		setMaxCharges(30);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Harden.wav"));
	}
	
	
	public void useMove(Pokemon attacker, Pokemon target) {
		if (charges <= 0) {
			useStruggle(attacker, target);
			return;
		}
		
		super.useMove(attacker, target);
		
		attacker.setDefStage(attacker.getDefStage()+1);
	}
	
}
