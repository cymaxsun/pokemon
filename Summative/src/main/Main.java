package main;

import javax.swing.JFrame;

import pokemon.Brian;
import pokemon.Ethan;

public class Main {
	
	public static JFrame window;
	
	
	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1.0");
		ApplicationData.enemyPokemon = new Brian();
		ApplicationData.enemyPokemon.setAllied(false);
		ApplicationData.soundtrack.setVolume(0.8f);
		
		ApplicationData.titlePanel = new TitlePanel();
		ApplicationData.titlePanel.requestFocusInWindow();

		ApplicationData.charSelect = new PokemonSelectPanel();
		
		
		ApplicationData.window = new JFrame();
		ApplicationData.window.setResizable(false);
		ApplicationData.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ApplicationData.window.add(ApplicationData.titlePanel);
		ApplicationData.window.setTitle("Pokemon");
		ApplicationData.window.pack();
		ApplicationData.window.setVisible(true);		
		

	}
}
