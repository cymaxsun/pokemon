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
	

	
	
	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attacker.setDefStage(attacker.getDefStage()+1);
	}
}
