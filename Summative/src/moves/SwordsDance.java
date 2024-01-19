package moves;

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

	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attacker.setAtkStage(attacker.getAtkStage()+2);
	}
	
}
