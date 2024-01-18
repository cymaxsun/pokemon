package moves;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

import main.ApplicationData;
import pokemon.Pokemon;
import pokemon.PokemonTypes;

public class PokemonMove {

	String name;
	int type;
	String typeName;
	URL sfx;
	Color typeColor;
	Image button;
	Image buttonPressed;
	int power;
	int dmg;
	int charges;
	int maxCharges;
	int acc;
	float effectiveness;
	float STAB;
	boolean ignoreEffectiveness = false;
	public boolean moveHit;

	public PokemonMove() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public URL getSFX() {
		return sfx;
	}

	public void setSFX(URL sfx) {
		this.sfx = sfx;
	}

	// Getters and setters for 'type'
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		getButtonImages(this.type);
		this.typeName = PokemonTypes.getTypeName(type);
		this.typeColor = PokemonTypes.getTypeColor(type);
	}

	public String getTypeName() {
		return typeName;
	}

	// Getters and setters for 'typeColor'
	public Color getTypeColor() {
		return typeColor;
	}

	// Getters and setters for 'button'
	public Image getButton() {
		return button;
	}

	public void setButton(Image button) {
		this.button = button;
	}

	// Getters and setters for 'buttonPressed'
	public Image getButtonPressed() {
		return buttonPressed;
	}

	public void setButtonPressed(Image buttonPressed) {
		this.buttonPressed = buttonPressed;
	}

	// Getters and setters for 'baseAtk'
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	// Getters and setters for 'charges'
	public int getCharges() {
		return charges;
	}

	public void setCharges(int charges) {
		if (charges < 0) {
			this.charges = 0;
		} else {
			this.charges = charges;
		}

	}

	// Getters and setters for 'maxCharges'
	public int getMaxCharges() {
		return maxCharges;
	}

	public void setMaxCharges(int maxCharges) {
		this.maxCharges = maxCharges;
		setCharges(maxCharges);
	}

	// Getters and setters for 'acc'
	public int getAcc() {
		return acc;
	}

	public void setAcc(int acc) {
		this.acc = acc;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {

		if (dmg <= 0) {
			this.dmg = 1;
		} else {
			this.dmg = dmg;
		}
	}

	public void useMove(Pokemon attacker, Pokemon target) {
		moveHit = false;
		System.out.println("\nMove: " + this.name);
		System.out.println("Attacker: " + attacker.getName());
		System.out.println("Target: " + target.getName());
		System.out.println("\n\nBEFORE\n\n");

		System.out.println(attacker.getName() + " Attack: " + attacker.getAtk());
		System.out.println(attacker.getName() + " Defense: " + attacker.getDef());

		System.out.println(target.getName() + " Attack: " + target.getAtk());
		System.out.println(target.getName() + " Defense: " + target.getDef());
		if (charges <= 0) {
			ApplicationData.animate.addTextAnimation(attacker.getName() + " can't use " + this.getName() + ".");
			ApplicationData.animate.addTextAnimation(attacker.getName() + " used Struggle!");
			ignoreEffectiveness = true;
			acc = 100;
			power = 50;
			sfx = null;
			ApplicationData.eventQueue.add(() -> ApplicationData.sfx.playSFX(2));
			dmgCalc(attacker, target);
			System.out.println("Dmg: " + dmg);
			dmgTaken(target, dmg);

			if (target.getCurrentHp() <= dmg) {
				ApplicationData.gameOver = true;
				return;
			}
			ApplicationData.eventQueue.add(() -> ApplicationData.sfx.playSFX(2));
			System.out.println("Dmg: " + attacker.getMaxHp() / 4);
			dmgTaken(attacker, attacker.getMaxHp() / 4);

			if (attacker.getMaxHp() / 4 >= attacker.getCurrentHp()) {
				ApplicationData.gameOver = true;
			}
			return;
		} else {
			if (ignoreEffectiveness == false && target.getType2() != -1) {
				
				if (target.getType2() != -1) {
					effectiveness = (PokemonTypes.typeChart[getType()][target.getType1()]) * (PokemonTypes.typeChart[getType()][target.getType2()]);
				} else {
					effectiveness = (PokemonTypes.typeChart[getType()][target.getType1()]);
				}
				
			
			} else {
				effectiveness = 1;

			}

			if (attacker.getType1() == getType() || attacker.getType2() == getType()) {
				STAB = 1.5f;
			} else {
				STAB = 1;
			}

			ApplicationData.animate.addTextAnimation(getAllied(attacker) + attacker.getName() + " used " + name + "!");
			if (roll(acc)) {
				moveHit = true;
				moveHit(attacker, target);
				if (target.getCurrentHp() <= 0) {
					return;
				}
			} else {
				moveMissed(attacker);
			}
		}
		System.out.println("\n\nAFTER\n\n");

		System.out.println(attacker.getName() + " Attack: " + attacker.getAtk());
		System.out.println(attacker.getName() + " Defense: " + attacker.getDef());
		System.out.println("Move Power: " + this.getPower());
		
		System.out.println(target.getName() + " Attack: " + target.getAtk());
		System.out.println(target.getName() + " Defense: " + target.getDef());
		System.out.println("move effectivness: " + effectiveness);
		System.out.println("STAB bonus: " + STAB);
		this.charges -= 1;
	}

	public String getAllied(Pokemon attacker) {
		String prefix;
		if (!attacker.isAllied()) {
			prefix = "The enemy ";
		} else {
			prefix = "Your ";
		}
		return prefix;
	}

	public int dmgCalc(Pokemon attacker, Pokemon target) {

		dmg = (int) (((((2 * attacker.getLvl() / 5.0) + 2) * power * attacker.getAtk() / target.getDef()) / 50 + 2)
				* STAB * effectiveness);
		if (dmg <= 0 && effectiveness != 0) {
			dmg = 1;
		}
		return dmg;
	}

	public void useStruggle(Pokemon attacker, Pokemon target) {

	}

	public void playSFX() {
		if (sfx != null) {
			ApplicationData.sfx.playFile(sfx);
			try {
				Thread.sleep(ApplicationData.sfx.getLength() / 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		ApplicationData.eventQueue.pop().run();
	}

	public void attack(Pokemon attacker, Pokemon target) {
		
		dmgCalc(attacker, target);
		System.out.println("Dmg: " + dmg);
		dmgTaken(target, dmg);

		dmgApplied(attacker);
		if (target.getCurrentHp() <= dmg) {
			return;
		}

	}

	public void attack(Pokemon attacker, Pokemon target, int dmg) {
		if (roll(acc)) {
			ApplicationData.eventQueue.add(() -> playSFX());
			dmgTaken(target, dmg);
			dmgApplied(attacker);

		} else {
			moveMissed(attacker);
		}

	}

	public void heal(Pokemon attacker, int healAmount) {

		if (attacker.getCurrentHp() + healAmount > attacker.getMaxHp()) {
			attacker.setCurrentHp(attacker.getMaxHp());
		} else {
			attacker.setCurrentHp(attacker.getCurrentHp() + healAmount);
		}
		ApplicationData.eventQueue.add(() -> ApplicationData.sfx.playSFX(7));
		ApplicationData.animate.addHpAnimation(attacker);
		ApplicationData.animate.addTextAnimation(getAllied(attacker) + attacker.getName() + " restored its HP!");

	}

	public void getButtonImages(int type) {

		setButton(new ImageIcon(
				getClass().getResource("/buttons/" + PokemonTypes.getTypeName(type).toLowerCase() + ".png"))
				.getImage());
		setButtonPressed(new ImageIcon(
				getClass().getResource("/buttons/" + PokemonTypes.getTypeName(type).toLowerCase() + "Pressed.png"))
				.getImage());

	}

	public boolean roll(int acc) {
		int x = ApplicationData.random.nextInt(100);
		return (x < acc);
	}

	public void dmgApplied(Pokemon attacker) {
		if (effectiveness == 0) {
			ApplicationData.animate.addTextAnimation("The move had no effect!");
		} else if (effectiveness <= 0.5f) {
			ApplicationData.animate.addTextAnimation("It was not very effective...");
		} else if (effectiveness >= 2) {
			ApplicationData.animate.addTextAnimation("It was super effective!");
		}
	}

	public void moveMissed(Pokemon attacker) {
		ApplicationData.animate.addTextAnimation(getAllied(attacker) + attacker.getName() + "'s attack missed!");
	}

	public void dmgTaken(Pokemon target, int dmg) {
		if (dmg != 0) {
			ApplicationData.eventQueue.add(() -> ApplicationData.sfx.playSFX(2));
		}
	
		ApplicationData.addToQueue(() -> target.setCurrentHp(target.getCurrentHp() - dmg));
		ApplicationData.eventQueue.add(() -> target.getSpritePanel().damageTaken());
		ApplicationData.animate.addHpAnimation(target);
	}

	public void moveHit(Pokemon attacker, Pokemon target) {

		ApplicationData.eventQueue.add(() -> playSFX());
	}
}
