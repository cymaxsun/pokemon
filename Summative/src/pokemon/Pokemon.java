package pokemon;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import main.SpritePanel;
import main.StatusPanel;
import moves.PokemonMove;

public class Pokemon {

    private String name;
    private String type;
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
    private Map<Status, Integer> statuses;
    private StatusPanel statusPanel;
    private SpritePanel spritePanel;
    
    
    
    public Pokemon(String name, String type, int lvl, int maxHp, int baseAtk, int baseDef, PokemonMove move1, PokemonMove move2, PokemonMove move3, PokemonMove move4) {
        this.name = name;
        this.lvl = lvl;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.baseAtk = baseAtk;
        this.baseDef = baseDef;
        this.currentDef = baseDef;
        this.type = type;
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.move4 = move4;
        this.setAllied(true);
        this.statuses = new HashMap<>();
        setStatusPanel(new StatusPanel(this));
        setSpritePanel(new SpritePanel(this));
    }

    private void reduceStatusDurations() {
        Set<Status> statusesToRemove = new HashSet<>();
        for (Map.Entry<Status, Integer> entry : statuses.entrySet()) {
            Status status = entry.getKey();
            int remainingDuration = entry.getValue();
            if (remainingDuration > 0) {
            	statuses.put(status, remainingDuration - 1);
            } else {
                statusesToRemove.add(status);
            }
        }
        for (Status statusToRemove : statusesToRemove) {
            removeStatus(statusToRemove);
        }
    }
    
    public void updateStatuses() {
    	reduceStatusDurations();
    	
    }

    
    // Getter and setter methods for each field

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

	public Map<Status, Integer> getStatuses() {
		return statuses;
	}

	public void addStatus(Status status, int duration) {
		for (Map.Entry<Status, Integer> entry : statuses.entrySet()) {
			if (entry.getKey().equals(status)) {
				return;
			}
		}
		statuses.put(status, duration);
	}
	public void removeStatus(Status status) {
		statuses.remove(status);
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
    
}

