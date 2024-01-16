package moves;

import main.ApplicationData;
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
	
	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub\	
		super.useMove(attacker, target);
		if (charges < 0) {
			setCharges(0);
			return;
		}
		attack(attacker, target);
		

	}
	
}
