package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class BulletSeed extends PokemonMove {

	int hits = 1;
	int totalDmg = 0;

	public BulletSeed() {
		super();
		// TODO Auto-generated constructor stub
		setName("Bullet Seed");
		setType(PokemonTypes.GRASS);
		setPower(25);
		setMaxCharges(30);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Bullet Seed.wav"));
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub
		power = 25;
		totalDmg = 0;
		int x = ApplicationData.random.nextInt(100);
		System.out.println(x);
		if (x < 37.5) {
			hits = 2;
		} else if (x < 75) {
			hits = 3;
		} else if (x < 87.5) {
			hits = 4;
		} else {
			hits = 5;
		}
		super.useMove(attacker, target);
		
	}

	@Override
	public void moveHitText(Pokemon attacker) {
		super.moveHitText(attacker);
		ApplicationData.animate.addTextAnimation("Hit " + hits + " times!");
	}

	@Override
	public void attack(Pokemon attacker, Pokemon target) {

		for (int i = 0; i < hits; i++) {
			ApplicationData.eventQueue.add(() -> playSFX());
			ApplicationData.eventQueue.add(() -> ApplicationData.sfx.playSFX(2));
			dmgTaken(target, super.dmgCalc(attacker, target));
			totalDmg += dmg;
			System.out.println("Dmg: " + this.dmg);
			if (totalDmg >= target.getCurrentHp()) {
				ApplicationData.gameOver = true;
				System.out.println("total dmg to kill: "+totalDmg);
				hits = i+1;
				moveHitText(attacker);
				return;
			}
			power += 25;
		}
		moveHitText(attacker);

	}

	@Override
	public void dmgTaken(Pokemon target, int dmg) {
		super.dmgTaken(target, dmg);
	}

	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		attack(attacker, target);
	}
	@Override
	public int dmgCalc(Pokemon attacker, Pokemon target) {
		return totalDmg;
	}
}
