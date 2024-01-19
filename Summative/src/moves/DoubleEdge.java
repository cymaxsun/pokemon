package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class DoubleEdge extends PokemonMove{

	
	public DoubleEdge () {
		super();
		setName("Double Edge");
		setType(PokemonTypes.NORMAL);
		setPower(120);
		setMaxCharges(10);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Double Edge.wav"));
	}
	
	
	@Override
	public void moveHit(Pokemon attacker,Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker, target);
	}
	
	@Override
	public void attack(Pokemon attacker, Pokemon target) {
		dmgCalc(attacker, target);
		dmgTaken(target, dmg);
		effectiveness = 1;
		dmgCalc(attacker,target);
		dmgTaken(attacker,dmg/3);
		dmgApplied(attacker, target);
		if (target.getCurrentHp() <= dmg || attacker.getCurrentHp() <= dmg/3) {
			ApplicationData.gameOver = true;
			return;
		}
	}
	
	@Override
	public void dmgApplied(Pokemon attacker, Pokemon target) {
		super.dmgApplied(attacker, target);
		ApplicationData.animate.addTextAnimation(getAllied(attacker) + attacker.getName() + " is damaged by recoil!");
	}
}
