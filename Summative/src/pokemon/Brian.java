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
		super("Brian", PokemonTypes.DARK, 69, 400, 10, 10, new Tackle(), new PizzaToss(), new Lick(),new Heal());
		try {
			getSpritePanel().setImage(ImageIO.read(getClass().getResourceAsStream("/pokemon/image.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
