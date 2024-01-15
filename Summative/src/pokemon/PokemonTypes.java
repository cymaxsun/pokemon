package pokemon;

import java.awt.Color;

public interface PokemonTypes {
	public static final int NORMAL = 0;
	public static final int FIRE = 1;
	public static final int WATER = 2;
	public static final int ELECTRIC = 3;
	public static final int GRASS = 4;
	public static final int ICE = 5;
	public static final int FIGHTING = 6;
	public static final int POISON = 7;
	public static final int GROUND = 8;
	public static final int FLYING = 9;
	public static final int PSYCHIC = 10;
	public static final int BUG = 11;
	public static final int ROCK = 12;
	public static final int GHOST = 13;
	public static final int DRAGON = 14;
	public static final int DARK = 15;
	public static final int STEEL = 16;
	public static final int FAIRY = 17;

	public static Color getTypeColor(int type) {
		Color color;
		switch (type) {
		case NORMAL:
			color = Color.LIGHT_GRAY;
			break;
		case FIRE:
			color = Color.RED;
			break;
		case WATER:
			color = Color.BLUE;
			break;
		case ELECTRIC:
			color = Color.YELLOW;
			break;
		case GRASS:
			color = Color.GREEN;
			break;
		case ICE:
			color = new Color(173, 216, 230); // Light Blue
			break;
		case FIGHTING:
			color = Color.ORANGE;
			break;
		case POISON:
			color = new Color(138, 43, 226); // Purple/Violet
			break;
		case GROUND:
			color = new Color(205, 133, 63); // Brown
			break;
		case FLYING:
			color = Color.CYAN;
			break;
		case PSYCHIC:
			color = new Color(255, 20, 147); // Pink
			break;
		case BUG:
			color = new Color(107, 142, 35); // Olive Green
			break;
		case ROCK:
			color = new Color(128, 128, 128); // Gray
			break;
		case GHOST:
			color = new Color(128, 0, 128); // Dark Purple
			break;
		case DRAGON:
			color = new Color(255, 20, 147); // Another shade of Pink
			break;
		case DARK:
			color = Color.DARK_GRAY;
			break;
		case STEEL:
			color = new Color(192, 192, 192); // Silver
			break;
		case FAIRY:
			color = new Color(255, 182, 193); // Light Pink
			break;
		default:
			color = Color.WHITE; // Default color
		}
		return color;
	}

	public static String getTypeName(int type) {
		String[] typeNames = { "NORMAL", "FIRE", "WATER", "ELECTRIC", "GRASS", "ICE", "FIGHTING", "POISON", "GROUND",
				"FLYING", "PSYCHIC", "BUG", "ROCK", "GHOST", "DRAGON", "DARK", "STEEL", "FAIRY" };

		if (type >= 0 && type < typeNames.length) {
			return typeNames[type];
		} else {
			return "UNKNOWN";
		}
	}

	static final float[][] typeChart = {
	        //              Normal   Fire   Water  Electric Grass Ice   Fighting  Poison  Ground  Flying  Psychic Bug  Rock Ghost  Dragon Dark  Steel  Fairy
	        /* Normal */    { 1.0f,   1.0f,   1.0f,   1.0f,     1.0f,  1.0f,  2.0f,      1.0f,    1.0f,    1.0f,    1.0f,    1.0f, 0.5f, 0.0f,   1.0f,   1.0f,  0.5f,   1.0f },
	        /* Fire */      { 1.0f,   0.5f,   0.5f,   1.0f,     2.0f,  2.0f,  1.0f,      1.0f,    1.0f,    1.0f,    1.0f,    2.0f, 0.5f, 1.0f,   0.5f,   1.0f,  2.0f,   1.0f },
	        /* Water */     { 1.0f,   2.0f,   0.5f,   1.0f,     0.5f,  1.0f,  1.0f,      1.0f,    2.0f,    1.0f,    1.0f,    1.0f, 2.0f, 1.0f,   0.5f,   1.0f,  1.0f,   1.0f },
	        /* Electric */  { 1.0f,   1.0f,   2.0f,   0.5f,     0.5f,  1.0f,  1.0f,      1.0f,    0.0f,    2.0f,    1.0f,    1.0f, 1.0f, 1.0f,   0.5f,   1.0f,  1.0f,   1.0f },
	        /* Grass */     { 1.0f,   0.5f,   2.0f,   1.0f,     0.5f,  1.0f,  1.0f,      0.5f,    2.0f,    0.5f,    1.0f,    0.5f, 2.0f, 1.0f,   0.5f,   1.0f,  0.5f,   1.0f },
	        /* Ice */       { 1.0f,   0.5f,   0.5f,   1.0f,     2.0f,  0.5f,  1.0f,      1.0f,    2.0f,    2.0f,    1.0f,    1.0f, 1.0f, 1.0f,   2.0f,   1.0f,  0.5f,   1.0f },
	        /* Fighting */  { 2.0f,   1.0f,   1.0f,   1.0f,     1.0f,  2.0f,  1.0f,      0.5f,    1.0f,    0.5f,    0.5f,    0.5f, 2.0f, 0.0f,   1.0f,   2.0f,  2.0f,   0.5f },
	        /* Poison */    { 1.0f,   1.0f,   1.0f,   1.0f,     2.0f,  1.0f,  1.0f,      0.5f,    0.5f,    1.0f,    1.0f,    1.0f, 0.5f, 0.5f,   1.0f,   1.0f,  0.0f,   2.0f },
	        /* Ground */    { 1.0f,   2.0f,   1.0f,   2.0f,     0.5f,  1.0f,  1.0f,      2.0f,    1.0f,    0.0f,    1.0f,    0.5f, 2.0f, 1.0f,   1.0f,   1.0f,  2.0f,   1.0f },
	        /* Flying */    { 1.0f,   1.0f,   1.0f,   0.5f,     2.0f,  1.0f,  2.0f,      1.0f,    1.0f,    1.0f,    1.0f,    2.0f, 0.5f, 1.0f,   1.0f,   1.0f,  0.5f,   1.0f },
	        /* Psychic */   { 1.0f,   1.0f,   1.0f,   1.0f,     1.0f,  1.0f,  2.0f,      2.0f,    1.0f,    1.0f,    0.5f,    1.0f, 1.0f, 1.0f,   1.0f,   0.0f,  0.5f,   1.0f },
	        /* Bug */       { 1.0f,   0.5f,   1.0f,   1.0f,     2.0f,  1.0f,  0.5f,      0.5f,    1.0f,    0.5f,    2.0f,    1.0f, 1.0f, 0.5f,   1.0f,   2.0f,  0.5f,   0.5f },
	        /* Rock */      { 1.0f,   2.0f,   1.0f,   1.0f,     1.0f,  2.0f,  0.5f,      1.0f,    0.5f,    2.0f,    1.0f,    2.0f, 1.0f, 1.0f,   1.0f,   1.0f,  0.5f,   1.0f },
	        /* Ghost */     { 0.0f,   1.0f,   1.0f,   1.0f,     1.0f,  1.0f,  0.0f,      1.0f,    1.0f,    1.0f,    2.0f,    1.0f, 1.0f, 2.0f,   1.0f,   0.5f,  1.0f,   1.0f },
	        /* Dragon */    { 1.0f,   1.0f,   1.0f,   1.0f,     1.0f,  1.0f,  1.0f,      1.0f,    1.0f,    1.0f,    1.0f,    1.0f, 1.0f, 1.0f,   2.0f,   1.0f,  0.5f,   0.0f },
	        /* Dark */      { 1.0f,   1.0f,   1.0f,   1.0f,     1.0f,  1.0f,  0.5f,      1.0f,    1.0f,    1.0f,    2.0f,    1.0f, 1.0f, 2.0f,   1.0f,   0.5f,  1.0f,   0.5f },
	        /* Steel */     { 1.0f,   0.5f,   0.5f,   0.5f,     1.0f,  2.0f,  0.0f,      0.0f,    2.0f,    1.0f,    1.0f,    0.5f, 2.0f, 1.0f,   1.0f,   1.0f,  0.5f,   2.0f },
	        /* Fairy */     { 1.0f,   0.5f,   1.0f,   1.0f,     1.0f,  1.0f,  2.0f,      0.5f,    1.0f,    1.0f,    1.0f,    1.0f, 1.0f, 1.0f,   2.0f,   2.0f,  0.5f,   1.0f },
	    };
}