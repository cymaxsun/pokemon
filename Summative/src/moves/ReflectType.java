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
	public void useMove(Pokemon attacker, Pokemon target) {
		super.useMove(attacker, target);
		ApplicationData.eventQueue.add(() -> playSFX());
		if (charges < 0) {
			setCharges(0);
			return;
		}
		
		attacker.setType(target.getType());
		ApplicationData.animate.addTextAnimation(getAllied(attacker) + attacker.getName() + "'s type changed!");
		System.out.println(attacker.getTypeName());
	}
	
}
