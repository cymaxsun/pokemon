package pokemon;

import moves.DarkPulse;
import moves.Lick;
import moves.ReflectType;
import moves.ShadowBall;

public class Gengar extends Pokemon{

	public Gengar() {
		super("Gengar", PokemonTypes.GHOST,  100, 324, 394, 273);
		
	}

	

	@Override
	public void initForBattle() {
		super.initForBattle();
		setMove1(new Lick());
		setMove2(new DarkPulse());
		setMove3(new ReflectType());
		setMove4(new ShadowBall());
	}
}
