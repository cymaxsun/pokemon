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

		 
	}

	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker,target);
		
	}
}
