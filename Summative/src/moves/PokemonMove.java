package moves;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

import main.ApplicationData;
import pokemon.Pokemon;

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
	private Random random;
	
	public PokemonMove() {
		random = new Random();
		power = 50;	
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
    	System.out.println("\nMove: " + this.name);
    	System.out.println("Attacker: " + attacker.getName());
    	System.out.println("Target: " + target.getName());
    	System.out.println(attacker.getName() + " Attack: " + attacker.getAtk());
    	System.out.println("Move Power: " + this.getPower());
		System.out.println(target.getName()+ " Defense: " + target.getDef());
		System.out.println("Dmg: " + dmgCalc(attacker, target));
    	ApplicationData.animate.addTextAnimation(getAllied(attacker) + attacker.getName() + " used " + name + "!");
		if (charges >  0) {
			charges -= 1;
		} 
		ApplicationData.eventQueue.add(()->playSFX());
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
    	dmg = power+attacker.getAtk()-target.getDef();
    	if (dmg <= 0 ) {
    		return 1;
    	}
    	return dmg;
    }
    
	
	public void useStruggle(Pokemon attacker, Pokemon target) {
    	ApplicationData.animate.addTextAnimation(attacker.getName() + " can't use " + this.getName()+".");
		ApplicationData.animate.addTextAnimation(attacker.getName() + " used Struggle!");
		attack(attacker, target, power);
		attack(target, attacker, attacker.getMaxHp()/4);
	}
	public void playAtkSFX() {
		ApplicationData.sfx.playFile(2);
		ApplicationData.eventQueue.pop().run();
	}
	
	public void playHealSFX() {
		ApplicationData.sfx.playFile(7);
		ApplicationData.eventQueue.pop().run();
	}
	
	public void playSFX() {
		if (sfx != null) {
			ApplicationData.sfx.playFile(sfx);
			try {
				Thread.sleep(ApplicationData.sfx.getLength()/1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		ApplicationData.eventQueue.pop().run();
	}
	

	
	public void attack(Pokemon attacker, Pokemon target, int dmg) {
		int x = random.nextInt(100);
		if (x < acc) {
			//System.out.println(x);
			ApplicationData.eventQueue.add(()->playAtkSFX());
			//System.out.println(baseAtk + " " + attacker.getBaseAtk() + " " + target.getBaseDef() + " " + (baseAtk+attacker.getBaseAtk()-target.getBaseDef()));
			target.setCurrentHp(target.getCurrentHp() - dmg);
			ApplicationData.eventQueue.add(()->target.getSpritePanel().damageTaken());
			ApplicationData.animate.addHpAnimation(target);
			if (target.getCurrentHp() <= 0) {
				return;
			}
		}

		
	}
	
	public void heal(Pokemon attacker, int healAmount) {

		if (attacker.getCurrentHp()+healAmount > attacker.getMaxHp()) {
			attacker.setCurrentHp(attacker.getMaxHp());
		} else {
			attacker.setCurrentHp(attacker.getCurrentHp()+ healAmount);
		}	
		ApplicationData.eventQueue.add(()->playHealSFX());	
		ApplicationData.animate.addHpAnimation(attacker);
		ApplicationData.animate.addTextAnimation(getAllied(attacker) + attacker.getName() + " restored its HP!");
		
	}
	
	public void getButtonImages(int type) {
		 
		 setButton(new ImageIcon("res/buttons/"+Pokemon.getTypeName(type) + ".png").getImage());
         buttonPressed = new ImageIcon("res/buttons/"+Pokemon.getTypeName(type) + "Pressed.png").getImage();
	    
	}

}
