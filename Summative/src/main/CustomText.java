package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JComponent;

public class CustomText extends JComponent {

	String text;
	FontMetrics metrics;

	public CustomText(String text) {
		super();
		setOpaque(false);
		this.text = text;
		setAlignmentX(LEFT_ALIGNMENT);
		setFont(ApplicationData.font.deriveFont(20));
		setForeground(new Color(88, 88, 80));
		setBackground(new Color(168, 184, 184));

	}

	@Override
	public void setFont(Font font) {
		super.setFont(font);
		metrics = getFontMetrics(font);
		
		setPreferredSize(new Dimension(metrics.stringWidth(text) + 5, metrics.getHeight()));
		setSize(getPreferredSize());
	}

	public void setText(String text) {
		this.text = text;
		repaint();
	}

	public void resize(Graphics g) {
		Font ogFont = getFont();

		if (metrics.stringWidth(text)>this.getWidth()) {
			while (metrics.stringWidth(text) > this.getWidth()) {
				//System.out.println(metrics.stringWidth(text) + ">" + this.getWidth());
				//System.out.println("change font size from " + getFont().getSize() + " to: " + (getFont().getSize() - 5));
				float newSize = getFont().getSize2D()-5f;
				super.setFont(getFont().deriveFont(newSize));
				metrics = getFontMetrics(getFont());
				setPreferredSize(new Dimension(metrics.stringWidth(text) + (getFont().getSize()/10), this.getHeight()));
				//System.out.println("set font to size : " + getFont().getSize());
				//System.out.println("new size; " + metrics.stringWidth(text));
			}
			System.out.println("RESIZED");
			repaint();
		} else {
			return;
		}
		
		//super.setFont(ogFont);
	}
	

	@Override
	public void paintComponent(Graphics g) {
		int centerY = (this.getHeight() + metrics.getAscent() - metrics.getDescent()) / 2 - (getFont().getSize()/16);
		int centerX;
		if (this.getAlignmentX() == 0.5f) {
			centerX = (this.getWidth()-metrics.stringWidth(text))/2;
		} else {
			centerX = 0;
		}
		
		
		
		g.setFont(getFont());
		g.setColor(getBackground());
		g.drawString(text, centerX + (getFont().getSize()/16), centerY + (getFont().getSize()/16));

		g.setColor(getForeground());
		g.drawString(text, centerX+ 0, centerY);

		
		
	}

}
