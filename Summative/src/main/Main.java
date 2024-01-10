package main;

import javax.swing.JFrame;

import pokemon.Brian;
import pokemon.Ethan;

public class Main {
	
	public static JFrame window;
	
	
	public static void main(String[] args) {
		
		ApplicationData.enemyPokemon = new Brian();
		ApplicationData.enemyPokemon.setAllied(false);
		
		
		ApplicationData.titlePanel = new TitlePanel();
		
		ApplicationData.window = new JFrame();
		ApplicationData.window.setBounds(0, 0, ApplicationData.frameWidth,  ApplicationData.frameHeight);
		ApplicationData.window.setResizable(false);
		ApplicationData.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ApplicationData.window.add(ApplicationData.titlePanel);
		ApplicationData.window.setTitle("Pokemon");
		ApplicationData.window.setVisible(true);
		
		

	}
}
