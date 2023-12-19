import java.util.ArrayList;
import java.util.Random;

public class Lick extends PokemonMove {

	public Lick() {
		super("Lick", PokemonTypes.GRASS, 50, 20, 100);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub
		ArrayList<String> moveUsedText = new ArrayList<String>();
		String prefix = "";
		if (charges <= 0) {
			moveUsedText.add("Cannot use that move");
			return moveUsedText;
		} 
		
		if (!attacker.isAllied()) {
			prefix = "The enemy ";
		}
		moveUsedText.add(prefix + attacker.getName() + " used " + name + "!");
		
		Random random = new Random();
		if (random.nextInt(101)<=acc) {
			target.setCurrentHp(target.getCurrentHp()-baseAtk);
		} else {
			moveUsedText.add(prefix + attacker.getName() + " tripped and fell...");
			moveUsedText.add("The move had no effect!");
		}
		charges -= 1;
		return moveUsedText;
	}

}
