
public abstract class Pokemon {
	
	String name;
	String type;	
	PokemonMove move1;
	PokemonMove move2;
	PokemonMove move3;
	PokemonMove move4;
	int lvl;
	int maxHp;
	int def;
	int currentHp;
	
	public Pokemon(String name, String type, int lvl, int maxHp, PokemonMove move1, PokemonMove move2, PokemonMove move3, PokemonMove move4) {
		this.name = name;
		this.lvl = lvl;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.type = type;
		this.move1 = move1;
		this.move2 = move2;
		this.move3 = move3;
		this.move4 = move4;
		
	}
	
	public void move1(Pokemon target) {
		// TODO Auto-generated method stub
		
	}


	public void move2(Pokemon target) {
		// TODO Auto-generated method stub
		
	}


	public void move3(Pokemon target) {
		// TODO Auto-generated method stub
		
	}


	public void move4(Pokemon target) {
		// TODO Auto-generated method stub
		
	}
	
}
