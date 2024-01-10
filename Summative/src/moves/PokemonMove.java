package moves;
import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import main.ApplicationData;
import pokemon.Pokemon;

public class PokemonMove {
	
	String name;
	int type;
	String typeName;

	Color typeColor;
	Image button;
	Image buttonPressed;
	int baseAtk;
	int currentAtk;
	int charges;
	int maxCharges;
	int acc;
	private Random random;
	
	public PokemonMove() {
		random = new Random();
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getters and setters for 'type'
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        getButtonImages(this.type);
        this.typeName = Pokemon.getTypeName(type);
        this.typeColor = Pokemon.getTypeColor(type);
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
    public int getBaseAtk() {
        return baseAtk;
    }

    public void setBaseAtk(int baseAtk) {
        this.baseAtk = baseAtk;
    }

    // Getters and setters for 'currentAtk'
    public int getCurrentAtk() {
        return currentAtk;
    }

    public void setCurrentAtk(int currentAtk) {
        this.currentAtk = currentAtk;
    }

    // Getters and setters for 'charges'
    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
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

	public void useMove(Pokemon attacker, Pokemon target) {
		// TODO Auto-generated method stub
	}
	
	public void playSFX() {
		ApplicationData.sfx.playFile(4,1.0f);
		ApplicationData.eventQueue.pop().run();
	}
	
	public void struggled(Pokemon attacker, Pokemon target) {
		ApplicationData.animate.addTextAnimation(attacker.getName() + " can't use " + this.getName()+".");
		ApplicationData.animate.addTextAnimation(attacker.getName() + " used Struggle!");
		target.setCurrentHp(target.getCurrentHp() - (attacker.getBaseAtk() + 50 - target.getBaseDef()));
		ApplicationData.eventQueue.add(()->target.getSpritePanel().damageTaken());
		ApplicationData.animate.addHpAnimation(target);
		attacker.setCurrentHp(attacker.getCurrentHp() - ((attacker.getBaseAtk() + 50 - target.getBaseDef())/4));
		ApplicationData.eventQueue.add(()->attacker.getSpritePanel().damageTaken());
		ApplicationData.animate.addHpAnimation(attacker);
		
	}
	
	public void attack(Pokemon attacker, Pokemon target) {
		String prefix;
		if (!attacker.isAllied()) {
			prefix = "The enemy ";
		} else {
			prefix = "Your ";
		}
		ApplicationData.animate.addTextAnimation(prefix + attacker.getName() + " used " + name + "!");
		int x = random.nextInt(100);
		if (x < acc) {
			System.out.println(x);
			ApplicationData.eventQueue.add(()->this.playSFX());
			target.setCurrentHp(target.getCurrentHp() - (baseAtk+attacker.getBaseAtk()-target.getBaseDef()));
			ApplicationData.eventQueue.add(()->target.getSpritePanel().damageTaken());
			ApplicationData.animate.addHpAnimation(target);
			if (target.getCurrentHp() <= 0) {
				return;
			}
		}
		charges -= 1;
		
	}
	
	public void getButtonImages(int type) {
		 
		 setButton(new ImageIcon("res/buttons/"+Pokemon.getTypeName(type) + ".png").getImage());
         buttonPressed = new ImageIcon("res/buttons/"+Pokemon.getTypeName(type) + "Pressed.png").getImage();
	    
	}

}
