package pokemon;

import moves.BulkUp;
import moves.CrossChop;
import moves.DoubleEdge;
import moves.SeismicToss;

public class Shivam extends Pokemon{

	public Shivam() {
		super("Shivam", PokemonTypes.FIGHTING,  100, 384, 394, 284);
		
	}

	

	@Override
	public void initForBattle() {
		super.initForBattle();
		setMove1(new BulkUp());
		setMove2(new DoubleEdge());
		setMove3(new SeismicToss());
		setMove4(new CrossChop());
	}
}
