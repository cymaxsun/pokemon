package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import pokemon.Brian;
import pokemon.Ethan;

public class Main {
	
	public static JFrame window;
	
	
	public static void main(String[] args) {
		
		ApplicationData.playerPokemon = new Brian();
		ApplicationData.enemyPokemon = new Ethan();
		ApplicationData.enemyPokemon.setAllied(false);
		
		
		PokemonSelect ps = new PokemonSelect();
		
		ApplicationData.window = new JFrame();
		ApplicationData.window.setBounds(0, 0, ApplicationData.frameWidth,  ApplicationData.frameHeight);
		ApplicationData.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ApplicationData.window.add(ps);
       
        //window.setResizable(false); 	
		ApplicationData.window.setTitle("Pokemon");
		ApplicationData.window.setVisible(true);
        //tp.startAnimtion();
		
       
        //window.add(ApplicationData.battleFrame);
        //window.pack();

	}
}
