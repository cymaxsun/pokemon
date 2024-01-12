package main;

import java.awt.Font;
import java.io.InputStream;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import pokemon.Pokemon;

public class ApplicationData {
	static BattlePanel battlePanel;
	static JFrame window;
	static TitlePanel titlePanel;
	static SettingsPanel settings;
	static PokemonSelectPanel charSelect;
	public static LinkedList<Runnable> eventQueue = new LinkedList<Runnable>();
	public static Sound soundtrack = new Sound();
	public static Sound sfx = new Sound();
	static float SFXVolume = 6f;
	static float soundtrackVolume = -10f;
	static Pokemon playerPokemon;
	static Pokemon enemyPokemon;
	public static AnimationHandler animate;
	public static boolean gameOver;
	final static int frameWidth = 1336;
	final static int frameHeight = 768;
	static Font font;
	final static int numOfPokemon = 9;
   
	static {
		try {
			InputStream inputStream = ApplicationData.class.getResourceAsStream("/font/PokemonTest2.ttf");
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
}
