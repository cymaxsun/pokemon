import java.awt.Color;

public abstract class PokemonMove {
	
	String name;
	String type;
	Color typeColor;
	int dmg;
	int charges;
	int maxCharges;
	
	public PokemonMove(String name, String type, int dmg, int charges) {
		this.name = name;
		this.type = type;
		this.dmg = dmg;
		this.charges = charges;
		this.maxCharges = charges;
		
		switch(type){
			case "DARK": 
				typeColor = Color.DARK_GRAY;
		}
	}
}
