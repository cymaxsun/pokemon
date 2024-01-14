package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class SwordsDance extends PokemonMove{

	
	public SwordsDance() {
		super();
		setName("Swords Dance");
		setType(PokemonTypes.NORMAL);
		setPower(0);
		setMaxCharges(10);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Swords Dance.wav"));
		// TODO Auto-generated constructor stub
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub


		if (charges <= 0) {
			useStruggle(attacker, target);
			return;
		}
		

		super.useMove(attacker, target);
		attacker.setAtkStage(attacker.getAtkStage()+2);
		

	}
	
}
