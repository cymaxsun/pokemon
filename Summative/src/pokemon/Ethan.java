package pokemon;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import moves.Heal;
import moves.Lick;
import moves.PizzaToss;
import moves.PokemonMove;
import moves.Tackle;

public class Ethan extends Pokemon{

	public Ethan() {
		super("Elthan", PokemonTypes.NORMAL, 69, 500, 20, 50, new Tackle(), new Tackle(), new Lick(),new Heal());
		try {
			getSpritePanel().setImage(ImageIO.read(getClass().getResourceAsStream("/pokemon/images.png")).getScaledInstance(150, 155, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
