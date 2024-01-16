package pokemon;

import moves.BulkUp;
import moves.Eruption;
import moves.Fissure;
import moves.Recover;

public class Imogen extends Pokemon {

	public Imogen() {
		super("Imogen", PokemonTypes.FIRE, 100, 404, 438, 416);
	}
	
	@Override
	public void initForBattle() {
		super.initForBattle();
		setMove1(new Fissure());
		setMove2(new BulkUp());
		setMove3(new Eruption());
		setMove4(new Recover());
	}
}
