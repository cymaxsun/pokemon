package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

public class EndPanel extends ImagePanel {

	private static final long serialVersionUID = 1L;
	private Image bg = new ImageIcon(getClass().getResource("/backgrounds/End Screen.jpg")).getImage();
	private CustomText playAgainButton;
	private CustomText quitButton;

	/**
	 * Create the panel.
	 */

	public EndPanel() {
		
		setImage(bg);
		setPreferredSize(ApplicationData.frameDimensions);
		
		
		
		
		playAgainButton = new CustomText("PLAY AGAIN");
		playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		playAgainButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				ApplicationData.sfx.playFile(1);
				ApplicationData.resetGame();
				ApplicationData.switchPanel(ApplicationData.endPanel, ApplicationData.titlePanel);

			}
		});
		playAgainButton.setFont(ApplicationData.font.deriveFont(50f));
		
	
		
		quitButton = new CustomText("QUIT");
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				ApplicationData.sfx.playFile(1);
				ApplicationData.window.dispatchEvent(new WindowEvent(ApplicationData.window, WindowEvent.WINDOW_CLOSING));
				
			}

		});
		quitButton.setFont(ApplicationData.font.deriveFont(50f));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(609)
							.addComponent(quitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(548)
							.addComponent(playAgainButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(548, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(372, Short.MAX_VALUE)
					.addComponent(playAgainButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(91)
					.addComponent(quitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(195))
		);
		setLayout(groupLayout);
		

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(255,255,255, 100));
		g.fillRoundRect((this.getWidth()-600)/2, 225, 600, 400, 20, 20);
		g.setColor(Color.BLACK);
		g.drawRoundRect((this.getWidth()-600)/2, 225, 600, 400, 20, 20);
		g.drawImage(ApplicationData.logo, (this.getWidth() - ApplicationData.logo.getWidth(null)) / 2 - 10,this.getHeight() / 4 - ApplicationData.logo.getHeight(null) / 2, null);
		
	}
}
