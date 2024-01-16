package moves;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class PizzaToss extends PokemonMove{
	
	public PizzaToss() {
		super();
		setName("Pizza Toss");
		setType(PokemonTypes.POISON);
		setPower(200);
		setMaxCharges(10);
		setAcc(60);
		setSFX(getClass().getResource("/sound/Pizza Toss.wav"));
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub
		super.useMove(attacker, target);
		if (charges < 0) {
			setCharges(0);
			return;
		}
		attack(attacker,target);
		if (target.getCurrentHp() <= 0) {
			return;
		}
		
		
		 
	}

}
