package main;

import java.awt.EventQueue;

import pokemon.Brian;

public class Main {
	public static void main(String[] args) {
		
		ApplicationData.playerPokemon = new Brian();
		ApplicationData.enemyPokemon = new Brian();
		ApplicationData.enemyPokemon.setAllied(false);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationData.battleFrame = new BattleFrame();
					ApplicationData.battleFrame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
