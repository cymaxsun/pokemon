package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;
import java.awt.GridLayout;

public class PokemonScrollPane extends JScrollPane{
	
	JPanel viewport;
	JLabel label;
	PokemonPreviewPanel[] pokemon;
	
	
	public PokemonScrollPane() {
		viewport = new JPanel();
		viewport.setLayout(new GridLayout(ApplicationData.numOfPokemon, 0, 0, 0));
		pokemon = new PokemonPreviewPanel[ApplicationData.numOfPokemon];
		viewport.setBackground(Color.red);
		for (PokemonPreviewPanel p : pokemon ) {
			p = new PokemonPreviewPanel(this);
			viewport.add(p);
		}
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
