package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class DracoMeteor extends PokemonMove {

	public DracoMeteor() {
		super();
		setName("Draco Meteor");
		setType(PokemonTypes.DRAGON);
		setPower(130);
		setMaxCharges(5);
		setAcc(90);
		setSFX(getClass().getResource("/sound/Draco Meteor.wav"));
	}
	
	
	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker, target);
		attacker.setAtkStage(attacker.getAtkStage()-1);
	}
}
