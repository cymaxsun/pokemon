import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class PokemonMove {
	
	String name;
	String type;
	Color typeColor;
	int dmg;
	int charges;
	int maxCharges;
	int acc;
	
	public PokemonMove(String name, String type, int dmg, int charges, int acc) {
		this.name = name;
		this.type = type;
		this.dmg = dmg;
		this.charges = charges;
		this.maxCharges = charges;
		this.acc = acc;
		
		switch(type){
			case "DARK": 
				typeColor = Color.DARK_GRAY;
		}
	}
	
	public abstract ArrayList<String> useMove(Pokemon attacker, Pokemon target);

}
