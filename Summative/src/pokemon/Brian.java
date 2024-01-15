package pokemon;

import moves.LeechSeed;
import moves.Lick;
import moves.PizzaToss;
import moves.Tackle;

public class Brian extends Pokemon {

	public Brian() {
		super("Brian", PokemonTypes.GRASS, 100, 301, 259, 236);
	}

	@Override
	public void initForBattle() {
		super.initForBattle();
		setMove1(new Tackle());
		setMove2(new PizzaToss());
		setMove3(new Lick());
		setMove4(new LeechSeed());
	}

}
