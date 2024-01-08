package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import pokemon.Pokemon;

public class PokemonPreviewPanel extends JButton{
	
	Image image; 
	Pokemon p;
	JScrollPane scrollPane;
	
	public PokemonPreviewPanel(JScrollPane sp) {
		super();
		this.scrollPane = sp;

		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		super.paintComponent(g2);
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
	
	}
}
