package main;

import javax.swing.JFrame;

import pokemon.Gengar;

public class Main {
	
	public static JFrame window;
	
	
	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1.0");
		float x = 0.5f;
		int y = 10;
		int z = (int) (x * y);
		System.out.println(z);
		ApplicationData.enemyPokemon = new Gengar();
		ApplicationData.enemyPokemon.setAllied(false);
		ApplicationData.soundtrack.setVolume(ApplicationData.soundtrackVolume);
		ApplicationData.sfx.setVolume(ApplicationData.SFXVolume);
		
		ApplicationData.titlePanel = new TitlePanel();
		ApplicationData.titlePanel.requestFocusInWindow();

		ApplicationData.charSelect = new PokemonSelectPanel();
		ApplicationData.settings = new SettingsPanel();
		
		
		ApplicationData.window = new JFrame();
		ApplicationData.window.setResizable(false);
		ApplicationData.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ApplicationData.window.add(ApplicationData.titlePanel);
		ApplicationData.window.setTitle("Pokemon");
		ApplicationData.window.pack();
		ApplicationData.window.setVisible(true);		
		

	}
}
