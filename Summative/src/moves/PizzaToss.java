package moves;
import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class PizzaToss extends PokemonMove{
	
	public PizzaToss() {
		super("Pizza Toss", PokemonTypes.GRASS, 40, 5, 100);
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub
		String prefix = "";
		if (charges <= 0) {
			ApplicationData.animate.addTextAnimation("Cannot use that move");
			return;
		}
		if (!attacker.isAllied()) {
			prefix = "The enemy ";
		}
		ApplicationData.animate.addTextAnimation(prefix + attacker.getName() + " used " + name + "!");
		
		charges -= 1;
		target.setCurrentHp(target.getCurrentHp()-(baseAtk+attacker.getCurrentAtk()));
		ApplicationData.animate.addHpAnimation(target);
		
		 
	}

}
