package moves;
import java.util.Random;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Lick extends PokemonMove {

	public Lick() {
		super();
		// TODO Auto-generated constructor stub
		setName("Lick");
		setType(PokemonTypes.DARK);
		setBaseAtk(50);
		setMaxCharges(20);
		setAcc(100);
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub

		if (charges <= 0) {
			ApplicationData.animate.addTextAnimation("Cannot use that move");
			return;
		}
		
		attack(attacker,target);
	}

	public void playSFX() {
		ApplicationData.sfx.playFile(3,1.0f);
		try {
			Thread.sleep(ApplicationData.sfx.getLength()/1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.playSFX();
	}
}
