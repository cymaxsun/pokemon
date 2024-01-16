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
		super.useMove(attacker, target);
		if (charges < 0) {
			setCharges(0);
			return;
		}
		ApplicationData.eventQueue.add(() -> playSFX());
		attacker.setDefStage(attacker.getDefStage()+1);
	}
	
}
