import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

public class MonkeyDance extends PokemonMove{

	public MonkeyDance() {
		super("Monkey Dance", PokemonTypes.DARK, 80, 10, 100);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub\
		ArrayList<String> outputText = new ArrayList<String>();
		Random random = new Random();
		String prefix = "Your";
		ArrayList<String> statusMessages = new ArrayList<String>();
		int x = random.nextInt(101);
		int statusChance = 0;
		
		if (charges <= 0) {
			outputText.add("Cannot use that move");
			return outputText;
		} 
		
		if (!attacker.isAllied()) {
			prefix = "The enemy ";
		} 
		
		for (Map.Entry<Status, Integer> entry : attacker.getStatuses().entrySet()) {
			switch (entry.getKey()) {
			case ASLEEP:
				break;
			case NORMAL:
				break;
			case PARALYZED:
				statusChance = 100;
				if (x<statusChance) {
					statusMessages.add(prefix + attacker.getName() + " is paralyzed!\n It can't move.");
				}  
				break;
			case POISONED:
				break;
			default:
				break;
			
			}
		}
		
		

		
		if (x >= statusChance) {
			x = random.nextInt(101);
			outputText.add(prefix + attacker.getName() + " used " + name + "!");
			if (x < acc) {
				target.setCurrentHp(target.getCurrentHp()-currentAtk);
				x = random.nextInt(101);
				if (x <= 100) {
					target.addStatus(Status.PARALYZED, 1);
					if (attacker.isAllied()) {
						outputText.add("The wild "+target.getName()+ " is paralyzed in fear!");
					} else {
						outputText.add("Your "+target.getName()+ " is paralyzed in fear!");
					}
				}
			} else {
				outputText.add(attacker.getName()+" tripped and fell...");
				outputText.add("The move had no effect!");
			}
			
		} else {
			outputText.addAll(statusMessages);
		}
		charges -= 1;
		return outputText;
		 
	}
	
}
