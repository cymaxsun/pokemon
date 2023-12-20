package moves;
import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class PhoGobble extends PokemonMove{
	
	public PhoGobble() {
		super("Pho Gobble", PokemonTypes.GRASS, -100, 5, 100);
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
		if (attacker.getCurrentHp()-baseAtk > attacker.getMaxHp()) {
			attacker.setCurrentHp(attacker.getMaxHp());
		} else {
			attacker.setCurrentHp(attacker.getCurrentHp()-baseAtk);
		}
		ApplicationData.animate.addHpAnimation(attacker);
		ApplicationData.animate.addTextAnimation(prefix + attacker.getName() + " restored its HP!");
		 
	}

}
