package pokemon;

import moves.Flail;
import moves.Harden;
import moves.Recover;
import moves.SwordsDance;

public class Ethan extends Pokemon{



	public Ethan( ) {
		super("Elthan", PokemonTypes.NORMAL, 100, 524, 350, 251);
	}
	
	
	@Override
	public void initForBattle() {
		super.initForBattle();
		setMove1(new SwordsDance());
		setMove2(new Harden());
		setMove3(new Flail());
		setMove4(new Recover());
	}
}
