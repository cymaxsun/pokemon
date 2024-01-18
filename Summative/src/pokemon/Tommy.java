package pokemon;

import moves.AncientPower;
import moves.EerieImpulse;
import moves.Roost;
import moves.ZapCannon;

public class Tommy extends Pokemon{

	
	public Tommy() {
		super("Tommy", PokemonTypes.ELECTRIC, PokemonTypes.FLYING, 100, 384, 306, 295);
	}
	
	@Override
	public void initForBattle() {
		super.initForBattle();
		setMove1(new AncientPower());
		setMove2(new Roost());
		setMove3(new ZapCannon());
		setMove4(new EerieImpulse());
	}
	
}
