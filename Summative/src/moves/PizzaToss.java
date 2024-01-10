package moves;
import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class PizzaToss extends PokemonMove{
	
	public PizzaToss() {
		super();
		setName("Pizza Toss");
		setType(PokemonTypes.GRASS);
		setBaseAtk(70);
		setMaxCharges(10);
		setAcc(100);
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub
		String prefix = "";
		if (charges <= 0) {
			struggled(attacker, target);
			return;
		}

		attack(attacker, target);
		
		
		
		 
	}

}
