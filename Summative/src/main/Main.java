package main;

import javax.swing.JFrame;

import pokemon.Brian;
import pokemon.Ethan;
import pokemon.Gengar;
import pokemon.Imogen;

public class Main {

	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1.0");
		ApplicationData.titlePanel = new TitlePanel();
		ApplicationData.titlePanel.requestFocusInWindow();

		ApplicationData.charSelect = new PokemonSelectPanel();
		ApplicationData.settings = new SettingsPanel();
		ApplicationData.endPanel = new EndPanel();
		ApplicationData.soundtrack.setVolume(ApplicationData.soundtrackVolume);
		ApplicationData.sfx.setVolume(ApplicationData.SFXVolume);
		
		ApplicationData.initGame();
		

		ApplicationData.window = new JFrame();
		ApplicationData.window.setResizable(false);
		ApplicationData.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ApplicationData.window.add(ApplicationData.titlePanel);
		ApplicationData.window.setTitle("Pokemon");
		ApplicationData.window.pack();
		ApplicationData.window.setVisible(true);

	}
}
