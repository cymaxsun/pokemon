package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class CustomButton extends JButton {
	private Image image, imagePressed;
	private String text = "";
	private boolean pressed;
	private Color textColor;
	private Color textShadowColor;

	public CustomButton(Image image, Image pressedImage) {
		super();
		setContentAreaFilled(false);
		setBorder(null);
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

			@Override
			public void mousePressed(MouseEvent e) {
				pressed = true;
				repaint();
				ApplicationData.sfx.playFile(1);
				
			}
		});

	}

	public CustomButton() {
		super();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(ApplicationData.font.deriveFont(Font.PLAIN, this.getWidth() / 11));
		int width = g.getFontMetrics().stringWidth(text);
		int height = g.getFontMetrics().getHeight();

		if (pressed) {
			g.drawImage(imagePressed, 0, 0, this.getWidth(), this.getHeight(), null);
			textColor = Color.red;
			textShadowColor = Color.red;

		} else {
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
			textColor = new Color(94, 94, 104);
			textShadowColor = Color.GRAY;
			g.setColor(textShadowColor);
			g.drawString(text, (this.getWidth() - width) / 2 + 3, (this.getHeight() + height) / 2 - 3);

		}

		g.setColor(textColor);
		g.drawString(text, (this.getWidth() - width) / 2, (this.getHeight() + height) / 2 - 5);
		g.dispose();
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImagePressed() {
		return imagePressed;
	}

	public void setImagePressed(Image imagePressed) {
		this.imagePressed = imagePressed;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Color getTextShadowColor() {
		return textShadowColor;
	}

	public void setTextShadowColor(Color textShadowColor) {
		this.textShadowColor = textShadowColor;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

}
