package pokemon;
import moves.LeechSeed;
import moves.Lick;
import moves.PizzaToss;
import moves.Tackle;

	public class Brian extends Pokemon{
		
		
		
		
		public Brian( ) {
			super("Brian", PokemonTypes.GRASS, 69, 400, 30, 30, new Tackle(), new PizzaToss(), new Lick(),new LeechSeed());
		}
	
		
		
	
	}
