package moves;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class PizzaToss extends PokemonMove{
	
	public PizzaToss() {
		super();
		setName("Pizza Toss");
		setType(PokemonTypes.GRASS);
		setPower(70);
		setMaxCharges(10);
		setAcc(100);
		setSFX(getClass().getResource("/sound/pizzaToss.wav"));
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub

		if (charges <= 0) {
			useStruggle(attacker, target);
			return;
		}
		super.useMove(attacker, target);
		attack(attacker,target, dmgCalc(attacker, target));
		
		
		
		 
	}

}
