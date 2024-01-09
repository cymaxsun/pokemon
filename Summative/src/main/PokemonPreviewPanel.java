package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import pokemon.Pokemon;

public class PokemonPreviewPanel extends JPanel{
	
	Image image; 
	Pokemon p;
	PokemonSelectPanel psp;
	boolean pressed = false;
	
	public PokemonPreviewPanel(PokemonSelectPanel psp, Pokemon p) {
		super();
		this.psp = psp;
		this.p = p;
		image = p.getSpritePanel().image;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				for (PokemonPreviewPanel psp : psp.pokemon) {
					psp.setPressed(false);
					psp.repaint();
				}
				pressed = true;
				repaint();
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				pressed = true;
				repaint();
				ApplicationData.sfx.playFile(1,1f);
				
			}
		});
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		super.paintComponent(g2);
		
		Stroke stroke = new BasicStroke(10f);
		g2.setStroke(stroke);
		if (!pressed) {
			//g2.setColor(Color.GRAY);
			
			
			
		} else {
			g2.setColor(Color.red);
			g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		
		g2.drawImage(image, 10,10, this.getWidth()-20, this.getHeight()-20, null);
		
	
	}
	
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
	
	public boolean getPressed() {
		return pressed;
	}
}
