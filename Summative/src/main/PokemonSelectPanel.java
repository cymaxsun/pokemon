package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;
import pokemon.Brian;
import pokemon.Ethan;
import pokemon.Imogen;
import pokemon.Pokemon;

public class PokemonSelectPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private int startAngle = 0;
	private int trailAngle = 0;
	private boolean transitionComplete = false;
	JPanel viewport;
	public PokemonPreviewPanel[][] pokemon;
	Pokemon[][] pokemonList = {{new Brian(), new Ethan(), new Imogen()},{ new Imogen(),new Brian(), new Ethan()},{ new Ethan(),new Imogen(), new Brian()}};
	private JPanel previewInfoPanel;
	PokemonPreviewPanel selectedPokemon;
	private JPanel pokemonStats;
	JPanel pokemonListDisplay;
	private JButton btnNewButton;
	private JPanel panel;
	private JTextArea txtrAtk;
	private Color backgroundColor  = new Color(25, 132, 66);
	private Image back;
	final private int backX = 20;
	final private int backY = 15;
	final private int backSize = 60;
	
	/**
	 * Create the panel.
	 */
	public PokemonSelectPanel() {
		setBackground(backgroundColor);
		setBorder(new MatteBorder(0, 7, 7, 7	, Color.gray));
		setLayout(new MigLayout("insets 100 100 50 100", "[50%,grow]100[30%,grow]", "[grow]"));
		
		pokemonListDisplay = new JPanel();
		pokemonListDisplay.setBorder(new CompoundBorder(new LineBorder(new Color(255, 255, 255, 125), 2, true), new LineBorder(new Color(25, 206, 115), 2, true)));
		pokemonListDisplay.setBackground(new Color(255,255,255,75));
		pokemonListDisplay.setLayout(new MigLayout("insets 25, gap 25", "[33.3%,grow][33.3%,grow][33.3%,grow]", "[33.3%,grow][33.3%,grow][33.3%,grow]"));
		pokemon = new PokemonPreviewPanel[3][3];
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				pokemon[row][col] = new PokemonPreviewPanel(this);
				pokemon[row][col].setPokemon(pokemonList[row][col]);
				pokemonListDisplay.add(pokemon[row][col],"cell " 	+ col + " " + row + " , grow");
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
		
		timer = new Timer(35, new ActionListener() {
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
                	 ApplicationData.battleFrame = new BattleFrame();
                	 ApplicationData.charSelect.setVisible(false);
                	 ApplicationData.window.add(ApplicationData.battleFrame);
                	 ApplicationData.battleFrame.setVisible(true);
                	 timer.stop(); // Stop the timer
                     
                 }
                 
             }
             
         });
		
		try {
			back = ImageIO.read(getClass().getResourceAsStream("/assets/backButton.png")).getScaledInstance(backSize, -1, Image.SCALE_SMOOTH);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				int xPos = e.getX();
				int yPos = e.getY();
				if (xPos >= backX && yPos >= backY && xPos <= (backX + backSize) && yPos <= (backY + backSize)) {
					ApplicationData.sfx.playFile(1,1f);
					ApplicationData.charSelect.setVisible(false);
		        	ApplicationData.window.add(ApplicationData.titlePanel);
		        	ApplicationData.titlePanel.setVisible(true);
		        	ApplicationData.titlePanel.requestFocusInWindow();
		        	ApplicationData.window.remove(ApplicationData.charSelect);
		        	
				}
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

        
   
		super.paintComponent(g2);
		g2.setColor(new Color(25, 206, 115));
		g2.fillRect(20, 90, getWidth()-40, getHeight()-110);
		g2.setColor(new Color(24, 173, 92));
		Stroke stroke = new BasicStroke(5f);
		g2.setStroke(stroke);
		g2.drawRect(20, 90, getWidth()-40, getHeight()-110);
		g2.setColor(Color.white);
		g2.setFont(ApplicationData.font.deriveFont(Font.BOLD, 40));
		int width = g2.getFontMetrics().stringWidth("SELECT A POKEMON");
		int height = g2.getFontMetrics().getHeight();
		g2.drawString("SELECT A POKEMON", (this.getWidth()-width)/2, (85+height)/2);
        g2.drawImage(back, backX, backY, null);
       
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
		
		txtrAtk.setText( selectedPokemon.getPokemon().getName() +"\nATK:  " + selectedPokemon.getPokemon().getBaseAtk() + "\nDEF:  "+ selectedPokemon.getPokemon().getBaseDef()+ "\nHP:  "+ selectedPokemon.getPokemon().getMaxHp() + "\nTYPE:  "+ Pokemon.getTypeName(selectedPokemon.getPokemon().getType()));
		
		
	}
}
