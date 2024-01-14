package main;

import java.awt.Font;
import java.io.InputStream;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pokemon.Pokemon;

public class ApplicationData {
	
	//frame + panels
	static BattlePanel battlePanel;
	static JFrame window;
	static TitlePanel titlePanel;
	static SettingsPanel settings;
	static PokemonSelectPanel charSelect;
	
	//event queue for animations
	public static LinkedList<Runnable> eventQueue = new LinkedList<Runnable>();
	
	//sound settings
	public static Sound soundtrack = new Sound();
	final static int numOfTracks = 6;
	static int track;
	public static Sound sfx = new Sound();
	static float SFXVolume = 6f;
	static float soundtrackVolume = -10f;
	
	//pokemon
	static Pokemon playerPokemon;
	static Pokemon enemyPokemon;
	
	//game helper objects
	public static AnimationHandler animate;
	public static boolean gameOver;
	
	//global constants
	final static int frameWidth = 1336;
	final static int frameHeight = 768;
	static Font font;
	final static int numOfPokemon = 9;
   
	static {
		try {
			InputStream inputStream = ApplicationData.class.getResourceAsStream("/font/PokemonFont.ttf");
			if (inputStream != null) {
				font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
				inputStream.close(); // Close the stream after use
			} else {
				System.err.println("Font file not found!");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Handle this exception appropriately for your application
		}
	}
	
	public static void switchPanel(JPanel start, JPanel end) {
		ApplicationData.sfx.playFile(1);
    	start.setVisible(false);
       	ApplicationData.window.add(end);
       	end.setVisible(true);
    	end.requestFocusInWindow();
    	ApplicationData.window.remove(start);
	}
	
    // Type effectiveness values: 0.5 = not very effective, 1 = normal effectiveness, 2 = super effective
	public static final float[][] typeChart = {
			// NORMAL FIRE WATER GRASS FLYING FIGHTING POISON ELECTRIC GROUND ROCK PSYCHIC ICE BUG GHOST STEEL DRAGON DARK FAIRY
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
