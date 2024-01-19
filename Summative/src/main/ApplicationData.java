package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pokemon.Imposter;
import pokemon.Pokemon;

public class ApplicationData {
	
	//frame + panels
	public static BattlePanel battlePanel;
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
	public static boolean gameOver = false;
	
	//global constants
	final static int frameWidth = 1336;
	final static int frameHeight = 768;
	final static Dimension frameDimensions = new Dimension (frameWidth,frameHeight);
	static Font font;
	public final static Random random = new Random();
	public static Image logo;
	static {
		try {
			InputStream inputStream = ApplicationData.class.getResourceAsStream("/font/PokemonFont.ttf");
			font = Font.createFont(Font.TRUETYPE_FONT, inputStream);		
			inputStream = ApplicationData.class.getResourceAsStream("/assets/title.png");
			
			logo = ImageIO.read(inputStream).getScaledInstance(ApplicationData.frameWidth / 2, -1, Image.SCALE_SMOOTH);
			inputStream.close(); // Close the stream after use
		} catch (Exception e) {
			System.err.println("file not found!");
			e.printStackTrace(); // Handle this exception appropriately for your application
		}
	}
	
	//pokemon
	static Pokemon playerPokemon;
	static Pokemon enemyPokemon;
	public final static int numOfPokemon = 7;

	
	
	
	public static void switchPanel(JPanel start, JPanel end) {
		
    	start.setVisible(false);
       	window.add(end);
       	end.setVisible(true);
    	end.requestFocusInWindow();
    	window.remove(start);
	}
	
	public static void resetGame() {
		soundtrack.stop();
		initGame();
		charSelect.resetPanel();
		gameOver = false;
	}
	
	public static void initGame() {
		enemyPokemon = Pokemon.createPokemon(random.nextInt(numOfPokemon));
		enemyPokemon.initForBattle();
		enemyPokemon.setAllied(false);
		track = random.nextInt(numOfTracks);
		soundtrack.playTrack(track);

	}
	
	public static void addToQueue(Runnable action) {
		Runnable method = new Runnable() {
			@Override
			public void run(){
				action.run();
				ApplicationData.eventQueue.pop().run();
			}
		};
		ApplicationData.eventQueue.add(method);
	}

}
