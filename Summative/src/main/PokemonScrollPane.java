package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PokemonScrollPane extends JScrollPane{
	
	JPanel viewport;
	JLabel label;
	
	
	
	public PokemonScrollPane() {
		viewport = new JPanel();
		viewport.setBackground(Color.red);
		label = new JLabel("pokemon");
			
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		setColumnHeaderView(label);	
		setViewportView(viewport);
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
	}
}
