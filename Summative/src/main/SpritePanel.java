package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import pokemon.Pokemon;

public class SpritePanel extends JPanel{

	Pokemon p;
	public Image image; 
	Graphics2D g2;
	public int tick = 0;
	public Timer damageTaken;
	
	public SpritePanel(Pokemon p) {
		this.p = p;
		setOpaque(false);
	}
	
	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		this.g2 = g2;
		super.paintComponent(g2);
		g2.drawImage(image, this.getWidth()/2-image.getWidth(null)/2, this.getHeight()*3/5-image.getHeight(null)/2, null);
		if (tick == 1) {
			g2.setColor(new Color(255,255,255,75));
			g2.fillRect(this.getWidth()/2-image.getWidth(null)/2, this.getHeight()*3/5-image.getHeight(null)/2, image.getWidth(null), image.getHeight(null));
		}
	}
	
	
	public void damageTaken() {
		damageTaken = new Timer(25, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tick++;
				if (tick == 3) {
					tick = 0;
				}
				repaint();
			}
		});
		damageTaken.start();
		ApplicationData.eventQueue.pop().run();
	}
}
