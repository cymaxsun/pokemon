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
				setPreferredSize(new Dimension(metrics.stringWidth(text) + 5, this.getHeight()));
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
		//System.out.println(this.text);
		//System.out.println("strring width: " + metrics.stringWidth(text) + "> container width: " + this.getWidth());
		//System.out.println("font size: " + getFont().getSize());
		//resize(g);
		// System.out.println(metrics.stringWidth(text) + ">" + this.getWidth());
		// g.setColor(Color.BLACK);
		// g.fillRect(0, 0, this.getWidth(), this.getHeight());
		int centerY = (this.getHeight() + metrics.getAscent() - metrics.getDescent()) / 2 - 3;

		g.setFont(getFont());
		g.setColor(getBackground());
		g.drawString(text, 3, centerY + 3);

		g.setColor(getForeground());
		g.drawString(text, 0, centerY);

		
		
	}

}
