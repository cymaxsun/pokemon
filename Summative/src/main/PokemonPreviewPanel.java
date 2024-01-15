package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import pokemon.Pokemon;

public class PokemonPreviewPanel extends JPanel{
	
	Image image; 


	Pokemon p;
	PokemonSelectPanel psp;
	boolean pressed;
	Color borderColor;
	Stroke borderStroke;
	boolean enabled;
	


	public PokemonPreviewPanel(PokemonSelectPanel psp) {
		super();
		this.psp = psp;
		enabled = true;
		pressed = false;
		borderColor = Color.RED;
		setOpaque(false);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (!enabled) {
					return;
				}
				for (PokemonPreviewPanel[] psp : psp.pokemon) {
					for (PokemonPreviewPanel p : psp) {
						p.setPressed(false);
						p.repaint();
					}
					
				}
				pressed = true;
				psp.selectedPokemon.setPokemon(p);
				psp.repaint();
				pressed = true;
				psp.updateStats();
				repaint();
				ApplicationData.sfx.playFile(1);
				
			}
		});
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		super.paintComponent(g2);
		
		g2.drawImage(image, 0,0, this.getWidth(), this.getHeight(), null);
		Stroke stroke = new BasicStroke(10f);
		g2.setStroke(stroke);
		if (borderColor != null) {
			if (pressed) {
				g2.setColor(borderColor);
				g2.drawRoundRect(0, 0, this.getWidth(), this.getHeight(),25,25);
			} else {
			
			}
			
 
		}
		g2.dispose();
		
	
		
	
	}
	
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
	
	public boolean getPressed() {
		return pressed;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	
	public Pokemon getPokemon() {
		return p;
	}

	public void setPokemon(Pokemon p) {
		this.p = p;
		if (p != null) {
			image = p.image;
		}
		
	}
	
	public boolean isBorderEnabled() {
		return enabled;
	}

	public void setBorderEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
