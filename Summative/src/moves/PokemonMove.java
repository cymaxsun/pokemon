package moves;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

import main.ApplicationData;
import pokemon.Pokemon;

public class PokemonMove {
	
	String name;
	String type;
	Color typeColor;
	Image button;
	Image buttonPressed;
	int baseAtk;
	int currentAtk;
	int charges;
	int maxCharges;
	int acc;
	
	public PokemonMove(String name, String type, int atk, int charges, int acc) {
		this.name = name;
		this.type = type;
		this.baseAtk = atk;
		this.currentAtk = atk;
		this.charges = charges;
		this.maxCharges = charges;
		this.acc = acc;

		
		setButton(new ImageIcon("res/buttons/"+type+".png").getImage());
		buttonPressed = new ImageIcon("res/buttons/"+type+"Pressed.png").getImage();
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getters and setters for 'type'
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getters and setters for 'typeColor'
    public Color getTypeColor() {
        return typeColor;
    }

    public void setTypeColor(Color typeColor) {
        this.typeColor = typeColor;
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

}
