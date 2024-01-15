package main;

import javax.swing.JFrame;

import pokemon.Brian;
import pokemon.Ethan;
import pokemon.Gengar;
import pokemon.Imogen;

public class Main {

	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1.0");
		
		int enemyPokemon = ApplicationData.random.nextInt(ApplicationData.numOfPokemon);

		switch (enemyPokemon) {
		case 0:
		case 4:
		case 8:
			ApplicationData.enemyPokemon = new Brian();
			break;
		case 1:
		case 6:
			ApplicationData.enemyPokemon = new Ethan();
			break;
		case 2:
		case 3:
		case 7:
			ApplicationData.enemyPokemon = new Imogen();
			break;
		case 5:
			ApplicationData.enemyPokemon = new Gengar();
			break;
		default:
			// Handle the case when enemyPokemon is out of bounds of the array
			System.out.println("Invalid enemyPokemon index!");
			return; // or break, depending on your context
		}
		ApplicationData.enemyPokemon = new Ethan();	
		System.out.println(ApplicationData.enemyPokemon.getName() + enemyPokemon);
		ApplicationData.enemyPokemon.initForBattle();
		ApplicationData.enemyPokemon.setAllied(false);
		ApplicationData.soundtrack.setVolume(ApplicationData.soundtrackVolume);
		ApplicationData.sfx.setVolume(ApplicationData.SFXVolume);

		ApplicationData.titlePanel = new TitlePanel();
		ApplicationData.titlePanel.requestFocusInWindow();

		ApplicationData.charSelect = new PokemonSelectPanel();
		ApplicationData.settings = new SettingsPanel();
		ApplicationData.endPanel = new EndPanel();

		ApplicationData.window = new JFrame();
		ApplicationData.window.setResizable(false);
		ApplicationData.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ApplicationData.window.add(ApplicationData.titlePanel);
		ApplicationData.window.setTitle("Pokemon");
		ApplicationData.window.pack();
		ApplicationData.window.setVisible(true);

	}
}
