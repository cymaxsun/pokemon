import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class PhoGobble extends PokemonMove{

	public PhoGobble() {
		super("Pho Gobble", PokemonTypes.GRASS, 100, 30, 100, new ImageIcon("resources/phoGobble.png").getImage());
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub
		ArrayList<String> textOutput = new ArrayList<String>();
		if (charges <= 0) {
			textOutput.add("Cannot use that move");
			return textOutput;
		} 
		
		textOutput.add(attacker.getName() + " used " + name + "!");
		textOutput.add(attacker.getName() + " restored its HP!");
		charges -= 1;
		if (attacker.getCurrentHp()+dmg > attacker.getMaxHp()) {
			attacker.setCurrentHp(attacker.getMaxHp());
		} else {
			attacker.setCurrentHp(attacker.getCurrentHp()+dmg);
		}
		return textOutput;
		 
	}

}
