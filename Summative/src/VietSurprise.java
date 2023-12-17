import java.util.ArrayList;
import java.util.Random;

public class VietSurprise extends PokemonMove{

	public VietSurprise() {
		super("Vietnamese Surprise", PokemonTypes.DARK, 20, 30, 70);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub
		ArrayList<String> moveUsedText = new ArrayList<String>();
		if (charges <= 0) {
			moveUsedText.add("Cannot use that move");
			return moveUsedText;
		} 
		
		moveUsedText.add(attacker.getName() + " used " + name + "!");
		charges -= 1;
		Random random = new Random();
		if (random.nextInt(101)<acc) {
			target.setCurrentHp(target.getCurrentHp()-dmg);
		} else {
			moveUsedText.add(attacker.getName() + " tripped and fell...");
			moveUsedText.add("The move had no effect!");
		}
		return moveUsedText;
		 
	}

}
