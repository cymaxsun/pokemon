package moves;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class Lick extends PokemonMove {


	
	public Lick() {
		super();
		// TODO Auto-generated constructor stub
		setName("Lick");
		setType(PokemonTypes.GHOST);
		setPower(50);
		setMaxCharges(10);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Lick.wav"));
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub

		if (charges <= 0) {
			useStruggle(attacker, target);
			return;
		}

		super.useMove(attacker, target);
		attack(attacker,target, dmgCalc(attacker, target));
		if (target.getCurrentHp() <= 0) {
			return;
		}
	}

}
