package main;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class PokemonSelect extends JPanel {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private int startAngle = 0;
	private int trailAngle = 0;
	private boolean transitionComplete = false;
	
	
	/**
	 * Create the panel.
	 */
	public PokemonSelect() {
		setLayout(new MigLayout("", "[33%,grow][33%,grow][33%,grow]", "[grow]"));
		
		PokemonScrollPane scrollPane = new PokemonScrollPane();
		add(scrollPane, "cell 0 0,grow");
		
		JPanel panel = new JPanel();
		add(panel, "cell 1 0 2 1,grow");
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.start();
				ApplicationData.soundtrack.playFile(0,0.8f);
		    	ApplicationData.soundtrack.loop();
			}
		});
		btnNewButton.setBounds(144, 234, 89, 23);
		panel.add(btnNewButton);
		
		timer = new Timer(20, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 if (!transitionComplete) {
                     startAngle += 5; // Decrease the start angle for counterclockwise motion
                     trailAngle -= 5; // Increase the trail angle to cover the circle

                     // Check if the trail angle exceeds 360 degrees
                     if (trailAngle <= -360) {
                         trailAngle = 0; // Reset trail angle to start from the beginning
                         startAngle = 90; // Reset start angle to start from 3 o'clock
                         transitionComplete = true; // Transition is complete
                     }
                     repaint(); // Trigger repaint to update the panel
                 } else {
                	 System.out.println("end");
                	 ApplicationData.window.setVisible(false);
                     ApplicationData.battleFrame = new BattleFrame();
                	 timer.stop(); // Stop the timer
                     
                 }
                 repaint();
             }
             
         });

	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
        super.paint(g2);
        

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        if (!transitionComplete) {
            // Set the color to black
            g2.setColor(Color.BLACK);

            // Calculate the bounding box for the arc
            int radius = (int) Math.sqrt(Math.pow(centerX, 2) + Math.pow(centerY, 2));
            int x = centerX - radius;
            int y = centerY - radius;
            int width = 2 * radius;
            int height = 2 * radius;

            // Draw the counterclockwise arc
            g2.fillArc(x, y, width, height, startAngle, trailAngle);
        } else {
            // Paint the entire panel black once transition is complete
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        g2.dispose();
    }


	public void startAnimtion() {
		timer.start();
  	 	ApplicationData.soundtrack.playFile(0,0.8f);
  	 	ApplicationData.soundtrack.loop();
	}
}
