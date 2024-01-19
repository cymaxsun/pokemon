package pokemon;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.ApplicationData;
import main.SpritePanel;
import main.StatusPanel;
import moves.PokemonMove;

public class Pokemon{

	private String name;
	private int type1;
	private int type2;
	private PokemonMove move1;
	private PokemonMove move2;
	private PokemonMove move3;
	private PokemonMove move4;
	private int lvl;
	private int maxHp;
	private int currentHp;
	private int baseDef;
	private int currentDef;
	private int baseAtk;
	private int currentAtk;
	private int atkStage;
	private int defStage;
	private boolean isAllied;
	boolean lowHP;
	private StatusPanel statusPanel;
	private SpritePanel spritePanel;
	public Image image;
	

	public Pokemon(String name, int type1, int type2, int lvl, int maxHp, int baseAtk, int baseDef, PokemonMove move1,
			PokemonMove move2, PokemonMove move3, PokemonMove move4) {
		this.name = name;
		this.lvl = lvl;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.baseAtk = baseAtk;
		this.currentAtk = baseAtk;
		this.baseDef = baseDef;
		this.currentDef = baseDef;
		this.type1 = type1;
		this.move1 = move1;
		this.move2 = move2;
		this.move3 = move3;
		this.move4 = move4;
		this.setAllied(true);
		setStatusPanel(new StatusPanel(this));
		lowHP = false;
		atkStage = 0;
		defStage = 0;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/pokemon/" + getName().toLowerCase() + ".png"));
			
			setSpritePanel(new SpritePanel(image));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Getter and setter methods for each field

	public Pokemon() {
		// TODO Auto-generated constructor stub
	}

	public Pokemon(String name, int type1, int lvl, int maxHp, int baseAtk, int baseDef) {
		this.name = name;
		this.lvl = lvl;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.baseAtk = baseAtk;
		this.currentAtk = baseAtk;
		this.baseDef = baseDef;
		this.currentDef = baseDef;
		setType1(type1);
		this.type2 = -1;
		this.setAllied(true);
		
		
		lowHP = false;
		atkStage = 0;
		defStage = 0;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/pokemon/" + getName().toLowerCase() + ".png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Pokemon(String name, int type1, int type2, int lvl, int maxHp, int baseAtk, int baseDef) {
		this.name = name;
		this.lvl = lvl;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.baseAtk = baseAtk;
		this.currentAtk = baseAtk;
		this.baseDef = baseDef;
		this.currentDef = baseDef;
		setType1(type1);
		setType2(type2);
		this.setAllied(true);
		
		
		lowHP = false;
		atkStage = 0;
		defStage = 0;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/pokemon/" + getName().toLowerCase() + ".png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType1() {
		return type1;
	}

	public void setType1(int type1) {
		this.type1 = type1;
		
	}

	public int getType2() {
		return type2;
	}

	public void setType2(int type2) {
		this.type2 = type2;
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
		if (def < 0) {
			this.currentDef = 0;
			return;
		}
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


	

	public String getTypeName() {
		String s;
		if (type2 != -1) {
			s = PokemonTypes.getTypeName(type1) + ", " + PokemonTypes.getTypeName(type2);
		} else {
			s = PokemonTypes.getTypeName(type1);
		}
		
		return s;
	}

	public void setLowHP(boolean lowHP) {
		this.lowHP = lowHP;
	}

	public void lowHPSFX() {
		if (lowHP) {
			ApplicationData.sfx.playFile(6);
			ApplicationData.sfx.loop();
		} else {
			ApplicationData.sfx.stop();
		}
	}

	public int getAtkStage() {
		return atkStage;
	}

	public void setAtkStage(int atkStage) {

		if (atkStage <= 6 && atkStage >= -6) {
			int change = atkStage - this.atkStage;
			
			if (this.atkStage < atkStage) {
				
				ApplicationData.eventQueue.add(()->ApplicationData.sfx.playSFX(8));
				if (change > 1) {
					ApplicationData.animate.addTextAnimation(PokemonMove.getAllied(this) + this.getName() + "'s attack sharply rose!");

				} else {
					ApplicationData.animate.addTextAnimation(PokemonMove.getAllied(this) + this.getName() + "'s attack rose!");

				}
				
			} else {
				ApplicationData.eventQueue.add(()->ApplicationData.sfx.playSFX(3));
				if (change < -1) {
					ApplicationData.animate.addTextAnimation(PokemonMove.getAllied(this) + this.getName() + "'s attack sharply fell!");
				} else {
					ApplicationData.animate.addTextAnimation(PokemonMove.getAllied(this) + this.getName() + "'s attack fell!");
				}
				
				
			}
			
			this.atkStage = atkStage;
		} else if (atkStage > 6) {
			ApplicationData.animate.addTextAnimation(PokemonMove.getAllied(this) + this.getName() + "'s attack wont go any higher!");
			this.atkStage = 6;
		} else {
			ApplicationData.animate.addTextAnimation(PokemonMove.getAllied(this) + this.getName() + "'s attack wont go any lower!");
			this.atkStage = -6;
		}
		
		if (atkStage < 0) {
			setAtk( baseAtk  *  2 / (2 - atkStage));
		} else {
			setAtk(baseAtk + (baseAtk * atkStage / 2));
		}

	}

	public int getDefStage() {
		return defStage;
	}

	public void setDefStage(int defStage) {
		if (defStage <= 6 && defStage >= -6) {
			int change = defStage - this.defStage;
			
			System.out.println(this.defStage + " - " + defStage + " = " + change);
			if (this.defStage < defStage) {
				ApplicationData.eventQueue.add(()->ApplicationData.sfx.playSFX(8));
				if (change > 1) {
					ApplicationData.animate.addTextAnimation(this.getName() + "'s defense sharply rose!");

				} else {
					ApplicationData.animate.addTextAnimation(this.getName() + "'s defense rose!");

				}
			
			} else {
				ApplicationData.eventQueue.add(()->ApplicationData.sfx.playSFX(3));
				if (change < -1) {
					ApplicationData.animate.addTextAnimation(this.getName() + "'s defense sharply fell!");
				} else {
					ApplicationData.animate.addTextAnimation(this.getName() + "'s defense fell!");
				}
				
			}
			this.defStage = defStage;
			
		} else if (defStage > 6) {
			ApplicationData.animate.addTextAnimation(this.getName() + "'s defense wont go any higher!");
			this.defStage = 6;
		} else {
			ApplicationData.animate.addTextAnimation(this.getName() + "'s defense wont go any lower!");
			this.defStage = -6;
		}
		if (defStage < 0) {
			setDef(baseDef  * 2 / (2 - defStage) );
		} else {
			setDef(baseDef + (baseDef * defStage / 2));
		}
	}

	public int getAtk() {
		return currentAtk;
	}

	public void setAtk(int currentAtk) {
		if (currentAtk < 0) {
			this.currentAtk = 0;
		}
		this.currentAtk = currentAtk;
	}

	public static Pokemon createPokemon(int i) {
		Pokemon p;
		switch (i){
		case 0:
			p = new Brian();
			break;
		case 1:
			p = new Ethan();
			break;
		case 2:
			p = new Imogen();
			break;
		case 3:
			p = new Gengar();
			break;
		case 4:
			p = new Tommy();
			break;
		case 5: 
			p = new Shivam();
			break;
		case 6: 
			p = new Imposter();
			break;
		default:
			p = createPokemon(ApplicationData.random.nextInt(ApplicationData.numOfPokemon));
			break;	
		}
		return p;
	}
	
	
	public void initForBattle() {
		setSpritePanel(new SpritePanel(image));
		setStatusPanel(new StatusPanel(this));
	}

}
