package moves;

import java.util.Random;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Flail extends PokemonMove {

	public Flail() {
		super();
		setName("Flail");
		setType(PokemonTypes.NORMAL);
		setPower(70);
		setMaxCharges(20);
		setAcc(100);
		// TODO Auto-generated constructor stub
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub\	
		super.useMove(attacker, target);
		if (charges < 0) {
			setCharges(0);
			return;
		}
		attack(attacker,target);
		if (target.getCurrentHp() <= 0) {
			return;
		}
		
		
			

	}
	
	@Override
	public int dmgCalc(Pokemon attacker, Pokemon target) {
		double healthThreshold = (0.0+attacker.getCurrentHp())/attacker.getMaxHp();
		if (healthThreshold >= 0.6875) {
			power = 20;
		} else if (healthThreshold >= 0.3542) {
			power  = 45;
		}else if (healthThreshold >= 0.2083) {
			power  = 80	;
		}else if (healthThreshold >= 0.1042) {
			power  = 100;
		}else if (healthThreshold >= 0.0417) {
			power  = 150;
		}else{
			power  = 200;
		}
		super.dmgCalc(attacker, target);
		
		return dmg;


	}

}
