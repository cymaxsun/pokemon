package pokemon;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import moves.Lick;
import moves.Tackle;
import moves.Heal;
import moves.PizzaToss;

public class Brian extends Pokemon{
	
	
	
	
	public Brian( ) {
		super("Brian", PokemonTypes.DARK, 69, 400, 20, 20, new Tackle(), new PizzaToss(), new Lick(),new Heal());
	}
	
	

}
