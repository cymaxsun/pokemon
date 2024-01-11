package pokemon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import main.ApplicationData;
import main.SpritePanel;
import main.StatusPanel;
import moves.PokemonMove;

public class Pokemon {

	private String name;
	private int type;
	private String typeName;
	private PokemonMove move1;
	private PokemonMove move2;
	private PokemonMove move3;
	private PokemonMove move4;
	private int lvl;
	private int maxHp;
	private int currentDef;
	private int baseDef;
	private int baseAtk;
	private int currentHp;
	private boolean isAllied;
	boolean lowHP;
	private StatusPanel statusPanel;
	private SpritePanel spritePanel;

	public Pokemon(String name, int type, int lvl, int maxHp, int baseAtk, int baseDef, PokemonMove move1,
			PokemonMove move2, PokemonMove move3, PokemonMove move4) {
		this.name = name;
		this.lvl = lvl;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.baseAtk = baseAtk;
		this.baseDef = baseDef;
		this.currentDef = baseDef;
		this.type = type;
		this.typeName = getTypeName(type);
		this.move1 = move1;
		this.move2 = move2;
		this.move3 = move3;
		this.move4 = move4;
		this.setAllied(true);
		setStatusPanel(new StatusPanel(this));
		setSpritePanel(new SpritePanel(this));
		lowHP = false;
	}

	// Getter and setter methods for each field

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;

	}

	public PokemonMove getMove1() {
		return move1;
	}

	public void setMove1(PokemonMove move1) {
		this.move1 = move1;
	}

	public PokemonMove getMove2() {
		return move2;
	}

	public void setMove2(PokemonMove move2) {
		this.move2 = move2;
	}

	public PokemonMove getMove3() {
		return move3;
	}

	public void setMove3(PokemonMove move3) {
		this.move3 = move3;
	}

	public PokemonMove getMove4() {
		return move4;
	}

	public void setMove4(PokemonMove move4) {
		this.move4 = move4;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getDef() {
		return currentDef;
	}

	public void setDef(int def) {
		this.currentDef = def;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	public boolean isAllied() {
		return isAllied;
	}

	public void setAllied(boolean isAllied) {
		this.isAllied = isAllied;
	}

	public int getBaseDef() {
		return baseDef;
	}

	public void setBaseDef(int baseDef) {
		this.baseDef = baseDef;
	}

	public StatusPanel getStatusPanel() {
		return statusPanel;
	}

	public void setStatusPanel(StatusPanel statusPanel) {
		this.statusPanel = statusPanel;
	}

	public int getBaseAtk() {
		return baseAtk;
	}

	public void setBaseAtk(int baseAtk) {
		this.baseAtk = baseAtk;
	}

	public SpritePanel getSpritePanel() {
		return spritePanel;
	}

	public void setSpritePanel(SpritePanel spritePanel) {
		this.spritePanel = spritePanel;
	}

	public static Color getTypeColor(int type) {
		Color color;
		switch (type) {
		case 1: // Normal
			color = Color.LIGHT_GRAY;
			break;
		case 2: // Fire
			color = Color.RED;
			break;
		case 3: // Water
			color = Color.BLUE;
			break;
		case 4: // Grass
			color = Color.GREEN;
			break;
		case 5: // Flying
			color = Color.CYAN;
			break;
		case 6: // Fighting
			color = Color.ORANGE;
			break;
		case 7: // Poison
			color = new Color(138, 43, 226); // Purple/Violet
			break;
		case 8: // Electric
			color = Color.YELLOW;
			break;
		case 9: // Ground
			color = new Color(205, 133, 63); // Brown
			break;
		case 10: // Rock
			color = new Color(128, 128, 128); // Gray
			break;
		case 11: // Psychic
			color = new Color(255, 20, 147); // Pink
			break;
		case 12: // Ice
			color = new Color(173, 216, 230); // Light Blue
			break;
		case 13: // Bug
			color = new Color(107, 142, 35); // Olive Green
			break;
		case 14: // Ghost
			color = new Color(128, 0, 128); // Dark Purple
			break;
		case 15: // Steel
			color = new Color(192, 192, 192); // Silver
			break;
		case 16: // Dragon
			color = new Color(255, 20, 147); // Another shade of Pink
			break;
		case 17: // Dark
			color = Color.DARK_GRAY;
			break;
		case 18: // Fairy
			color = new Color(255, 182, 193); // Light Pink
			break;
		default:
			color = Color.WHITE; // Default color
			break;
		}
		return color;
	}

	public static String getTypeName(int type) {
		String typeName;
		switch (type) {
		case 1:
			typeName = "NORMAL";
			break;
		case 2:
			typeName = "FIRE";
			break;
		case 3:
			typeName = "WATER";
			break;
		case 4:
			typeName = "GRASS";
			break;
		case 5:
			typeName = "FLYING";
			break;
		case 6:
			typeName = "FIGHTING";
			break;
		case 7:
			typeName = "POISON";
			break;
		case 8:
			typeName = "ELECTRIC";
			break;
		case 9:
			typeName = "GROUND";
			break;
		case 10:
			typeName = "ROCK";
			break;
		case 11:
			typeName = "PSYCHIC";
			break;
		case 12:
			typeName = "ICE";
			break;
		case 13:
			typeName = "BUG";
			break;
		case 14:
			typeName = "GHOST";
			break;
		case 15:
			typeName = "STEEL";
			break;
		case 16:
			typeName = "DRAGON";
			break;
		case 17:
			typeName = "DARK";
			break;
		case 18:
			typeName = "FAIRY";
			break;
		default:
			typeName = "UNKNOWN";
			break;
		}
		return typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setLowHP(boolean lowHP) {
		this.lowHP = lowHP;
	}
	
	public void lowHPSFX() {
		if (lowHP) {
			ApplicationData.sfx.playFile(6, 0.8f);
			ApplicationData.sfx.loop();
		} else {
			ApplicationData.sfx.stop();
		}
	}
}
