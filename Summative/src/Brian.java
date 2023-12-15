
public class Brian extends Pokemon{
	
	

	public Brian() {
		super("Brian", PokemonTypes.DARK,69, 420, new MonkeyDance(), new MonkeyDance(), new MonkeyDance(),new MonkeyDance());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move1(Pokemon target) {
		// TODO Auto-generated method stub
		move1.charges -= 1;
		target.currentHp -= move1.dmg;
		
	
		
	}

	@Override
	public void move2(Pokemon target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move3(Pokemon target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move4(Pokemon target) {
		// TODO Auto-generated method stub
		
	}

}
