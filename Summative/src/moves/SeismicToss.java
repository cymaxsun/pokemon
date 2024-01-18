package moves;

import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class SeismicToss extends PokemonMove{
	public SeismicToss() {
		super();
		setName("Seismic Toss");
		setType(PokemonTypes.ROCK);
		setPower(0);
		setMaxCharges(20);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Seismic Toss.wav"));
	}

	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attack(attacker, target);
		
	}
	@Override
	public int dmgCalc(Pokemon attacker, Pokemon target) {
		dmg = attacker.getLvl() ;
		return dmg;
	}
}
