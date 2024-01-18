package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class ZapCannon extends PokemonMove{

	public ZapCannon() {
		super();
		setName("Zap Cannon");
		setType(PokemonTypes.ELECTRIC);
		setPower(200);
		setMaxCharges(10);
		setAcc(60);
		setSFX(getClass().getResource("/sound/Zap Cannon.wav"));
	}
	

	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker, target);
	}
}
