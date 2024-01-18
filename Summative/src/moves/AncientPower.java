package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class AncientPower extends PokemonMove{

	public AncientPower() {
		super();
		setName("Ancient Power");
		setType(PokemonTypes.WATER);
		setPower(60);
		setMaxCharges(5);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Ancient Power.wav"));
	}
	

	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker, target);
		if (roll(25)) {
			attacker.setAtkStage(attacker.getAtkStage()+1);
			attacker.setDefStage(attacker.getDefStage()+1);
			
		}
		
	}
}
