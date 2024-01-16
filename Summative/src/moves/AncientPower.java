package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class AncientPower extends PokemonMove{

	public AncientPower() {
		super();
		setName("Ancient Power");
		setType(PokemonTypes.ROCK);
		setPower(60);
		setMaxCharges(5);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Ancient Power.wav"));
	}
	
	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub\	
		super.useMove(attacker, target);
		if (charges < 0) {
			setCharges(0);
			return;
		}
		attack(attacker, target);
		if (roll(15)) {
			attacker.setAtkStage(attacker.getAtkStage()+2);
			attacker.setDefStage(attacker.getDefStage()+2);
			
		}
		
			

	}
	
}
