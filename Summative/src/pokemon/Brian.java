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
		super("Brian", PokemonTypes.DARK, 69, 400, 10, 50, new Tackle(), new PizzaToss(), new Lick(),new Heal());
		try {
			getSpritePanel().setImage(ImageIO.read(getClass().getResourceAsStream("/pokemon/image.png")).getScaledInstance(150, 155, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
