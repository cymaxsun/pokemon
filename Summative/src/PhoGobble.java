import java.util.ArrayList;

public class PhoGobble extends PokemonMove{
	
	public PhoGobble() {
		super("Pho Gobble", PokemonTypes.GRASS, 100, 30, 100);
	}

	@Override
	public ArrayList<String> useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub
		ArrayList<String> textOutput = new ArrayList<String>();
		String prefix = "";
		if (charges <= 0) {
			textOutput.add("Cannot use that move");
			return textOutput;
		} 
		if (!attacker.isAllied()) {
			prefix = "The enemy ";
		}
		textOutput.add(prefix + attacker.getName() + " used " + name + "!");
		textOutput.add(prefix + attacker.getName() + " restored its HP!");
		charges -= 1;
		if (attacker.getCurrentHp()+baseAtk > attacker.getMaxHp()) {
			attacker.setCurrentHp(attacker.getMaxHp());
		} else {
			attacker.setCurrentHp(attacker.getCurrentHp()+baseAtk);
		}
		return textOutput;
		 
	}

}
