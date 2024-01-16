package pokemon;

import moves.BulletSeed;
import moves.GigaDrain;
import moves.PizzaToss;
import moves.Tackle;

public class Brian extends Pokemon {

	public Brian() {
		super("Brian", PokemonTypes.GRASS, 100, 364, 328, 379);
	}

	@Override
	public void initForBattle() {
		super.initForBattle();
		setMove1(new Tackle());
		setMove2(new PizzaToss());
		setMove3(new BulletSeed());
		setMove4(new GigaDrain());
	}

}
