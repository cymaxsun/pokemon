package pokemon;
import java.awt.Image;

import javax.swing.ImageIcon;

import moves.MonkeyDance;
import moves.PhoGobble;

public class Brian extends Pokemon{
	
	
	
	
	public Brian( ) {
		super("Viet Warrior", PokemonTypes.DARK, 69, 400, 50, new MonkeyDance(), new PhoGobble(), new PhoGobble(),new MonkeyDance());
		setImage(new ImageIcon("res/pokemon/image.png").getImage().getScaledInstance(150, 155, Image.SCALE_SMOOTH));
		// TODO Auto-generated constructor stub
	}
	
	

}
