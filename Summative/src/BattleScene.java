import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.swing.MigLayout;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Dimension;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;

import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Rectangle;

public class BattleScene extends JFrame {

	private Image panelBkg = new ImageIcon("resources/btnbkg.png").getImage();
	private Image myImage = new ImageIcon("resources/bkg.png").getImage();
	private Image fight = new ImageIcon("resources/fight.png").getImage();
	private Image fightPressed = new ImageIcon("resources/fightPressed.png").getImage();
	private Image bag = new ImageIcon("resources/bag.png").getImage();
	private Image bagPressed = new ImageIcon("resources/bagPressed.png").getImage();
	private Image pokemon = new ImageIcon("resources/pokemon.png").getImage();
	private Image pokemonPressed = new ImageIcon("resources/pokemonPressed.png").getImage();
	private Image run = new ImageIcon("resources/run.png").getImage();
	private Image runPressed = new ImageIcon("resources/runPressed.png").getImage();
	private BackgroundPanel battleFrame;
	// private JPanel battleFrame;
	private Brian brian;
	private Brian enemyBrian;
	private JTextArea textBox;
	private JButton move1, move2, move3, move4;
	private CustomButton fightButton, bagButton, pokemonButton, runButton;
	private JProgressBar allyHpBar, enemyHpBar;
	private JLabel allyHpLbl, enemyHpLbl, allyName, allyLvl, enemyName, enemyLvl, moveType, movePP, allyPoke, enemyPoke;
	private JPanel movePanel, abilityInfo, enemyInfo, enemyHpPanel, allyHpPanel, allyInfo, textPanel, actionPanel,
			textAreaPanel;
	private ImagePanel bottomPanel;
	private Timer textAnimation, hpBarAnimation;
	private LinkedList<Runnable> eventQueue = new LinkedList<Runnable>();
	private boolean gameOver = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationData.battleFrame = new BattleScene();
					ApplicationData.battleFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public BattleScene() throws IOException, FontFormatException {
		setFocusable(true);
		setResizable(true);
		battleFrame = new BackgroundPanel(myImage);
		// battleFrame = new JPanel();
		brian = new Brian();
		enemyBrian = new Brian(false);

		setTitle("Pokemon");

		setBounds(100, 100, 1366, 768);
		setContentPane(battleFrame);
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("insets 0, gapy 0, gapx 0",
				"[35%:n:35%,grow][::15%,grow][::15%,grow][100px:100px:35%,grow]",
				"[grow][20%:n:20%,grow][15%:n,grow][5%:n][22.57%:n:20%,grow][grow][30%:n:30%,grow,fill]"));

		enemyInfo = new JPanel();
		enemyInfo.setPreferredSize(new Dimension(150, 50));
		enemyInfo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(enemyInfo, "cell 0 1,grow");
		enemyInfo.setLayout(
				new MigLayout("insets 15 15 15 15, gapy 5", "[80px][][70%,grow]", "[14px][:15%:15%,grow][grow]"));

		enemyName = new JLabel(enemyBrian.getName());
		enemyName.setHorizontalAlignment(SwingConstants.CENTER);
		enemyInfo.add(enemyName, "cell 0 0 2 1,grow");

		enemyLvl = new JLabel("Lv. " + enemyBrian.getLvl());
		enemyLvl.setHorizontalAlignment(SwingConstants.CENTER);
		enemyInfo.add(enemyLvl, "cell 2 0,alignx right,aligny bottom");

		enemyHpPanel = new JPanel();
		enemyHpPanel.setBackground(new Color(94, 94, 104));
		enemyInfo.add(enemyHpPanel, "cell 1 1 2 1,grow");
		enemyHpPanel.setLayout(new MigLayout("insets 0, gapx 0", "[:1%:1%,grow][][:1%:1%,grow][18px,grow][::1%,grow]",
				"[::1%,grow][98%,grow][::1%,grow]"));

		enemyHpLbl = new JLabel("HP");
		enemyHpLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		enemyHpLbl.setForeground(Color.ORANGE);
		enemyHpPanel.add(enemyHpLbl, "cell 1 0 1 3,alignx center,growy");
		enemyHpLbl.setFont(new Font("Tahoma", Font.BOLD, 16));

		enemyHpBar = new JProgressBar();
		enemyHpBar.setForeground(Color.GREEN);
		enemyHpBar.setBackground(new Color(94, 94, 104));
		enemyHpBar.setValue(enemyBrian.getCurrentHp() / enemyBrian.getMaxHp() * 100);
		enemyHpBar.setBorderPainted(false);
		enemyHpPanel.add(enemyHpBar, "cell 3 1,growx,aligny center");
		URL url = this.getClass().getResource("image.png");
		ImageIcon icon = new ImageIcon(url);
		Image image = icon.getImage().getScaledInstance(150, 155, Image.SCALE_SMOOTH);

		enemyPoke = new JLabel("");
		enemyPoke.setVerticalAlignment(SwingConstants.CENTER);
		enemyPoke.setHorizontalAlignment(SwingConstants.CENTER);
		enemyPoke.setIcon(new ImageIcon(image));
		getContentPane().add(enemyPoke, "cell 2 1 2 2,alignx center,aligny bottom");

		allyPoke = new JLabel("");
		allyPoke.setHorizontalAlignment(SwingConstants.CENTER);
		allyPoke.setVerticalAlignment(SwingConstants.CENTER);

		allyPoke.setIcon(new ImageIcon(image));
		allyPoke.setVerticalAlignment(SwingConstants.CENTER);
		allyPoke.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(allyPoke, "flowx,cell 0 3 2 2,alignx center,growy");

		allyInfo = new JPanel();
		allyInfo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(allyInfo, "cell 3 4,grow");
		allyInfo.setLayout(
				new MigLayout("insets 15 15 15 15, gapy 5", "[80px][][70%,grow]", "[14px][:15%:15%,grow][grow]"));

		allyName = new JLabel(brian.getName());
		allyName.setHorizontalAlignment(SwingConstants.CENTER);
		allyInfo.add(allyName, "cell 0 0,growx,aligny bottom");

		allyLvl = new JLabel("Lv. " + brian.getLvl());
		allyLvl.setHorizontalAlignment(SwingConstants.CENTER);
		allyInfo.add(allyLvl, "cell 2 0,alignx right,aligny bottom");

		allyHpPanel = new JPanel();
		allyHpPanel.setBackground(new Color(94, 94, 104));
		allyInfo.add(allyHpPanel, "cell 1 1 2 1,grow");
		allyHpPanel.setLayout(new MigLayout("insets 0, gapx 0", "[:1%:1%,grow][][:1%:1%,grow][18px,grow][::1%,grow]",
				"[::1%,grow][98%,grow][::1%,grow]"));

		allyHpLbl = new JLabel("HP");
		allyHpLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		allyHpLbl.setForeground(Color.ORANGE);
		allyHpLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		allyHpPanel.add(allyHpLbl, "cell 1 0 1 3,alignx center,growy");

		allyHpBar = new JProgressBar();
		allyHpBar.setForeground(Color.GREEN);
		allyHpBar.setBackground(new Color(94, 94, 104));
		allyHpBar.setValue(brian.getCurrentHp() / brian.getMaxHp() * 100);
		allyHpBar.setBorderPainted(false);
		allyHpPanel.add(allyHpBar, "cell 3 1,growx,aligny center");

		bottomPanel = new ImagePanel(panelBkg);
		// JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(255, 255, 255));
		bottomPanel.setBorder(new CompoundBorder(new MatteBorder(5, 0, 0, 0, (Color) new Color(0, 0, 0)),
				new MatteBorder(5, 0, 5, 0, (Color) new Color(130, 212, 126))));
		getContentPane().add(bottomPanel, "cell 0 6 4 1,grow");
		bottomPanel.setLayout(new MigLayout("", "[49%,grow]1%[49%,grow]", "[::100%,grow]"));

		actionPanel = new JPanel();
		actionPanel.setOpaque(false);
		actionPanel.setLayout(
				new MigLayout("insets 0, gap 0", "[50%,grow][50%,grow]", "[:50%:50%,grow,fill][:50%:50%,grow]"));
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
		textBox.setColumns(10);
		textBox.setText("What will \n" + brian.getName() + " do?");
		textBox.setMargin(new Insets(15, 35, 15, 35));
		textBox.setForeground(new Color(94, 94, 104));
		textBox.setEditable(false);
		Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
		// attributes.put(TextAttribute.TRACKING, -0.18);
		attributes.put(TextAttribute.SIZE, 50f);
		// attributes.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);

		textBox.setFont(textBox.getFont().deriveFont(attributes));
		textBox.setBackground(Color.WHITE);
		textAreaPanel.add(textBox, "cell 0 0,grow");

		
		fightButton = new CustomButton(fight,fightPressed);
		actionPanel.add(fightButton, "cell 0 0,grow");

		bagButton = new CustomButton(bag,bagPressed);
		actionPanel.add(bagButton, "cell 1 0,grow");

		pokemonButton = new CustomButton(pokemon,pokemonPressed);
		actionPanel.add(pokemonButton, "cell 0 1,grow");

		runButton = new CustomButton(run,runPressed);
		actionPanel.add(runButton, "cell 1 1, grow");

		movePanel = new JPanel();
		movePanel.setLayout(
				new MigLayout("insets 0, gap 0", "[50%,grow][50%,grow]", "[:50%:50%,grow,fill][:50%:50%,grow]"));
		movePanel.setOpaque(false);

		move1 = new CustomButton(brian.getMove1().image,brian.getMove1().image );
		movePanel.add(move1, "cell 0 0,grow");
		move2 = new JButton(brian.getMove2().name);
		movePanel.add(move2, "cell 1 0,grow");
		move3 = new JButton(brian.getMove3().name);
		movePanel.add(move3, "cell 0 1,grow");
		move4 = new JButton(brian.getMove4().name);
		movePanel.add(move4, "cell 1 1, grow");

		abilityInfo = new JPanel();
		abilityInfo.setLayout(new MigLayout("insets 15 40 15 40, gapx 0", "[100%,grow]", "[50%,grow][50%,grow]"));
		abilityInfo.setBackground(Color.WHITE);

		moveType = new JLabel(brian.getType());
		moveType.setFont(moveType.getFont().deriveFont(Font.PLAIN, 40));
		moveType.setBackground(Color.DARK_GRAY);
		moveType.setForeground(Color.WHITE);
		moveType.setHorizontalAlignment(SwingConstants.CENTER);
		moveType.setOpaque(true);
		abilityInfo.add(moveType, "cell 0 0, grow");

		movePP = new JLabel("PP:" + brian.getMove1().charges + "/" + brian.getMove1().maxCharges);
		movePP.setBorder(new MatteBorder(10, 0, 10, 0, Color.white));
		movePP.setFont(moveType.getFont().deriveFont(Font.PLAIN, 40));
		movePP.setHorizontalAlignment(SwingConstants.CENTER);

		abilityInfo.add(movePP, "cell 0 1, grow");
		
		fightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				moveSelection();
			}
		});
		
		bagButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				moveSelection();
			}
		});

		enemyHpBar.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (enemyHpBar.getValue() <= 0) {

				} else if (enemyHpBar.getValue() <= 25) {
					enemyHpBar.setForeground(Color.red);
				} else if (enemyHpBar.getValue() <= 50) {
					enemyHpBar.setForeground(Color.yellow);
				}
			}
		});

		allyHpBar.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (allyHpBar.getValue() <= 0) {
				} else if (allyHpBar.getValue() <= 25) {
					allyHpBar.setForeground(Color.red);
				} else if (allyHpBar.getValue() <= 50) {
					allyHpBar.setForeground(Color.yellow);
				}
			}
		});

		move1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(enemyBrian, brian.getMove1());

			}
		});
		move2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(enemyBrian, brian.getMove2());

			}
		});
		move3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(enemyBrian, brian.getMove3());

			}
		});
		move4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(enemyBrian, brian.getMove4());

			}
		});

	}


	private void textAnimation(String string) {
		textBox.setText("");
		textAnimation = new Timer(50, new ActionListener() {
			int index = 0;

			public void actionPerformed(ActionEvent e) {
				if (index <= string.length()) {
					textBox.setText(string.substring(0, index));
					index++;
				} else {
					((Timer) e.getSource()).stop();
					if (eventQueue.peek() !=null) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						eventQueue.pop().run();
					}
				}
			}
		});
		textAnimation.start();
	}

	private void hpAnimation(JProgressBar p, Pokemon pokemon) {
		hpBarAnimation = new Timer(25, new ActionListener() {
			int value = p.getValue();

			public void actionPerformed(ActionEvent e) {
				if (value <= 0 || value <= (0.0 + pokemon.getCurrentHp()) / pokemon.getMaxHp() * 100) {
					// Increment the progress value
					((Timer) e.getSource()).stop();
					if (eventQueue.peek() !=null) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						eventQueue.pop().run();
					}
					
				} else {
					value = (value - 1);
				}
				p.setValue(value);

			}
		});
		hpBarAnimation.start();
	}

	private void enemyMove() {
		Random random = new Random();
		ArrayList<String> displayText = new ArrayList<String>();
		int randomMove = random.nextInt(4);

		switch (randomMove) {
		case 0:
			displayText = enemyBrian.getMove1().useMove(enemyBrian, brian);
			break;
		case 1:
			displayText = enemyBrian.getMove2().useMove(enemyBrian, brian);
			break;
		case 2:
			displayText = enemyBrian.getMove3().useMove(enemyBrian, brian);
			break;
		case 3:
			displayText = enemyBrian.getMove4().useMove(enemyBrian, brian);
			break;
		default:
			break;
		}
		if (brian.getCurrentHp()<=0) {
			gameOver = true;
		}
		final ArrayList<String> finalDisplayText = displayText;
		eventQueue.add(() -> textAnimation(finalDisplayText.get(0)));
		eventQueue.add(() -> hpAnimation(allyHpBar, brian));
		for (int i = 1; i < finalDisplayText.size(); i++) {
			String s = finalDisplayText.get(i);
			eventQueue.add(() -> textAnimation(s));
		}
		if (gameOver) {
			eventQueue.add(() -> textAnimation("Your " + brian.getName() + " has fainted."));
			eventQueue.add(() -> gameOver());
		} else {
			eventQueue.add(() -> actionSelection());
		}
		eventQueue.pop().run();
	}

	private void playerMove(Pokemon target, PokemonMove move) {
		if (textAnimation != null) {
			textAnimation.stop();
		}
		ArrayList<String> displayText = move.useMove(brian, enemyBrian);
		if (enemyBrian.getCurrentHp()<=0) {
			gameOver = true;
		}
		bottomPanel.removeAll();
		bottomPanel.setLayout(new MigLayout("", "[100%,grow]", "[100%,grow]"));
		textPanel.remove(abilityInfo);
		textPanel.add(textAreaPanel, "cell 0 0, span, grow");
		bottomPanel.add(textPanel, "grow");
		repaint();
		revalidate();
		eventQueue.add(() -> textAnimation(displayText.get(0)));
		eventQueue.add(() -> hpAnimation(enemyHpBar, enemyBrian));
		for (int i = 1;i<displayText.size();i++) {
			String s = displayText.get(i);
			eventQueue.add(() -> textAnimation(s));
		}
		if (gameOver) {
			eventQueue.add(() -> textAnimation("The enemy " + enemyBrian.getName() + " has fainted!"));
			eventQueue.add(() -> gameOver());
		} else {
			eventQueue.add(() -> enemyMove());
		}
		eventQueue.pop().run();
	}

	private void gameOver() {
		if (brian.getCurrentHp() <= 0) {
			battleFrame.remove(allyInfo);
			
		} else if ( enemyBrian.getCurrentHp() <= 0) {
			battleFrame.remove(enemyInfo);
			
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
		movePP.setText("PP:" + brian.getMove1().charges + "/" + brian.getMove1().maxCharges);
		bottomPanel.add(movePanel, "cell 0 0, grow");
		bottomPanel.add(textPanel, "cell 1 0, grow");
		bottomPanel.repaint();
		bottomPanel.revalidate();
	}

	private void actionSelection() {
		bottomPanel.removeAll();
		bottomPanel.setLayout(new MigLayout("", "[49%,grow]1%[49%,grow]", "[::100%,grow]"));
		textAnimation("What will \n" + brian.getName() + " do?");
		textPanel.add(textAreaPanel, "cell 1 0 , grow");
		textPanel.remove(abilityInfo);
		bottomPanel.add(textPanel, "cell 0 0, grow");
		bottomPanel.add(actionPanel, "cell 1 0, grow");
		bottomPanel.repaint();
		bottomPanel.revalidate();
	}

}
