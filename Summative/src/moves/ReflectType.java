package moves;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class ReflectType extends PokemonMove{

	public ReflectType () {
		super();
		setName("Reflect Type");
		setType(PokemonTypes.NORMAL);
		setPower(0);
		setMaxCharges(15);
		setAcc(100);
		setSFX(getClass().getResource("/sound/Reflect Type.wav"));
	}
	

	@Override
	public void moveHit(Pokemon attacker, Pokemon target) {
		super.moveHit(attacker, target);
		attacker.setType1(target.getType1());
		attacker.setType2(target.getType2());
		System.out.println("changed to: " + attacker.getTypeName() + " " + target.getType1() + " " + target.getType2());
		System.out.println(attacker.getType1());
		System.out.println(attacker.getType2());
		
		ApplicationData.animate.addTextAnimation(getAllied(attacker) + attacker.getName() + "'s type changed!");
	}
	

}
