package main;

import java.awt.Font;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pokemon.Brian;
import pokemon.Ethan;
import pokemon.Gengar;
import pokemon.Imogen;
import pokemon.Pokemon;

public class ApplicationData {
	
	//frame + panels
	static BattlePanel battlePanel;
	static JFrame window;
	static TitlePanel titlePanel;
	static SettingsPanel settings;
	static PokemonSelectPanel charSelect;
	static EndPanel endPanel;
	
	//event queue for animations
	public static LinkedList<Runnable> eventQueue = new LinkedList<Runnable>();
	
	//sound settings
	public static Sound soundtrack = new Sound();
	final static int numOfTracks = 7;
	static int track;
	public static Sound sfx = new Sound();
	static float SFXVolume = 6f;
	static float soundtrackVolume = -10f;
	
	
	
	//game helper objects
	public static AnimationHandler animate;
	public static boolean gameOver;
	
	//global constants
	final static int frameWidth = 1336;
	final static int frameHeight = 768;
	static Font font;
	public final static Random random = new Random();
	
	
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
	
	//pokemon
	static Pokemon playerPokemon;
	static Pokemon enemyPokemon;
	public final static int numOfPokemon = 9;
	static int[] pokemonList = new int[numOfPokemon];

	
	
	
	public static void switchPanel(JPanel start, JPanel end) {
		sfx.playFile(1);
    	start.setVisible(false);
       	window.add(end);
       	end.setVisible(true);
    	end.requestFocusInWindow();
    	window.remove(start);
	}
	
	public static void resetGame() {
		enemyPokemon = Pokemon.createPokemon(ApplicationData.random.nextInt(ApplicationData.numOfPokemon));
		enemyPokemon.initForBattle();
		enemyPokemon.setAllied(false);
		charSelect.resetPanel();
		gameOver = false;
	}
    // Type effectiveness values: 0.5 = not very effective, 1 = normal effectiveness, 2 = super effective

}
