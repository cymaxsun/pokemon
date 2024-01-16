package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class BulletSeed extends PokemonMove {

	int hits = 1;
	
	public BulletSeed() {
		super();
		// TODO Auto-generated constructor stub
		setName("Bullet Seed");
		setType(PokemonTypes.GRASS);
		setPower(50);
		setMaxCharges(30);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Bullet Seed.wav"));
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub

		super.useMove(attacker, target);
		if (charges < 0) {
			setCharges(0);
			return;
		}
		int x = ApplicationData.random.nextInt(100);
		System.out.println(x);
		if (x < 13) {
			hits = 5;
		} else if (x < 26) {
			hits = 4;
		}  else if (x < 63) {
			hits = 3;
		} else {
			hits = 2;
		}
		
		for (int i = 1 ; i < hits ; i++) {
			ApplicationData.eventQueue.add(() -> playSFX());
		}
		attack(attacker, target);
		
	}
	
	@Override
	public int dmgCalc(Pokemon attacker, Pokemon target) {
		power += hits * 30;
		return hits * super.dmgCalc(attacker, target);
		
	}
	
	@Override
	public void moveHit(Pokemon attacker) {
		ApplicationData.animate.addTextAnimation("Hit " + hits + " times!");
	}
}
