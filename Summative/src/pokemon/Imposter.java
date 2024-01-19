package pokemon;

import moves.DracoMeteor;
import moves.DragonPulse;
import moves.Psyshock;
import moves.SusOut;

public class Imposter extends Pokemon{
	
	
	public Imposter() {
		super("Imposter", PokemonTypes.PSYCHIC, PokemonTypes.DRAGON, 100, 364, 350, 394	);
	}
	
	
	@Override
	public void initForBattle() {
		super.initForBattle();
		setMove1(new Psyshock());
		setMove2(new SusOut());
		setMove3(new DragonPulse());
		setMove4(new DracoMeteor());
	}

}
