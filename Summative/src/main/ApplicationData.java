package main;

import java.awt.Font;
import java.io.InputStream;

import javax.swing.JFrame;

import pokemon.Pokemon;

public class ApplicationData {
	static BattleFrame battleFrame;
	static JFrame window;
	static Sound soundtrack = new Sound();
	static Sound sfx = new Sound();
	static Pokemon playerPokemon;
	static Pokemon enemyPokemon;
	public static AnimationHandler animate;
	public static boolean gameOver;
	final static int frameWidth = 1336;
	final static int frameHeight = 768;
	static Font font;

    static {
        try {
            InputStream inputStream = ApplicationData.class.getResourceAsStream("/font/pokemon.ttf");
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
}
