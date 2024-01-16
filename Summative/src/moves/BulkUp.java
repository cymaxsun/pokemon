package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class BulkUp extends PokemonMove {
	public BulkUp() {
		super();
		setName("Bulk Up");
		setType(PokemonTypes.FIGHTING);
		setPower(0);
		setMaxCharges(20);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Bulk Up.wav"));
	}
	
	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub\	
		super.useMove(attacker, target);
		if (charges < 0) {
			setCharges(0);
			return;
		}
		ApplicationData.eventQueue.add(() -> playSFX());
		attacker.setAtkStage(attacker.getAtkStage()+1);
		attacker.setDefStage(attacker.getDefStage()+1);
		
			

	}

}
