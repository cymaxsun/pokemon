package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import pokemon.Brian;

public class Main {
	
	public static JFrame window;
	
	
	public static void main(String[] args) {
		
		ApplicationData.playerPokemon = new Brian();
		ApplicationData.enemyPokemon = new Brian();
		ApplicationData.enemyPokemon.setAllied(false);
		
		//window = new JFrame();
        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setResizable(false);
        //window.setTitle("2D Adventure");

        ApplicationData.battleFrame = new BattleFrame();
        ApplicationData.battleFrame.setVisible(true);
        //window.add(ApplicationData.battleFrame);
        //window.pack();

	}
}
