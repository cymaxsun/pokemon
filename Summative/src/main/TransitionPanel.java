package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransitionPanel extends JPanel {
	private Timer timer;
	private int startAngle = 0;
	private int trailAngle = 0;
	private boolean transitionComplete = false;

	public TransitionPanel() {
    	ApplicationData.soundtrack.playFile(0,0.8f);
    	ApplicationData.soundtrack.loop();
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
             }
         });
         timer.start(); // Start the timer
     }

     @Override
     protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         Graphics2D g2 = (Graphics2D) g.create();

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
		timer.start(); // Start the timer
	}
}
