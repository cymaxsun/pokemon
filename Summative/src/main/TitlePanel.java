package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.miginfocom.swing.MigLayout;

public class TitlePanel extends ImagePanel {

	Image titleImage;
	Image bgImage;
	Image settingsImage;
	final String subtitle = "JAVA EDITION";
	final String pressStart = "PRESS ENTER";
	final int settingX = ApplicationData.frameWidth-90;
	final int settingY = ApplicationData.frameHeight-90;
	final int settingSize = 80;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TitlePanel() {
		super();
		try {
			
			bgImage = ImageIO.read(getClass().getResourceAsStream("/backgrounds/titleBg.png"));
			settingsImage = ImageIO.read(getClass().getResourceAsStream("/assets/settings.png")).getScaledInstance(settingSize, -1, Image.SCALE_SMOOTH);;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setImage(bgImage);
		setPreferredSize(ApplicationData.frameDimensions);
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		addKeyListener(new KeyAdapter() {
			 @Override
			    public void keyPressed(KeyEvent e) {
			        char c = e.getKeyChar();
			        if (c == KeyEvent.VK_ENTER) {
			        	ApplicationData.sfx.playFile(1);
			        	ApplicationData.switchPanel(ApplicationData.titlePanel, ApplicationData.charSelect);	
			        } else if (c == KeyEvent.VK_ESCAPE) {
			        	ApplicationData.sfx.playFile(1);
			        	ApplicationData.switchPanel(ApplicationData.titlePanel, ApplicationData.settings);
			        	
			        }
			    }
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int xPos = e.getX();
				int yPos = e.getY();
				if (xPos >= settingX && yPos >= settingY && xPos <= (settingX + settingSize) && yPos <= (settingY + settingSize)) {
					ApplicationData.sfx.playFile(1);
					ApplicationData.switchPanel(ApplicationData.titlePanel, ApplicationData.settings);
		        	
				}
			}
		});
		setFocusable(true);
	}

	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		g2.drawImage(ApplicationData.logo, (this.getWidth() - ApplicationData.logo.getWidth(null)) / 2 ,this.getHeight() / 4 - ApplicationData.logo.getHeight(null) / 2, null);
		g2.setColor(Color.BLACK);
		g2.setFont(ApplicationData.font.deriveFont(Font.BOLD, 50));
		
		int textWidth = g2.getFontMetrics().stringWidth(subtitle);
		int textHeight = g2.getFontMetrics().getHeight();

		Graphics2D tempG2 = (Graphics2D) g2.create();
		AffineTransform transform = tempG2.getTransform();
		int x = (this.getWidth() - textWidth) / 2 + 25;
		int y = this.getHeight() / 4 + ApplicationData.logo.getHeight(null) - textHeight - 15;
		transform.translate(x, y);
		tempG2.transform(transform);
		tempG2.setColor(Color.black);
		FontRenderContext frc = g2.getFontRenderContext();
		TextLayout tl = new TextLayout(subtitle, tempG2.getFont().deriveFont(Font.BOLD,50f), frc);
		Shape shape = tl.getOutline(null);
		tempG2.setStroke(new BasicStroke(5f));
		tempG2.draw(shape);
		tempG2.setColor(Color.white);
		tempG2.fill(shape);
		
		g2.setFont(ApplicationData.font.deriveFont(30F));
		textWidth = g2.getFontMetrics().stringWidth("PRESS ENTER");
		textHeight = g2.getFontMetrics().getHeight();
		tempG2 = (Graphics2D) g2.create();
		transform = tempG2.getTransform();
		transform.translate((this.getWidth()-textWidth)/2 + 25, 500);
		tempG2.transform(transform);
		tempG2.setColor(Color.black);
		tl = new TextLayout("PRESS ENTER", tempG2.getFont().deriveFont(30F), frc);
		shape = tl.getOutline(null);
		tempG2.setStroke(new BasicStroke(5f));
		tempG2.draw(shape);
		tempG2.setColor(Color.white);
		tempG2.fill(shape);
		
		g2.drawImage(settingsImage, settingX, settingY,null);
		//g2.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());

	}

}
