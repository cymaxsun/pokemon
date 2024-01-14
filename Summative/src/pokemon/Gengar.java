package pokemon;

import moves.Lick;
import moves.ReflectType;
import moves.ShadowBall;
import moves.SuckerPunch;

public class Gengar extends Pokemon{

	public Gengar() {
		super("Gengar", PokemonTypes.GHOST,  69, 600, 35, 30, new Lick(), new SuckerPunch(), new ReflectType(), new ShadowBall());
		// TODO Auto-generated constructor stub
	}

}
