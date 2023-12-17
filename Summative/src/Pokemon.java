
public abstract class Pokemon {

    private String name;
    private String type;
    private PokemonMove move1;
    private PokemonMove move2;
    private PokemonMove move3;
    private PokemonMove move4;
    private int lvl;
    private int maxHp;
    private int def;
    private int currentHp;
    private boolean allied;

    public Pokemon(String name, String type, int lvl, boolean allied, int maxHp, PokemonMove move1, PokemonMove move2, PokemonMove move3, PokemonMove move4) {
        if (allied) {
        	this.name = name;
        } else {
        	this.name = "Enemy "+ name;
        }
    	
    	
        this.lvl = lvl;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.type = type;
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.move4 = move4;
        this.allied = allied;
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
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }
}

