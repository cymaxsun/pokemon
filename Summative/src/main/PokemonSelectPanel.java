package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
	public PokemonPreviewPanel[] pokemon;
	Pokemon[] pokemonList = {new Brian(), new Ethan(), new Brian(), new Ethan(),new Brian(), new Ethan()};
	private JPanel previewInfoPanel;
	PokemonPreviewPanel selectedPokemon;
	private JPanel panel_2;
	JPanel pokemonListDisplay;
	private JButton btnNewButton;
	private JTextArea textArea;
	
	/**
	 * Create the panel.
	 */
	public PokemonSelectPanel() {
		//setBackground(new Color(25, 206, 115));
		setBackground(new Color(25, 132, 66));
		setLayout(new MigLayout("insets 100 100 50 100", "[50%,grow]100[30%,grow]", "[grow]"));
		
		pokemonListDisplay = new JPanel();
		pokemonListDisplay.setOpaque(false);
		pokemonListDisplay.setLayout(new MigLayout("gap 25", "[33.3%,grow][33.3%,grow][33.3%,grow]", "[33.3%,grow][33.3%,grow][33.3%,grow]"));
		pokemon = new PokemonPreviewPanel[ApplicationData.numOfPokemon];
		for (int i = 0; i < ApplicationData.numOfPokemon;i++) {
			pokemon[i] = new PokemonPreviewPanel(this);
			pokemon[i].setPokemon(pokemonList[i]);
			if (i<3) {
				pokemonListDisplay.add(pokemon[i],"cell " 	+ i + " 0 , grow");
				
			} else {
				pokemonListDisplay.add(pokemon[i],"cell " + (i-3) + " 1 , grow");
				
			}
			
		}
		add(pokemonListDisplay, "cell 0 0,grow");

		
		previewInfoPanel = new JPanel();
		previewInfoPanel.setOpaque(false);
		add(previewInfoPanel, "cell 1 0,grow");
		previewInfoPanel.setLayout(new MigLayout("insets 0", "[grow]", "[65%,grow][35%,grow]"));
		
		selectedPokemon = new PokemonPreviewPanel(this);
		selectedPokemon.setBorderEnabled(false);
		previewInfoPanel.add(selectedPokemon, "cell 0 0,grow");
		
		panel_2 = new JPanel();
		previewInfoPanel.add(panel_2, "cell 0 1,grow");
		
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationData.playerPokemon = selectedPokemon.getPokemon();
				timer.start();
		  	 	ApplicationData.soundtrack.playFile(0,0.8f);
		  	 	ApplicationData.soundtrack.loop();
			}
		});
		panel_2.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		panel_2.add(btnNewButton, "cell 0 1,alignx center,aligny top");
		
		textArea = new JTextArea();
		panel_2.add(textArea, "cell 0 0,grow");
		
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
                	 ApplicationData.window.setVisible(false);
                     ApplicationData.battleFrame = new BattleFrame();
                	 timer.stop(); // Stop the timer
                     
                 }
                 repaint();
             }
             
         });

	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
        
        
   
		super.paintComponent(g2);
		g2.setColor(new Color(25, 206, 115));
		g2.fillRect(20, 90, getWidth()-40, getHeight()-120);
        
       
    }
	
	@Override
	public void paint(Graphics g) {

		int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
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
