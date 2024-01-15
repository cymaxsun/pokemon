package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import moves.PokemonMove;
import net.miginfocom.swing.MigLayout;
import pokemon.Pokemon;

public class BattlePanel extends JPanel {

	private Image panelBkg = new ImageIcon(getClass().getResource("/backgrounds/btnbkg.png")).getImage();
	private Image fightBackground = new ImageIcon(getClass().getResource("/backgrounds/bkg.png")).getImage();
	private Image fight = new ImageIcon(getClass().getResource("/buttons/fight.png")).getImage();
	private Image fightPressed = new ImageIcon(getClass().getResource("/buttons/fightPressed.png")).getImage();
	private Image bag = new ImageIcon(getClass().getResource("/buttons/bag.png")).getImage();
	private Image bagPressed = new ImageIcon(getClass().getResource("/buttons/bagPressed.png")).getImage();
	private Image pokemon = new ImageIcon(getClass().getResource("/buttons/pokemon.png")).getImage();
	private Image pokemonPressed = new ImageIcon(getClass().getResource("/buttons/pokemonPressed.png")).getImage();
	private Image run = new ImageIcon(getClass().getResource("/buttons/run.png")).getImage();
	private Image runPressed = new ImageIcon(getClass().getResource("/buttons/runPressed.png")).getImage();
	public JTextArea textBox;
	public JButton move1, move2, move3, move4;
	public CustomButton fightButton, bagButton, pokemonButton, runButton;
	public JLabel allyPoke, enemyPoke, movePP, moveType;
	public JPanel movePanel, textPanel, actionPanel, textAreaPanel;
	public ImagePanel bottomPanel, topPanel;
	public boolean gameOver = false;
	public Pokemon enemyPokemon, playerPokemon;
	public int fadeSat = 0;
	public MoveInfoPanel moveInfo;
	public int turnNumber = 0;


	// GAME STATE
	public int gameState;
	public final int playState = 0;
	public final int pauseState = 1;

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public BattlePanel() {
		setFocusable(true);
		playerPokemon = ApplicationData.playerPokemon;
		enemyPokemon = ApplicationData.enemyPokemon;
		ApplicationData.animate = new AnimationHandler(this);

		
		


		setBounds(0, 0, ApplicationData.frameWidth, ApplicationData.frameHeight);
		
		setBackground(new Color(255, 255, 255));


		setLayout(
				new MigLayout("insets 0, gapy 0, gapx 0", "[50%,grow][50%, grow]", "[70%,grow][30%:n:30%,grow,fill]"));

		// JPanel topPanel = new JPanel();
		topPanel = new ImagePanel(fightBackground);
		topPanel.setLayout(new MigLayout("insets 0, gapy 0, gapx 0", "[35%,grow][15%,grow][15%,grow][35%,grow]",
				"[10%,grow][25%,grow][15%,grow][15%,grow][25%,grow][10%,grow]"));
		add(topPanel, "grow, span");

		topPanel.add(enemyPokemon.getStatusPanel(), "cell 0 1,grow");

		
		topPanel.add(enemyPokemon.getSpritePanel(), "cell 2 1 2 3,grow");

		
	    topPanel.add(playerPokemon.getSpritePanel(), ",cell 0 4 2 2,growx,growy ");

		topPanel.add(playerPokemon.getStatusPanel(), "cell 3 4,grow");

		bottomPanel = new ImagePanel(panelBkg);
		// JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(255, 255, 255));
		bottomPanel.setBorder(new CompoundBorder(new MatteBorder(5, 0, 0, 0, (Color) new Color(0, 0, 0)),
				new MatteBorder(5, 0, 5, 0, (Color) new Color(130, 212, 126))));
		add(bottomPanel, "cell 0 1 4 1,grow");
		
		bottomPanel.setLayout(new MigLayout("", "[49%,grow]1%[49%,grow]", "[::100%,grow]"));

		actionPanel = new JPanel();
		actionPanel.setOpaque(false);
		actionPanel.setLayout(new MigLayout("insets 0, gap 0", "[50%,grow][50%,grow]", "[50%,grow][50%,grow]"));
		bottomPanel.add(actionPanel, "cell 1 0,grow");

		textPanel = new JPanel();
		textPanel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 7, true),
				new LineBorder(new Color(221, 195, 104), 2, true)));
		textPanel.setBackground(new Color(96, 96, 104));
		bottomPanel.add(textPanel, "cell 0 0 1 2,grow");
		textPanel.setLayout(new MigLayout("gapx 0", "[2.5%:n:2.5%][95%,grow][2.5%:n:2.5%]", "[::100%,grow]"));

		textAreaPanel = new JPanel();
		textAreaPanel.setOpaque(false);
		textPanel.add(textAreaPanel, "cell 1 0,grow");
		textAreaPanel.setLayout(new MigLayout("insets 0 10 0 10", "[::100%,grow]", "[::100%,grow]"));

		textBox = new JTextArea();
		textBox.setLineWrap(true);
		textBox.setMinimumSize(new Dimension(0, 0));
		textBox.setText("What will \n" + playerPokemon.getName() + " do?");
		textBox.setMargin(new Insets(20, 35, 15, 35));
		textBox.setForeground(new Color(94, 94, 104));
		textBox.setEditable(false);
		textBox.setFont(ApplicationData.font.deriveFont(Font.PLAIN, 55));
		textBox.setBackground(Color.WHITE);
		textAreaPanel.add(textBox, "cell 0 0,grow");

		fightButton = new CustomButton(fight, fightPressed);
		actionPanel.add(fightButton, "cell 0 0,grow");

		bagButton = new CustomButton(bag, bagPressed);
		actionPanel.add(bagButton, "cell 1 0,grow");

		pokemonButton = new CustomButton(pokemon, pokemonPressed);
		actionPanel.add(pokemonButton, "cell 0 1,grow");

		runButton = new CustomButton(run, runPressed);
		actionPanel.add(runButton, "cell 1 1, grow");

		movePanel = new JPanel();
		movePanel.setLayout(
				new MigLayout("insets 0, gap 0", "[50%,grow][50%,grow]", "[:50%:50%,grow,fill][:50%:50%,grow]"));
		movePanel.setOpaque(false);

		move1 = new CustomButton(playerPokemon.getMove1().getButton(), playerPokemon.getMove1().getButtonPressed());
		move1.setText(playerPokemon.getMove1().getName());
		movePanel.add(move1, "cell 0 0,grow");
		move2 = new CustomButton(playerPokemon.getMove2().getButton(), playerPokemon.getMove2().getButtonPressed());
		move2.setText(playerPokemon.getMove2().getName());
		movePanel.add(move2, "cell 1 0,grow");
		move3 = new CustomButton(playerPokemon.getMove3().getButton(), playerPokemon.getMove3().getButtonPressed());
		move3.setText(playerPokemon.getMove3().getName());
		movePanel.add(move3, "cell 0 1,grow");
		move4 = new CustomButton(playerPokemon.getMove4().getButton(), playerPokemon.getMove4().getButtonPressed());
		move4.setText(playerPokemon.getMove4().getName());
		movePanel.add(move4, "cell 1 1, grow");

		moveInfo = new MoveInfoPanel(playerPokemon);

		fightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				moveSelection();
			}
		});

		move1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(playerPokemon.getMove1());

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				moveInfo.updateMove(playerPokemon.getMove1());
			}
		});
		move2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(playerPokemon.getMove2());

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				moveInfo.updateMove(playerPokemon.getMove2());
			}
		});
		move3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(playerPokemon.getMove3());

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				moveInfo.updateMove(playerPokemon.getMove3());
			}
		});
		move4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(playerPokemon.getMove4());

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				moveInfo.updateMove(playerPokemon.getMove4());
			}
		});
		
	setVisible(true);
	}
		
	private void enemyMove() {
		Random random = new Random();
		int randomMove = random.nextInt(4);

		switch (randomMove) {
		case 0:
			enemyPokemon.getMove1().useMove(enemyPokemon, playerPokemon);
			break;
		case 1:
			enemyPokemon.getMove2().useMove(enemyPokemon, playerPokemon);
			break;
		case 2:
			enemyPokemon.getMove3().useMove(enemyPokemon, playerPokemon);
			break;
		case 3:
			enemyPokemon.getMove4().useMove(enemyPokemon, playerPokemon);
			break;
		default:
			break;
		}
		if (isGameOver()) {
			ApplicationData.eventQueue.add(() -> gameOver());
		} else {
			ApplicationData.eventQueue.add(() -> actionSelection());
		}

		ApplicationData.eventQueue.pop().run();

	}

	private void playerMove(PokemonMove move) {
		ApplicationData.animate.stopAnimation();

		move.useMove(playerPokemon, enemyPokemon);
		if (enemyPokemon.getCurrentHp() <= 0) {
			gameOver = true;
			
		}
		bottomPanel.removeAll();
		bottomPanel.setLayout(new MigLayout("", "[100%,grow]", "[100%,grow]"));
		textPanel.remove(moveInfo);
		textPanel.add(textAreaPanel, "cell 0 0, span, grow");
		bottomPanel.add(textPanel, "grow");
		repaint();
		revalidate();

		if (isGameOver()) {
			ApplicationData.eventQueue.add(() -> gameOver());
		} else {
			ApplicationData.eventQueue.add(() -> enemyMove());
		}
		ApplicationData.eventQueue.pop().run();
	}

	public boolean isGameOver() {
		return (playerPokemon.getCurrentHp() <= 0 || enemyPokemon.getCurrentHp() <= 0);
	}

	private void gameOver() {
		ApplicationData.soundtrack.stop();
		if (playerPokemon.getCurrentHp() <= 0) {
			topPanel.remove(playerPokemon.getStatusPanel());
			topPanel.remove(playerPokemon.getSpritePanel());
			ApplicationData.sfx.playFile(5);
			ApplicationData.animate.textAnimation("Your " + playerPokemon.getName() + " has fainted!");
			ApplicationData.animate.addFadeAnimation();
			
		} else if (enemyPokemon.getCurrentHp() <= 0) {
			topPanel.remove(enemyPokemon.getStatusPanel());
			topPanel.remove(enemyPokemon.getSpritePanel());
			ApplicationData.animate.textAnimation("The enemy " + enemyPokemon.getName() + " has fainted!");
			
			ApplicationData.soundtrack.playFile(4);
		}
		System.out.println("Game Over");
		
		topPanel.repaint();
		topPanel.revalidate();
		ApplicationData.eventQueue.add(()->ApplicationData.switchPanel(ApplicationData.battlePanel, ApplicationData.endPanel));
	}

	private void moveSelection() {
		turnNumber++;
		bottomPanel.removeAll();
		bottomPanel.setLayout(new MigLayout("gap 0", "[74.5%,grow]1%[24.5%:24.5%:,grow]", "[100%,grow]"));
		textPanel.remove(textAreaPanel);
		textPanel.add(moveInfo, "cell 1 0");
		moveInfo.updateMove();
		bottomPanel.add(movePanel, "cell 0 0, grow");
		bottomPanel.add(textPanel, "cell 1 0, grow");
		bottomPanel.repaint();
		bottomPanel.revalidate();
	}

	private void actionSelection() {
		bottomPanel.removeAll();
		bottomPanel.setLayout(new MigLayout("", "[49%,grow]1%[49%,grow]", "[::100%,grow]"));
		ApplicationData.animate.textAnimation("What will \n" + playerPokemon.getName() + " do?");
		textPanel.add(textAreaPanel, "cell 1 0 , grow");
		textPanel.remove(moveInfo);
		bottomPanel.add(textPanel, "cell 0 0, grow");
		bottomPanel.add(actionPanel, "cell 1 0, grow");
		bottomPanel.repaint();
		bottomPanel.revalidate();
	}

	/*
	 * private void updateAllyStatuses() { if (playerPokemon.getStatuses()==null) {
	 * allyStatus.setText("NOrmal"); System.out.println("normal"); return; } for
	 * (Map.Entry<Status, Integer> entry : playerPokemon.getStatuses().entrySet()) {
	 * switch(entry.getKey()) { case ASLEEP: break; case NORMAL:
	 * 
	 * break; case PARALYZED:
	 * 
	 * allyStatus.setText( entry.getKey().toString()+" " + entry.getValue());
	 * allyStatus.setForeground(Color.pink); break; case POISONED: break;
	 * 
	 * 
	 * default: allyStatus.setText("NOrmal"); break;
	 * 
	 * }
	 * 
	 * System.out.println(entry.getKey() +" "+ entry.getValue());
	 * 
	 * } repaint(); ApplicationData.eventQueue.pop().run(); }
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(0,0,0, fadeSat));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	

}
