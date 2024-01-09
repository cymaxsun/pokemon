package pokemon;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import moves.DumbAss;
import moves.Heal;
import moves.Lick;
import moves.Tackle;

public class Ethan extends Pokemon{

	public Ethan() {
		super("Elthan", PokemonTypes.NORMAL, 69, 500, 20, 50, new Tackle(), new DumbAss(), new Lick(),new Heal());
		try {
			getSpritePanel().setImage(ImageIO.read(getClass().getResourceAsStream("/pokemon/images.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
