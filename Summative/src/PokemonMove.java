import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class PokemonMove {
	
	String name;
	String type;
	Color typeColor;
	Image button;
	Image buttonPressed;
	int baseAtk;
	int currentAtk;
	int charges;
	int maxCharges;
	int acc;
	
	public PokemonMove(String name, String type, int atk, int charges, int acc) {
		this.name = name;
		this.type = type;
		this.baseAtk = atk;
		this.currentAtk = atk;
		this.charges = charges;
		this.maxCharges = charges;
		this.acc = acc;
		
		switch(type){
			case "DARK": 
				typeColor = Color.DARK_GRAY;
				break;
			case "GRASS":
				typeColor = Color.GREEN;
				break;
		}
		
		button = new ImageIcon("resources/"+type+".png").getImage();
		buttonPressed = new ImageIcon("resources/"+type+"Pressed.png").getImage();
	}
	
	public abstract ArrayList<String> useMove(Pokemon attacker, Pokemon target);

}
