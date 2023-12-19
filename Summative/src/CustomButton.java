import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;

public class CustomButton extends JButton {
	private Image image, imagePressed;
	private String text;
	private boolean pressed;


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

	public void setText(String text){
		this.text = text;
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (pressed) {
			g.drawImage(imagePressed, 0, 0, this.getWidth(), this.getHeight(), null);
			g.setColor(Color.red);

		} else {
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
			g.setColor(new Color(94, 94, 104));

		}
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("pokemon.ttf"));
			g.setFont(font.deriveFont(Font.PLAIN, this.getWidth()/11));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int width = g.getFontMetrics().stringWidth(text);
		int height = g.getFontMetrics().getHeight();
		g.drawString(text, (this.getWidth()-width)/2, (this.getHeight()+height)/2-5);

	}

}
