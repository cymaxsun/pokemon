package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class ZapCannon extends PokemonMove{

	public ZapCannon() {
		super();
		setName("Zap Cannon");
		setType(PokemonTypes.ELECTRIC);
		setPower(200);
		setMaxCharges(1);
		setAcc(60);
		setSFX(getClass().getResource("/sound/Zap Cannon.wav"));
	}
	

	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		System.out.println("move hit");
		attack(attacker, target);
	}
}
