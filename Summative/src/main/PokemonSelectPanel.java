package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

import net.miginfocom.swing.MigLayout;
import pokemon.Brian;
import pokemon.Ethan;
import pokemon.Pokemon;

public class PokemonSelectPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private int startAngle = 0;
	private int trailAngle = 0;
	private boolean transitionComplete = false;
	JPanel viewport;
	JLabel label;
	public PokemonPreviewPanel[] pokemon;
	Pokemon[] pokemonList = {new Brian(), new Ethan(), new Brian(), new Ethan()};
	
	/**
	 * Create the panel.
	 */
	public PokemonSelectPanel() {
		setLayout(new MigLayout("", "[33%,grow][33%,grow][33%,grow]", "[grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		viewport = new JPanel();
		viewport.setPreferredSize(new Dimension(10, ApplicationData.numOfPokemon*300));
		viewport.setLayout(new GridLayout(ApplicationData.numOfPokemon, 0, 0, 0));
		pokemon = new PokemonPreviewPanel[ApplicationData.numOfPokemon];
		viewport.setBackground(new Color(255, 255, 255));
		for (int i = 0; i < ApplicationData.numOfPokemon;i++) {
			pokemon[i] = new PokemonPreviewPanel(this,pokemonList[i]);
			viewport.add(pokemon[i]);
		}
		label = new JLabel("pokemon");
			
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		scrollPane.setColumnHeaderView(label);	
		scrollPane.setViewportView(viewport);
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
		
		timer = new Timer(50, new ActionListener() {
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
