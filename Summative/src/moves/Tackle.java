package moves;

import java.util.Map;
import java.util.Random;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;
import pokemon.Status;

public class Tackle extends PokemonMove {

	public Tackle() {
		super("Tackle", PokemonTypes.DARK, 75, 10, 100);
		// TODO Auto-generated constructor stub
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub\
		Random random = new Random();
		String prefix = "Your ";
		int x = random.nextInt(101);
		int statusChance = 0;

		if (charges <= 0) {
			ApplicationData.animate.addTextAnimation("Cannot use that move");
			return;
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
				statusChance = 25;
				if (x < statusChance) {
					ApplicationData.animate.addTextAnimation(prefix + attacker.getName() + " is paralyzed!\nIt can't move.");
					return;
				}
				break;
			case POISONED:
				break;
			default:
				break;

			}
		}

		x = random.nextInt(101);
		ApplicationData.animate.addTextAnimation(prefix + attacker.getName() + " used " + name + "!");
		if (x < acc) {
			ApplicationData.eventQueue.add(()->playSFX());
			target.setCurrentHp(target.getCurrentHp() - (baseAtk+attacker.getBaseAtk()));
			ApplicationData.eventQueue.add(()->target.getSpritePanel().damageTaken());
			ApplicationData.animate.addHpAnimation(target);
			if (target.getCurrentHp() <= 0) {
				return;
			}
			x = random.nextInt(101);
			if (x >= 100) {
				target.addStatus(Status.PARALYZED, 1);
				if (attacker.isAllied()) {
					ApplicationData.animate.addTextAnimation("The wild " + target.getName() + " \nis paralyzed in fear!");
				} else {
					ApplicationData.animate.addTextAnimation("Your " + target.getName() + " "
							+ "\nis paralyzed in fear!");
				}
			}
		} else {
			ApplicationData.animate
					.addTextAnimation(attacker.getName() + " tripped and fell... \nThe move had no effect!");
		}

		charges -= 1;

	}

}
