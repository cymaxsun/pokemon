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
	                color =Color.LIGHT_GRAY;
	            case FIRE:
	                color =Color.RED;
	            case WATER:
	                color =Color.BLUE;
	            case ELECTRIC:
	                color =Color.YELLOW;
	            case GRASS:
	                color =Color.GREEN;
	            case ICE:
	                color =new Color(173, 216, 230); // Light Blue
	            case FIGHTING:
	                color =Color.ORANGE;
	            case POISON:
	                color =new Color(138, 43, 226); // Purple/Violet
	            case GROUND:
	                color =new Color(205, 133, 63); // Brown
	            case FLYING:
	                color =Color.CYAN;
	            case PSYCHIC:
	                color =new Color(255, 20, 147); // Pink
	            case BUG:
	                color =new Color(107, 142, 35); // Olive Green
	            case ROCK:
	                color =new Color(128, 128, 128); // Gray
	            case GHOST:
	                color =new Color(128, 0, 128); // Dark Purple
	            case DRAGON:
	                color =new Color(255, 20, 147); // Another shade of Pink
	            case DARK:
	                color =Color.DARK_GRAY;
	            case STEEL:
	                color =new Color(192, 192, 192); // Silver
	            case FAIRY:
	                color =new Color(255, 182, 193); // Light Pink
	            default:
	                color =Color.WHITE; // Default color
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

	public static final float[][] typeChart = {
			// NORMAL FIRE WATER GRASS FLYING FIGHTING POISON ELECTRIC GROUND ROCK PSYCHIC
			// ICE BUG GHOST STEEL DRAGON DARK FAIRY
			/* NORMAL */ { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.5f, 1, 1, 1, 0, 0.5f, 1, 1, 1 },
			/* FIRE */ { 1, 0.5f, 0.5f, 2, 1, 1, 1, 1, 1, 2, 1, 0.5f, 2, 1, 0.5f, 1, 1, 1 },
			/* WATER */ { 1, 2, 0.5f, 0.5f, 1, 1, 1, 1, 0.5f, 2, 1, 1, 0.5f, 1, 1, 1, 1, 1 },
			/* GRASS */ { 1, 0.5f, 2, 0.5f, 1, 1, 0.5f, 1, 2, 0.5f, 1, 0.5f, 2, 1, 0.5f, 1, 1, 1 },
			/* FLYING */ { 1, 1, 1, 2, 1, 0.5f, 1, 2, 1, 2, 1, 1, 1, 1, 0.5f, 1, 1, 1 },
			/* FIGHTING */ { 2, 1, 1, 1, 2, 1, 0, 1, 1, 0.5f, 2, 2, 0.5f, 0, 2, 1, 0.5f, 0.5f },
			/* POISON */ { 1, 1, 1, 2, 1, 1, 0.5f, 1, 0.5f, 0.5f, 1, 0.5f, 2, 0.5f, 0, 1, 1, 2 },
			/* ELECTRIC */ { 1, 1, 2, 0.5f, 0, 1, 1, 0.5f, 2, 1, 1, 1, 1, 1, 0.5f, 1, 1, 1 },
			/* GROUND */ { 1, 1, 1, 2, 1, 1, 2, 0, 1, 2, 1, 2, 0.5f, 1, 2, 1, 1, 1 },
			/* ROCK */ { 1, 2, 1, 1, 2, 0.5f, 1, 1, 0.5f, 1, 1, 2, 1, 1, 0.5f, 1, 1, 1 },
			/* PSYCHIC */ { 1, 1, 1, 1, 1, 0.5f, 2, 1, 1, 1, 0, 1, 1, 2, 0.5f, 1, 0, 1 },
			/* ICE */ { 1, 2, 0.5f, 1, 2, 1, 1, 1, 2, 2, 1, 0.5f, 1, 1, 0.5f, 1, 1, 1 },
			/* BUG */ { 1, 0.5f, 1, 0.5f, 2, 0.5f, 0.5f, 1, 0.5f, 2, 1, 1, 1, 0.5f, 0.5f, 1, 2, 0.5f },
			/* GHOST */ { 0, 1, 1, 1, 1, 0, 0.5f, 1, 1, 1, 1, 1, 1, 2, 0.5f, 1, 2, 1 },
			/* STEEL */ { 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 2, 0, 1, 2, 2, 1, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f },
			/* DRAGON */ { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0.5f, 2, 1, 0 },
			/* DARK */ { 1, 1, 1, 1, 1, 0.5f, 1, 1, 1, 1, 0, 1, 1, 2, 0.5f, 1, 0.5f, 2 },
			/* FAIRY */ { 1, 0.5f, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0.5f, 1, 1 } };
}
