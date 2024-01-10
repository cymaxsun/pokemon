package main;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.Insets;

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
	private JPanel pokemonStats;
	JPanel pokemonListDisplay;
	private JButton btnNewButton;
	private JPanel panel;
	private JTextArea txtrAtk;
	private Color backgroundColor  = new Color(25, 132, 66);
	/**
	 * Create the panel.
	 */
	public PokemonSelectPanel() {
		setBackground(backgroundColor);
		setLayout(new MigLayout("insets 100 100 50 100", "[50%,grow]100[30%,grow]", "[grow]"));
		
		pokemonListDisplay = new JPanel();
		pokemonListDisplay.setBorder(new CompoundBorder(new LineBorder(new Color(255, 255, 255, 125), 2, true), new LineBorder(new Color(25, 206, 115), 2, true)));
		pokemonListDisplay.setBackground(new Color(255,255,255,75));
		pokemonListDisplay.setLayout(new MigLayout("insets 25, gap 25", "[33.3%,grow][33.3%,grow][33.3%,grow]", "[33.3%,grow][33.3%,grow][33.3%,grow]"));
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
		
		pokemonStats = new JPanel();
		pokemonStats.setOpaque(false);
		previewInfoPanel.add(pokemonStats, "cell 0 1,grow");
		
		btnNewButton = new JButton("OK");
		btnNewButton.setFont(ApplicationData.font.deriveFont(Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationData.playerPokemon = selectedPokemon.getPokemon();
				timer.start();
				ApplicationData.soundtrack.stop();
		  	 	ApplicationData.soundtrack.playFile(0,0.8f);
		  	 	ApplicationData.soundtrack.loop();
		  	 	btnNewButton.setEnabled(false);
			}
		});
		pokemonStats.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		pokemonStats.add(btnNewButton, "cell 0 1,alignx center,aligny top");
		
		panel = new JPanel();
		panel.setBackground(backgroundColor);
		panel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 3), new LineBorder(backgroundColor, 2)));
		pokemonStats.add(panel, "flowx,cell 0 0,grow");
		panel.setLayout(new MigLayout("insets 0", "[5%,grow][90%,grow][5%,grow]", "[165px]"));
		
		txtrAtk = new JTextArea();   
		txtrAtk.setFont(ApplicationData.font.deriveFont(Font.PLAIN, 20));
		txtrAtk.setText("\nATK:\t\nDEF:\t\nHP:\t\nTYPE:");
		txtrAtk.setMargin(new Insets(25, 25, 25, 25));
		txtrAtk.setLineWrap(true);
		txtrAtk.setEditable(false);
		panel.add(txtrAtk, "cell 1 0,grow");
		
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
		g2.setColor(Color.white);
		g2.setFont(ApplicationData.font.deriveFont(Font.BOLD, 40));
		int width = g2.getFontMetrics().stringWidth("SELECT A POKEMON");
		int height = g2.getFontMetrics().getHeight();
		g2.drawString("SELECT A POKEMON", (this.getWidth()-width)/2, (85+height)/2);
        
       
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
	
	public void updateStats() {
		
		txtrAtk.setText( selectedPokemon.getPokemon().getName() +"\nATK:  " + selectedPokemon.getPokemon().getBaseAtk() + "\nDEF:  "+ selectedPokemon.getPokemon().getBaseDef()+ "\nHP:  "+ selectedPokemon.getPokemon().getMaxHp() + "\nTYPE:  "+ selectedPokemon.getPokemon().getType());
		
		
	}
}
