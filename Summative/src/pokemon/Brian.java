package pokemon;
import java.awt.Image;

import javax.swing.ImageIcon;

import moves.Lick;
import moves.MonkeyDance;
import moves.PhoPhrenzy;
import moves.PizzaToss;

public class Brian extends Pokemon{
	
	
	
	
	public Brian( ) {
		super("Brian", PokemonTypes.DARK, 69, 400, 10, 50, new MonkeyDance(), new PizzaToss(), new Lick(),new PhoPhrenzy());
		setImage(new ImageIcon("res/pokemon/image.png").getImage().getScaledInstance(150, 155, Image.SCALE_SMOOTH));
		// TODO Auto-generated constructor stu
	}
	
	

}
