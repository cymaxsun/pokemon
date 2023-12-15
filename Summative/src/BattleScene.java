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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.swing.MigLayout;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
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

	private Image panelBkg = ImageIO.read(new File("resources/btnbkg.png"));
	private Image myImage = ImageIO.read(new File("resources/bkg.png"));
	private BackgroundPanel battleFrame;
	// private JPanel battleFrame;
	private Brian brian;
	private Brian enemyBrian;

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
		enemyBrian = new Brian();

		setTitle("Pokemon");

		setBounds(100, 100, 1366, 768);
		setContentPane(battleFrame);
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("insets 0, gapy 0, gapx 0",
				"[35%:n:35%,grow][::15%,grow][::15%,grow][100px:100px:35%,grow]",
				"[grow][20%:n:20%,grow][15%:n,grow][5%:n][22.57%:n:20%,grow][grow][30%:n:30%,grow,fill]"));

		JPanel enemyInfo = new JPanel();
		enemyInfo.setPreferredSize(new Dimension(150, 50));
		enemyInfo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(enemyInfo, "cell 0 1,grow");
		enemyInfo.setLayout(
				new MigLayout("insets 15 15 15 15, gapy 5", "[80px][][70%,grow]", "[14px][:15%:15%,grow][grow]"));

		JLabel enemyName = new JLabel(enemyBrian.name);
		enemyName.setHorizontalAlignment(SwingConstants.CENTER);
		enemyInfo.add(enemyName, "cell 0 0 2 1,grow");

		JLabel enemyLvl = new JLabel("Lv. " + enemyBrian.lvl);
		enemyLvl.setHorizontalAlignment(SwingConstants.CENTER);
		enemyInfo.add(enemyLvl, "cell 2 0,alignx right,aligny bottom");

		JPanel enemyHpPanel = new JPanel();
		enemyHpPanel.setBackground(new Color(94, 94, 104));
		enemyInfo.add(enemyHpPanel, "cell 1 1 2 1,grow");
		enemyHpPanel.setLayout(new MigLayout("insets 0, gapx 0", "[:1%:1%,grow][][:1%:1%,grow][18px,grow][::1%,grow]",
				"[::1%,grow][98%,grow][::1%,grow]"));

		JLabel enemyHpLbl = new JLabel("HP");
		enemyHpLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		enemyHpLbl.setForeground(Color.ORANGE);
		enemyHpPanel.add(enemyHpLbl, "cell 1 0 1 3,alignx center,growy");
		enemyHpLbl.setFont(new Font("Tahoma", Font.BOLD, 16));

		JProgressBar enemyHpBar = new JProgressBar();
		enemyHpBar.setForeground(Color.GREEN);
		enemyHpBar.setBackground(new Color(94, 94, 104));
		enemyHpBar.setValue(enemyBrian.currentHp / enemyBrian.maxHp * 100);
		enemyHpBar.setBorderPainted(false);
		enemyHpPanel.add(enemyHpBar, "cell 3 1,growx,aligny center");
		URL url = this.getClass().getResource("image.png");
		ImageIcon icon = new ImageIcon(url);
		Image image = icon.getImage().getScaledInstance(150, 155, Image.SCALE_SMOOTH);

		JLabel enemyPoke = new JLabel("");
		enemyPoke.setVerticalAlignment(SwingConstants.CENTER);
		enemyPoke.setHorizontalAlignment(SwingConstants.CENTER);
		enemyPoke.setIcon(new ImageIcon(image));
		getContentPane().add(enemyPoke, "cell 2 1 2 2,alignx center,aligny bottom");

		JLabel allyPoke = new JLabel("");
		allyPoke.setHorizontalAlignment(SwingConstants.CENTER);
		allyPoke.setVerticalAlignment(SwingConstants.CENTER);

		allyPoke.setIcon(new ImageIcon(image));
		allyPoke.setVerticalAlignment(SwingConstants.CENTER);
		allyPoke.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(allyPoke, "flowx,cell 0 3 2 2,alignx center,growy");

		JPanel allyInfo = new JPanel();
		allyInfo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().add(allyInfo, "cell 3 4,grow");
		allyInfo.setLayout(
				new MigLayout("insets 15 15 15 15, gapy 5", "[80px][][70%,grow]", "[14px][:15%:15%,grow][grow]"));

		JLabel allyName = new JLabel(brian.name);
		allyName.setHorizontalAlignment(SwingConstants.CENTER);
		allyInfo.add(allyName, "cell 0 0,growx,aligny bottom");

		JLabel allyLvl = new JLabel("Lv. " + brian.lvl);
		allyLvl.setHorizontalAlignment(SwingConstants.CENTER);
		allyInfo.add(allyLvl, "cell 2 0,alignx right,aligny bottom");

		JPanel allyHpPanel = new JPanel();
		allyHpPanel.setBackground(new Color(94, 94, 104));
		allyInfo.add(allyHpPanel, "cell 1 1 2 1,grow");
		allyHpPanel.setLayout(new MigLayout("insets 0, gapx 0", "[:1%:1%,grow][][:1%:1%,grow][18px,grow][::1%,grow]",
				"[::1%,grow][98%,grow][::1%,grow]"));

		JLabel allyHpLbl = new JLabel("HP");
		allyHpLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		allyHpLbl.setForeground(Color.ORANGE);
		allyHpLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		allyHpPanel.add(allyHpLbl, "cell 1 0 1 3,alignx center,growy");

		JProgressBar allyHpBar = new JProgressBar();
		allyHpBar.setForeground(Color.GREEN);
		allyHpBar.setBackground(new Color(94, 94, 104));
		allyHpBar.setValue(brian.currentHp / brian.maxHp * 100);
		allyHpBar.setBorderPainted(false);
		allyHpPanel.add(allyHpBar, "cell 3 1,growx,aligny center");

		ImagePanel bottomPanel = new ImagePanel(panelBkg);
		// JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(255, 255, 255));
		bottomPanel.setBorder(new CompoundBorder(new MatteBorder(5, 0, 0, 0, (Color) new Color(0, 0, 0)),
				new MatteBorder(5, 0, 5, 0, (Color) new Color(130, 212, 126))));
		getContentPane().add(bottomPanel, "cell 0 6 4 1,grow");
		bottomPanel.setLayout(new MigLayout("", "[49%,grow]1%[49%,grow]", "[::100%,grow]"));

		JPanel actionPanel = new JPanel();
		actionPanel.setOpaque(false);
		actionPanel.setLayout(
				new MigLayout("insets 0, gap 0", "[50%,grow][50%,grow]", "[:50%:50%,grow,fill][:50%:50%,grow]"));
		bottomPanel.add(actionPanel, "cell 1 0,grow");

		JPanel textPanel = new JPanel();
		textPanel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 7, true),
				new LineBorder(new Color(221, 195, 104), 2, true)));
		textPanel.setBackground(new Color(96, 96, 104));
		bottomPanel.add(textPanel, "cell 0 0 1 2,grow");
		textPanel.setLayout(new MigLayout("gapx 0", "[2.5%:n:2.5%][95%,grow][2.5%:n:2.5%]", "[::100%,grow]"));

		JPanel textAreaPanel = new JPanel();
		textAreaPanel.setOpaque(false);
		textPanel.add(textAreaPanel, "cell 1 0,grow");
		textAreaPanel.setLayout(new MigLayout("insets 0 10 0 10", "[::100%,grow]", "[::100%,grow]"));

		JTextArea textBox = new JTextArea();
		textBox.setLineWrap(true);
		textBox.setMinimumSize(new Dimension(0, 0));
		textBox.setColumns(10);
		textBox.setText("What will \n" + brian.name + " do?");
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

		JButton fightButton = new JButton();
		fightButton.setBorder(null);
		resizeButtonIcon(fightButton, "fight.png");
		fightButton.setContentAreaFilled(false);
		actionPanel.add(fightButton, "cell 0 0,alignx right,growy");

		JButton bagButton = new JButton();
		bagButton.setBorder(null);
		resizeButtonIcon(bagButton, "bag.png");
		bagButton.setContentAreaFilled(false);
		actionPanel.add(bagButton, "cell 1 0,alignx left,growy");

		JButton pokemonButton = new JButton();
		pokemonButton.setBorder(null);
		resizeButtonIcon(pokemonButton, "pokemon.png");
		pokemonButton.setContentAreaFilled(false);
		actionPanel.add(pokemonButton, "cell 0 1,alignx right,growy");

		JButton runButton = new JButton();
		runButton.setBorder(null);
		resizeButtonIcon(runButton, "run.png");
		runButton.setContentAreaFilled(false);
		actionPanel.add(runButton, "cell 1 1,alignx left,growy");

		battleFrame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resizeButtonIcon(fightButton, "fight.png");
				resizeButtonIcon(bagButton, "bag.png");
				resizeButtonIcon(runButton, "run.png");
				resizeButtonIcon(pokemonButton, "pokemon.png");
			}
		});

		JPanel movePanel = new JPanel();
		movePanel.setLayout(
				new MigLayout("insets 0, gap 0", "[50%,grow][50%,grow]", "[:50%:50%,grow,fill][:50%:50%,grow]"));
		movePanel.setBackground(Color.red);

		JButton move1 = new JButton(brian.move1.name);
		movePanel.add(move1, "cell 0 0,grow");
		JButton move2 = new JButton("move2");
		movePanel.add(move2, "cell 1 0,grow");
		JButton move3 = new JButton("move3");
		movePanel.add(move3, "cell 0 1,grow");
		JButton move4 = new JButton("move4");
		movePanel.add(move4, "cell 1 1, grow");

		JPanel abilityInfo = new JPanel();
		abilityInfo.setLayout(new MigLayout("insets 15 40 15 40, gapx 0", "[100%,grow]", "[50%,grow][50%,grow]"));
		abilityInfo.setBackground(Color.WHITE);

		JLabel moveType = new JLabel(brian.type);
		moveType.setFont(moveType.getFont().deriveFont(Font.PLAIN, 40));
		moveType.setBackground(Color.DARK_GRAY);
		moveType.setForeground(Color.WHITE);
		moveType.setHorizontalAlignment(SwingConstants.CENTER);
		moveType.setOpaque(true);
		abilityInfo.add(moveType, "cell 0 0, grow");

		JTextField movePP = new JTextField("PP:" + brian.move1.charges + "/" + brian.move1.maxCharges);
		movePP.setBorder(new MatteBorder(10, 0, 10, 0, Color.white));
		movePP.setFont(moveType.getFont().deriveFont(Font.PLAIN, 40));
		movePP.setHorizontalAlignment(SwingConstants.CENTER);

		abilityInfo.add(movePP, "cell 0 1, grow");

		fightButton.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isPressed()) {
					resizeButtonIcon(fightButton, "fightPressed.png");
				}
			}
		});
		fightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				bottomPanel.removeAll();
				bottomPanel.setLayout(new MigLayout("gap 0", "[74.5%,grow]1%[24.5%,grow]", "[100%,grow]"));
				textPanel.remove(textAreaPanel);
				textPanel.add(abilityInfo, "cell 1 0,grow");
				bottomPanel.add(movePanel, "cell 0 0, grow");
				bottomPanel.add(textPanel, "cell 1 0, grow");
				bottomPanel.repaint();
				bottomPanel.revalidate();

			}
		});

		bagButton.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();

				if (model.isPressed()) {
					resizeButtonIcon(bagButton, "bagPressed.png");
				}
			}
		});

		pokemonButton.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isPressed()) {
					resizeButtonIcon(pokemonButton, "pokemonPressed.png");
				}
			}
		});

		runButton.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ButtonModel model = (ButtonModel) e.getSource();
				if (model.isPressed()) {
					resizeButtonIcon(runButton, "runPressed.png");
				}
			}
		});

		enemyHpBar.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (enemyHpBar.getValue() <= 0) {
					textAnimation(textBox, "The enemy " + enemyBrian.name + " has fainted.", null);
					for (KeyListener k: getKeyListeners()) {
						removeKeyListener(k);
					}
				} else if (enemyHpBar.getValue() <= 25) {
					enemyHpBar.setForeground(Color.red);
				} else if (enemyHpBar.getValue() <= 50) {
					enemyHpBar.setForeground(Color.yellow);
				}
			}
		});

		allyHpBar.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (enemyHpBar.getValue() <= 0) {
					textAnimation(textBox, "Your " + brian.name + " has fainted.", null);
					for (KeyListener k: getKeyListeners()) {
						removeKeyListener(k);
					}
				} else if (allyHpBar.getValue() <= 25) {
					allyHpBar.setForeground(Color.red);
				} else if (allyHpBar.getValue() <= 50) {
					allyHpBar.setForeground(Color.yellow);
				}
			}
		});

		move1.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			}
		});

		move1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				brian.move1(enemyBrian);
				PokemonMove enemyMove = enemyMove(enemyBrian, brian);

				movePP.setText("PP:" + brian.move1.charges + "/20");

				bottomPanel.removeAll();
				bottomPanel.setLayout(new MigLayout("", "[100%,grow]", "[100%,grow]"));
				textPanel.remove(abilityInfo);
				textPanel.add(textAreaPanel, "cell 0 0, span, grow");
				bottomPanel.add(textPanel, "grow");

				textAnimation(textBox, brian.name + " used " + brian.move1.name + "!", ()->hpAnimation(enemyHpBar, enemyBrian));
				addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							// If Enter key is pressed
							textAnimation(textBox, "The enemy " + enemyBrian.name + " used " + enemyBrian.move1.name + "!", ()->hpAnimation(allyHpBar, brian));
							removeKeyListener(this);
						}
					}
				});
			
				
				repaint();
				revalidate();

			}
		});

	}

	private void resizeButtonIcon(JButton button, String path) {
		URL url = this.getClass().getResource(path);
		ImageIcon icon = new ImageIcon(url);
		Image image = icon.getImage().getScaledInstance(getWidth() * 330 / 1366, getHeight() * 95 / 768,
				Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(image));
	}

	private PokemonMove enemyMove(Pokemon attacker, Pokemon target) {
		Random random = new Random();

		int randomMove = random.nextInt(4);
		randomMove = 0;

		switch (randomMove) {
		case 0:
			attacker.move1(target);
			return attacker.move1;
		case 1:
			attacker.move2(target);
			return attacker.move2;
		case 2:
			attacker.move3(target);
			return attacker.move3;
		case 3:
			attacker.move4(target);
			return attacker.move4;
		}
		return null;
	}

	private void textAnimation(JTextComponent c, String string, Runnable onAnimationEnd) {
		c.setText("");
		Timer textAnimation = new Timer(50, new ActionListener() {
			int index = 0;

			public void actionPerformed(ActionEvent e) {
				if (index <= string.length()) {
					c.setText(string.substring(0, index));
					index++;
				} else {
					((Timer) e.getSource()).stop();
					if (onAnimationEnd != null) {
                        onAnimationEnd.run();
                    }
				}
			}
		});

		textAnimation.start();
	}

	private void hpAnimation(JProgressBar p, Pokemon pokemon) {
		Timer hpBarAnimation = new Timer(25, new ActionListener() {
			int value = p.getValue();

			public void actionPerformed(ActionEvent e) {
				value = (value - 1); // Increment the progress value
				if (value < 0 || value <= (0.0 + pokemon.currentHp) / pokemon.maxHp * 100) {
					((Timer) e.getSource()).stop(); // Stop the timer when reaching 50
				}

				p.setValue(value);

			}
		});
		hpBarAnimation.start();
	}
}
