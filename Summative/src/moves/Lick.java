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


	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker,target);
	}
	
}
