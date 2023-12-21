package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import moves.PokemonMove;
import net.miginfocom.swing.MigLayout;
import pokemon.Pokemon;

public class BattleFrame extends JFrame {

	private Image panelBkg = new ImageIcon("res/backgrounds/btnbkg.png").getImage();
	private Image fightBackground = new ImageIcon("res/backgrounds/bkg.png").getImage();
	private Image fight = new ImageIcon("res/buttons/fight.png").getImage();
	private Image fightPressed = new ImageIcon("res/buttons/fightPressed.png").getImage();
	private Image bag = new ImageIcon("res/buttons/bag.png").getImage();
	private Image bagPressed = new ImageIcon("res/buttons/bagPressed.png").getImage();
	private Image pokemon = new ImageIcon("res/buttons/pokemon.png").getImage();
	private Image pokemonPressed = new ImageIcon("res/buttons/pokemonPressed.png").getImage();
	private Image run = new ImageIcon("res/buttons/run.png").getImage();
	private Image runPressed = new ImageIcon("res/buttons/runPressed.png").getImage();
	public JPanel battlePanel;
	public JTextArea textBox;
	public JButton move1, move2, move3, move4;
	public CustomButton fightButton, bagButton, pokemonButton, runButton;
	public JLabel allyPoke, enemyPoke, movePP, moveType;
	public JPanel movePanel, abilityInfo, textPanel, actionPanel, textAreaPanel;
	public ImagePanel bottomPanel, topPanel;
	public LinkedList<Runnable> eventQueue = new LinkedList<Runnable>();
	public boolean gameOver = false;
	public Pokemon enemyPokemon, playerPokemon;

	private Graphics2D g2;

	// GAME STATE
	public int gameState;
	public final int playState = 0;
	public final int pauseState = 1;

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public BattleFrame() {
		setFocusable(true);
		setResizable(false);
		battlePanel = new JPanel();
		playerPokemon = ApplicationData.playerPokemon;
		enemyPokemon = ApplicationData.enemyPokemon;
		ApplicationData.animate = new AnimationHandler(this);

		
		

		setTitle("Pokemon");

		setBounds(0, 0, ApplicationData.frameWidth, ApplicationData.frameHeight);

		setContentPane(battlePanel);
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(
				new MigLayout("insets 0, gapy 0, gapx 0", "[50%,grow][50%, grow]", "[70%,grow][30%:n:30%,grow,fill]"));

		// JPanel topPanel = new JPanel();
		topPanel = new ImagePanel(fightBackground);
		topPanel.setLayout(new MigLayout("insets 0, gapy 0, gapx 0", "[35%,grow][15%,grow][15%,grow][35%,grow]",
				"[10%,grow][25%,grow][15%,grow][15%,grow][25%,grow][10%,grow]"));
		getContentPane().add(topPanel, "grow, span");

		topPanel.add(enemyPokemon.getInfoPanel(), "cell 0 1,grow");

		enemyPoke = new JLabel();
		enemyPoke.setVerticalAlignment(SwingConstants.CENTER);
		enemyPoke.setHorizontalAlignment(SwingConstants.CENTER);
		enemyPoke.setIcon(new ImageIcon(enemyPokemon.getImage()));
		topPanel.add(enemyPoke, "cell 2 1 2 3,alignx center,aligny center");

		allyPoke = new JLabel();
		allyPoke.setHorizontalAlignment(SwingConstants.CENTER);
		allyPoke.setVerticalAlignment(SwingConstants.CENTER);

		allyPoke.setIcon(new ImageIcon(enemyPokemon.getImage()));
		allyPoke.setVerticalAlignment(SwingConstants.CENTER);
		allyPoke.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(allyPoke, "flowx,cell 0 4 2 2,growx,aligny center");

		topPanel.add(playerPokemon.getInfoPanel(), "cell 3 4,grow");

		bottomPanel = new ImagePanel(panelBkg);
		// JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(255, 255, 255));
		bottomPanel.setBorder(new CompoundBorder(new MatteBorder(5, 0, 0, 0, (Color) new Color(0, 0, 0)),
				new MatteBorder(5, 0, 5, 0, (Color) new Color(130, 212, 126))));
		getContentPane().add(bottomPanel, "cell 0 1 4 1,grow");
		bottomPanel.setLayout(new MigLayout("", "[49%,grow]1%[49%,grow]", "[::100%,grow]"));

		actionPanel = new JPanel();
		actionPanel.setOpaque(false);
		actionPanel.setLayout(new MigLayout("insets 0, gap 0", "[50%,grow][50%,grow]", "[50%,grow,fill][50%,grow]"));
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
		textBox.setMargin(new Insets(25, 35, 15, 35));
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

		abilityInfo = new JPanel();
		abilityInfo.setLayout(new MigLayout("insets 15 40 15 40, gapx 0", "[100%,grow]", "[50%,grow][50%,grow]"));
		abilityInfo.setBackground(Color.WHITE);

		moveType = new JLabel(playerPokemon.getType());
		moveType.setFont(moveType.getFont().deriveFont(Font.PLAIN, 40));
		moveType.setBackground(Color.DARK_GRAY);
		moveType.setForeground(Color.WHITE);
		moveType.setHorizontalAlignment(SwingConstants.CENTER);
		moveType.setOpaque(true);
		abilityInfo.add(moveType, "cell 0 0, grow");

		movePP = new JLabel(
				"PP:" + playerPokemon.getMove1().getCharges() + "/" + playerPokemon.getMove1().getMaxCharges());
		movePP.setBorder(new MatteBorder(10, 0, 10, 0, Color.white));
		movePP.setFont(moveType.getFont().deriveFont(Font.PLAIN, 40));
		movePP.setHorizontalAlignment(SwingConstants.CENTER);

		abilityInfo.add(movePP, "cell 0 1, grow");

		ApplicationData.animate.entryAnimation(playerPokemon);

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
		});
		move2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(playerPokemon.getMove2());

			}
		});
		move3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(playerPokemon.getMove3());

			}
		});
		move4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(playerPokemon.getMove4());

			}
		});

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
			eventQueue.add(() -> gameOver());
		} else {
			eventQueue.add(() -> actionSelection());
		}

		enemyPokemon.updateStatuses();
		eventQueue.pop().run();

	}

	private void playerMove(PokemonMove move) {
		ApplicationData.animate.stopAnimation();

		move.useMove(playerPokemon, enemyPokemon);
		if (enemyPokemon.getCurrentHp() <= 0) {
			gameOver = true;
		}
		bottomPanel.removeAll();
		bottomPanel.setLayout(new MigLayout("", "[100%,grow]", "[100%,grow]"));
		textPanel.remove(abilityInfo);
		textPanel.add(textAreaPanel, "cell 0 0, span, grow");
		bottomPanel.add(textPanel, "grow");
		repaint();
		revalidate();

		if (isGameOver()) {
			eventQueue.add(() -> gameOver());
		} else {
			eventQueue.add(() -> enemyMove());
		}
		// playerPokemon.updateStatuses();
		eventQueue.pop().run();
	}

	public boolean isGameOver() {
		return (playerPokemon.getCurrentHp() <= 0 || enemyPokemon.getCurrentHp() <= 0);
	}

	private void gameOver() {
		if (playerPokemon.getCurrentHp() <= 0) {
			battlePanel.remove(playerPokemon.getInfoPanel());

		} else if (enemyPokemon.getCurrentHp() <= 0) {
			battlePanel.remove(enemyPokemon.getInfoPanel());

		}
		System.out.println("Game Over");
		repaint();
		revalidate();
	}

	private void moveSelection() {
		bottomPanel.removeAll();
		bottomPanel.setLayout(new MigLayout("gap 0", "[74.5%,grow]1%[24.5%,grow]", "[100%,grow]"));
		textPanel.remove(textAreaPanel);
		textPanel.add(abilityInfo, "cell 1 0,grow");
		movePP.setText("PP:" + playerPokemon.getMove1().getCharges() + "/" + playerPokemon.getMove1().getMaxCharges());
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
		textPanel.remove(abilityInfo);
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
	 * } repaint(); eventQueue.pop().run(); }
	 */

}
