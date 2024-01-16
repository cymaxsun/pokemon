package pokemon;

import moves.DumbAss;
import moves.Harden;
import moves.Lick;
import moves.Recover;
import moves.ReflectType;
import moves.ShadowBall;
import moves.SuckerPunch;
import moves.SwordsDance;

public class Gengar extends Pokemon{

	public Gengar() {
		super("Gengar", PokemonTypes.GHOST,  100, 324, 394, 273);
		
	}

	

	@Override
	public void initForBattle() {
		super.initForBattle();
		setMove1(new Lick());
		setMove2(new SuckerPunch());
		setMove3(new ReflectType());
		setMove4(new ShadowBall());
	}
}
