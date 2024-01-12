package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import pokemon.Pokemon;

public class StatusPanel extends JPanel {

	public JLabel name;
	public JLabel lvl;
	public JPanel hpPanel;
	public JLabel hpLbl;
	public JProgressBar hpBar;
	public JLabel hpValue;
	public Pokemon p;

	public String insets;
	public String name1;
	public int hpValue1;

	private Color background = this.getBackground();
	private Color highlights = new Color(184, 184, 184);
	private Color textColor = Color.black;

	public StatusPanel(Pokemon p) {
		this.p = p;
		hpBar = new JProgressBar(0, p.getMaxHp());
		hpValue1 = p.getMaxHp();
		name1 = p.getName();

		setLayout(new MigLayout("insets 20 45 20 45, gapy 0", "[80px][70%,grow]", "[55%,grow][5%,grow]5[45%,grow]"));
		
		lvl = new JLabel("Lv. " + p.getLvl());
		lvl.setPreferredSize(new Dimension(30, 25));
		lvl.setFont(ApplicationData.font.deriveFont(Font.PLAIN, 15));
		lvl.setHorizontalAlignment(SwingConstants.CENTER);
		add(lvl, "cell 1 0,alignx right,aligny bottom");
		
		hpPanel = new JPanel();
		hpPanel.setBackground(new Color(94, 94, 104));
		add(hpPanel, "cell 1 1,grow");
		hpPanel.setLayout(new MigLayout("insets 0, gapx 0", "[:1%:1%,grow][][:1%:1%,grow][18px,grow][::1%,grow]",
				"[::1%,grow][98%,grow][::1%,grow]"));

		hpLbl = new JLabel("HP");
		hpLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		hpLbl.setForeground(Color.ORANGE);
		hpPanel.add(hpLbl, "cell 1 0 1 3,alignx center,growy");
		hpLbl.setFont(new Font("Tahoma", Font.BOLD, 16));

		hpBar.setForeground(Color.GREEN);
		hpBar.setBackground(new Color(94, 94, 104));
		hpBar.setValue(p.getMaxHp());
		hpBar.setBorderPainted(false);
		hpPanel.add(hpBar, "cell 3 1,grow");

		hpBar.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (hpBar.getValue() <= p.getMaxHp()/4) {
					hpBar.setForeground(Color.red);
				} else if (hpBar.getValue() <=  p.getMaxHp()/2) {
					hpBar.setForeground(Color.yellow);
					
				} else {
					hpBar.setForeground(Color.green);
				}
			}
		});

		hpValue = new JLabel(p.getCurrentHp() + "/" + p.getMaxHp());
		hpValue.setFont(ApplicationData.font.deriveFont(Font.BOLD, 15));
		hpValue.setPreferredSize(new Dimension(30, 25));
		//add(hpValue, "cell 1 2,alignx center,aligny top");

		setOpaque(false);

	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		super.paintComponent(g2);

		drawBackground(g2);
		drawText(g2);
		drawBorder(g2);
	
	}



	public void drawText(Graphics2D g2) {
		int x ;
		int y  = 48 ;
		g2.setColor(textColor);
		g2.setFont(ApplicationData.font.deriveFont(Font.PLAIN, 30));
		if (p.isAllied()) {
			x = 60;
		} else {
			x = 60;
		}
		
		g2.drawString(name1, x, y);
		g2.setFont(ApplicationData.font.deriveFont(Font.BOLD, 20));
		int width = g2.getFontMetrics().stringWidth(hpValue1 + "/" + p.getMaxHp());
		int height = g2.getFontMetrics().getHeight();
		g2.setColor(background);
		g2.fillRoundRect(this.getWidth()/2-30, this.getHeight()*3/5+6, width + 60, height+10, 20, 15);
		g2.setColor(textColor);
		g2.drawString(hpValue1 + "/" + p.getMaxHp(), this.getWidth()/2, this.getHeight()*4/5);
	
	}
	
	public void drawBackground(Graphics2D g2) {
		int[] xPoints = { 0, 20, this.getWidth() - 20, this.getWidth() }; // X coordinates of the trapezoid
		int[] yPoints = { this.getHeight(), 0, 0, this.getHeight() }; // Y coordinates of the trapezoid

		if (p.isAllied()) {
			xPoints[2] = this.getWidth();
		} else {
			xPoints[1] = 0;
		}

		
		
		g2.setColor(background); // Set the color for the trapezoid
		
		g2.fillPolygon(xPoints, yPoints, 4); // Draw the trapezoid using the provided coordinates

		g2.setColor(highlights);
		yPoints[1] = this.getHeight() / 2;
		yPoints[2] = this.getHeight() / 2;

		if (p.isAllied()) {
			xPoints[1] = 10;
		} else {
			xPoints[2] = this.getWidth() - 10;
		}

		g2.fillPolygon(xPoints, yPoints, 4);

		g2.setColor(background);

		if (p.isAllied()) {
			xPoints[0] = 9;
			xPoints[1] = 9;
			xPoints[2] = this.getWidth();
			xPoints[3] = this.getWidth();
		} else {
			xPoints[0] = 0;
			xPoints[1] = 0;
			xPoints[2] = this.getWidth() - 9;
			xPoints[3] = this.getWidth() - 9;
		}

		yPoints[0] = this.getHeight() / 2 + 6;
		yPoints[1] = this.getHeight() / 2 + 3;
		yPoints[2] = this.getHeight() / 2 + 3;
		yPoints[3] = this.getHeight() / 2 + 6;

		g2.fillPolygon(xPoints, yPoints, 4);
		
		
	}
	
	public void drawBorder(Graphics2D g2) {
		int[] xPoints = {0, 20, this.getWidth() - 20, this.getWidth()};
		int[] yPoints = {this.getHeight(), 0, 0, this.getHeight()};

		if (p.isAllied()) {
		    xPoints[2] = this.getWidth();
		} else {
		    xPoints[1] = 0;
		}

		g2.setColor(Color.black);

		// Define different strokes for different line segments
		Stroke stroke1 = new BasicStroke(8f); // First line's stroke
		Stroke stroke2 = new BasicStroke(3f); // Second line's stroke

		g2.setStroke(stroke2);
		g2.drawLine(xPoints[2], yPoints[2], xPoints[3], yPoints[3]);
		g2.drawLine(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
		

		g2.setStroke(stroke1);
		g2.drawLine(xPoints[1]+2, yPoints[1], xPoints[2]-2, yPoints[2]);
		g2.drawLine(xPoints[3], yPoints[3], xPoints[0], yPoints[0]);
	}

}
