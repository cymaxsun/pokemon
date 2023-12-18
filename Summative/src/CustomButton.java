import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CustomButton extends JButton {
	private Image image, imagePressed;
	private String text;
	private boolean pressed;

	public CustomButton(Image image, Image pressedImage, String text) {
		super();
		pressed = false;
		setContentAreaFilled(false);
		setBorder(null);
		this.image = image;
		this.imagePressed = pressedImage;
		this.text = text;

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pressed = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pressed = false;
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pressed = false;
				repaint();
			}
		});

	}

	public CustomButton(Image image, Image pressedImage) {
		super();
		setContentAreaFilled(false);
		setBorder(null);
		text = "";
		pressed = false;
		this.image = image;
		this.imagePressed = pressedImage;

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pressed = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pressed = false;
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pressed = false;
				repaint();
			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (pressed) {
			g.drawImage(imagePressed, 0, 0, this.getWidth(), this.getHeight(), null);

		} else {
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);

		}
		
		g.drawString(text, this.getWidth() / 2, this.getHeight() / 2);

	}

}
