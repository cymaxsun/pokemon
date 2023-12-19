import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

public class BattleScene extends JFrame {

	private Image panelBkg = new ImageIcon("resources/btnbkg.png").getImage();
	private Image fightBackground = new ImageIcon("resources/bkg.png").getImage();
	private Image fight = new ImageIcon("resources/fight.png").getImage();
	private Image fightPressed = new ImageIcon("resources/fightPressed.png").getImage();
	private Image bag = new ImageIcon("resources/bag.png").getImage();
	private Image bagPressed = new ImageIcon("resources/bagPressed.png").getImage();
	private Image pokemon = new ImageIcon("resources/pokemon.png").getImage();
	private Image pokemonPressed = new ImageIcon("resources/pokemonPressed.png").getImage();
	private Image run = new ImageIcon("resources/run.png").getImage();
	private Image runPressed = new ImageIcon("resources/runPressed.png").getImage();
	private JPanel battleFrame;
	private Brian allyPokemon;
	private Brian enemyPokemon;
	private JTextArea textBox;
	private JButton move1, move2, move3, move4;
	private CustomButton fightButton, bagButton, pokemonButton, runButton;
	private JProgressBar allyHpBar, enemyHpBar;
	private JLabel allyHpLbl, enemyHpLbl, allyName, allyLvl, enemyName, enemyLvl, moveType, movePP, allyPoke, enemyPoke;
	private JPanel movePanel, abilityInfo, enemyInfo, enemyHpPanel, allyHpPanel, allyInfo, textPanel, actionPanel,
			textAreaPanel;
	private ImagePanel bottomPanel, topPanel;
	private Timer textAnimation, hpBarAnimation;
	private LinkedList<Runnable> eventQueue = new LinkedList<Runnable>();
	private boolean gameOver = false;
	private JLabel enemyHpValue;
	private JLabel enemyStatus;
	private JLabel allyStatus;
	private JLabel allyHpValue;
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
		battleFrame = new JPanel();
		allyPokemon = new Brian();
		enemyPokemon = new Brian(false);
		Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("pokemon.ttf"));
		
		
		setTitle("Pokemon");
		
		
		setBounds(0,0, ApplicationData.frameWidth, ApplicationData.frameHeight);

		setContentPane(battleFrame);
		getContentPane().setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new MigLayout("insets 0, gapy 0, gapx 0", "[50%,grow][50%, grow]", "[70%,grow][30%:n:30%,grow,fill]"));
		
		//JPanel topPanel = new JPanel();
		topPanel = new ImagePanel(fightBackground);
		topPanel.setLayout(new MigLayout("insets 0, gapy 0, gapx 0", "[35%,grow][15%,grow][15%,grow][35%,grow]", "[10%,grow][25%,grow][15%,grow][15%,grow][25%,grow][10%,grow]"));
		getContentPane().add(topPanel,"grow, span");
		

		enemyInfo = new JPanel();
		enemyInfo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		topPanel.add(enemyInfo, "cell 0 1,grow");
		enemyInfo.setLayout(
				new MigLayout("insets 15, gapy 0", "[80px][][70%,grow]", "[15%,grow][5%]5 [grow]"));

		enemyName = new JLabel(enemyPokemon.getName());
		enemyName.setPreferredSize(new Dimension(24, 40));
		enemyName.setFont(font.deriveFont(Font.PLAIN, 30));
		enemyName.setHorizontalAlignment(SwingConstants.CENTER);
		enemyInfo.add(enemyName, "cell 0 0 2 1,growx,aligny bottom");

		enemyLvl = new JLabel("Lv. " + enemyPokemon.getLvl());
		enemyLvl.setPreferredSize(new Dimension(30, 25));
		enemyLvl.setFont(font.deriveFont(Font.PLAIN, 15));
		enemyLvl.setHorizontalAlignment(SwingConstants.CENTER);
		enemyInfo.add(enemyLvl, "cell 2 0,alignx right,aligny bottom");
		
		enemyStatus = new JLabel("NORM");
		enemyInfo.add(enemyStatus, "cell 0 1,alignx center");

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
		enemyHpBar.setValue(enemyPokemon.getCurrentHp() / enemyPokemon.getMaxHp() * 100);
		enemyHpBar.setBorderPainted(false);
		enemyHpPanel.add(enemyHpBar, "cell 3 1,grow");
		
		enemyHpValue = new JLabel(enemyPokemon.getCurrentHp() + "/" + enemyPokemon.getMaxHp());
		enemyHpValue.setFont(font.deriveFont(Font.BOLD, 15));
		enemyHpValue.setPreferredSize(new Dimension(30, 25));
		enemyInfo.add(enemyHpValue, "cell 2 2,alignx center,aligny top");
		URL url = this.getClass().getResource("image.png");
		ImageIcon icon = new ImageIcon(url);
		Image image = icon.getImage().getScaledInstance(150, 155, Image.SCALE_SMOOTH);

		enemyPoke = new JLabel("");
		enemyPoke.setVerticalAlignment(SwingConstants.CENTER);
		enemyPoke.setHorizontalAlignment(SwingConstants.CENTER);
		enemyPoke.setIcon(new ImageIcon(image));
		topPanel.add(enemyPoke, "cell 2 1 2 3,alignx center,aligny center");

		allyPoke = new JLabel("");
		allyPoke.setHorizontalAlignment(SwingConstants.CENTER);
		allyPoke.setVerticalAlignment(SwingConstants.CENTER);

		allyPoke.setIcon(new ImageIcon(image));
		allyPoke.setVerticalAlignment(SwingConstants.CENTER);
		allyPoke.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(allyPoke, "flowx,cell 0 4 2 2,growx,aligny center");

		allyInfo = new JPanel();
		allyInfo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		topPanel.add(allyInfo, "cell 3 4,grow");
		allyInfo.setLayout(
				new MigLayout("insets 15, gapy 0", "[80px][][70%,grow]", "[15%,grow][5%]5 [grow]"));

		allyName = new JLabel(allyPokemon.getName());
		allyName.setPreferredSize(new Dimension(24, 40));
		allyName.setFont(font.deriveFont(Font.PLAIN, 30));
		allyName.setHorizontalAlignment(SwingConstants.CENTER);
		allyInfo.add(allyName, "cell 0 0,growx,aligny bottom");

		allyLvl = new JLabel("Lv. " + allyPokemon.getLvl());
		allyLvl.setPreferredSize(new Dimension(30, 25));
		allyLvl.setFont(font.deriveFont(Font.PLAIN, 15));
		allyLvl.setHorizontalAlignment(SwingConstants.CENTER);
		allyInfo.add(allyLvl, "cell 2 0,alignx right,aligny bottom");
		
		allyStatus = new JLabel("New label");
		allyInfo.add(allyStatus, "cell 0 1,alignx center");

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
		allyHpBar.setValue(allyPokemon.getCurrentHp() / allyPokemon.getMaxHp() * 100);
		allyHpBar.setBorderPainted(false);
		allyHpPanel.add(allyHpBar, "cell 3 1,growx,aligny center");
		
		allyHpValue = new JLabel(allyPokemon.getCurrentHp() + "/" + allyPokemon.getMaxHp());
		allyHpValue.setFont(font.deriveFont(Font.BOLD, 15));
		allyHpValue.setPreferredSize(new Dimension(30, 25));
		
		allyInfo.add(allyHpValue, "cell 2 2,alignx center,aligny top");

		bottomPanel = new ImagePanel(panelBkg);
		//JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(255, 255, 255));
		bottomPanel.setBorder(new CompoundBorder(new MatteBorder(5, 0, 0, 0, (Color) new Color(0, 0, 0)),
				new MatteBorder(5, 0, 5, 0, (Color) new Color(130, 212, 126))));
		getContentPane().add(bottomPanel, "cell 0 1 4 1,grow");
		bottomPanel.setLayout(new MigLayout("", "[49%,grow]1%[49%,grow]", "[::100%,grow]"));

		actionPanel = new JPanel();
		actionPanel.setOpaque(false);
		actionPanel.setLayout(
				new MigLayout("insets 0, gap 0", "[50%,grow][50%,grow]", "[50%,grow,fill][50%,grow]"));
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
		textBox.setText("What will \n" + allyPokemon.getName() + " do?");
		textBox.setMargin(new Insets(25, 35, 15, 35));
		textBox.setForeground(new Color(94, 94, 104));
		textBox.setEditable(false);
		Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
		// attributes.put(TextAttribute.TRACKING, -0.18);
		attributes.put(TextAttribute.SIZE, getHeight()/15);
		// attributes.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);

		textBox.setFont(font.deriveFont(Font.PLAIN, 55));
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

		move1 = new CustomButton(allyPokemon.getMove1().button,allyPokemon.getMove1().buttonPressed);
		move1.setText(allyPokemon.getMove1().name);
		movePanel.add(move1, "cell 0 0,grow");
		move2 = new CustomButton(allyPokemon.getMove2().button,allyPokemon.getMove2().buttonPressed);
		move2.setText(allyPokemon.getMove2().name);
		movePanel.add(move2, "cell 1 0,grow");
		move3 = new CustomButton(allyPokemon.getMove3().button,allyPokemon.getMove3().buttonPressed);
		move3.setText(allyPokemon.getMove3().name);
		movePanel.add(move3, "cell 0 1,grow");
		move4 = new CustomButton(allyPokemon.getMove4().button,allyPokemon.getMove4().buttonPressed);
		move4.setText(allyPokemon.getMove4().name);
		movePanel.add(move4, "cell 1 1, grow");

		abilityInfo = new JPanel();
		abilityInfo.setLayout(new MigLayout("insets 15 40 15 40, gapx 0", "[100%,grow]", "[50%,grow][50%,grow]"));
		abilityInfo.setBackground(Color.WHITE);

		moveType = new JLabel(allyPokemon.getType());
		moveType.setFont(moveType.getFont().deriveFont(Font.PLAIN, 40));
		moveType.setBackground(Color.DARK_GRAY);
		moveType.setForeground(Color.WHITE);
		moveType.setHorizontalAlignment(SwingConstants.CENTER);
		moveType.setOpaque(true);
		abilityInfo.add(moveType, "cell 0 0, grow");

		movePP = new JLabel("PP:" + allyPokemon.getMove1().charges + "/" + allyPokemon.getMove1().maxCharges);
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
				playerMove(enemyPokemon, allyPokemon.getMove1());

			}
		});
		move2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(enemyPokemon, allyPokemon.getMove2());

			}
		});
		move3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(enemyPokemon, allyPokemon.getMove3());

			}
		});
		move4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				playerMove(enemyPokemon, allyPokemon.getMove4());

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
				repaint();
			}
		});
		textAnimation.start();
	}

	private void hpAnimation(JProgressBar p, Pokemon pokemon) {
		hpBarAnimation = new Timer(25, new ActionListener() {
			int barValue = p.getValue();
			int intValue = (int) ((0.0+barValue)/100*pokemon.getMaxHp());
			int targetBarValue = (int) ((0.0 + pokemon.getCurrentHp() )/ pokemon.getMaxHp() * 100);
			int targetIntValue = (int) ((0.0+targetBarValue)/100*pokemon.getMaxHp());
			
			public void actionPerformed(ActionEvent e) {
				if (barValue <= 0 || barValue > 100 || barValue == targetBarValue){
					
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
					if (barValue > targetBarValue) {
						
						barValue -= 1;
						intValue -= pokemon.getMaxHp()/100;
					} else {
						barValue += 1;
						intValue += pokemon.getMaxHp()/100;
					}
					
				}  
				p.setValue(barValue);
				if (pokemon.isAllied()) {
					allyHpValue.setText(intValue + "/" + pokemon.getMaxHp());
				} else {
					enemyHpValue.setText(intValue + "/" + pokemon.getMaxHp());
				}
				repaint();

			}
		});
		hpBarAnimation.start();
	}

	private void enemyMove() {
		Random random = new Random();
		ArrayList<String> displayText = new ArrayList<String>();
		int randomMove = random.nextInt(4);
		randomMove = 0;
		switch (randomMove) {
		case 0:
			displayText = enemyPokemon.getMove1().useMove(enemyPokemon, allyPokemon);
			break;
		case 1:
			displayText = enemyPokemon.getMove2().useMove(enemyPokemon, allyPokemon);
			break;
		case 2:
			displayText = enemyPokemon.getMove3().useMove(enemyPokemon, allyPokemon);
			break;
		case 3:
			displayText = enemyPokemon.getMove4().useMove(enemyPokemon, allyPokemon);
			break;
		default:
			break;
		}
		if (allyPokemon.getCurrentHp()<=0) {
			gameOver = true;
		}
		final ArrayList<String> finalDisplayText = displayText;
		
		for (int i = 0; i < finalDisplayText.size(); i++) {
			String s = finalDisplayText.get(i);
			if (i == 0) {
				eventQueue.add(() -> textAnimation(s));
				eventQueue.add(() -> hpAnimation(allyHpBar, allyPokemon));
				eventQueue.add(() -> hpAnimation(enemyHpBar, enemyPokemon));
			} else {
				eventQueue.add(() -> textAnimation(s));
			}
		}
		eventQueue.add(()->updateAllyStatuses());
		if (gameOver) {
			eventQueue.add(() -> textAnimation("Your " + allyPokemon.getName() + " has fainted."));
			eventQueue.add(() -> gameOver());
		} else {
			eventQueue.add(() -> actionSelection());
		}
		
		enemyPokemon.reduceStatusDurations();
		eventQueue.pop().run();
		
	}

	private void playerMove(Pokemon target, PokemonMove move) {
		if (textAnimation != null) {
			textAnimation.stop();
		}
		ArrayList<String> displayText = move.useMove(allyPokemon, enemyPokemon);
		if (enemyPokemon.getCurrentHp()<=0) {
			gameOver = true;
		}
		
		bottomPanel.removeAll();
		bottomPanel.setLayout(new MigLayout("", "[100%,grow]", "[100%,grow]"));
		textPanel.remove(abilityInfo);
		textPanel.add(textAreaPanel, "cell 0 0, span, grow");
		bottomPanel.add(textPanel, "grow");
		repaint();
		revalidate();
		
		for (int i = 0;i<displayText.size();i++) {
			String s = displayText.get(i);
			if (i==0) {
				eventQueue.add(() -> textAnimation(s));
				eventQueue.add(() -> hpAnimation(enemyHpBar, enemyPokemon));
				eventQueue.add(() -> hpAnimation(allyHpBar, allyPokemon));
			}else {
			eventQueue.add(() -> textAnimation(s));
			}
		}
		if (gameOver) {
			eventQueue.add(() -> textAnimation("The enemy " + enemyPokemon.getName() + " has fainted!"));
			eventQueue.add(() -> gameOver());
		} else {
			eventQueue.add(() -> enemyMove());
		}
		allyPokemon.reduceStatusDurations();
		eventQueue.pop().run();
	}

	private void gameOver() {
		if (allyPokemon.getCurrentHp() <= 0) {
			battleFrame.remove(allyInfo);
			
		} else if ( enemyPokemon.getCurrentHp() <= 0) {
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
		movePP.setText("PP:" + allyPokemon.getMove1().charges + "/" + allyPokemon.getMove1().maxCharges);
		bottomPanel.add(movePanel, "cell 0 0, grow");
		bottomPanel.add(textPanel, "cell 1 0, grow");
		bottomPanel.repaint();
		bottomPanel.revalidate();
	}

	private void actionSelection() {
		bottomPanel.removeAll();
		bottomPanel.setLayout(new MigLayout("", "[49%,grow]1%[49%,grow]", "[::100%,grow]"));
		textAnimation("What will \n" + allyPokemon.getName() + " do?");
		textPanel.add(textAreaPanel, "cell 1 0 , grow");
		textPanel.remove(abilityInfo);
		bottomPanel.add(textPanel, "cell 0 0, grow");
		bottomPanel.add(actionPanel, "cell 1 0, grow");
		bottomPanel.repaint();
		bottomPanel.revalidate();
	}

	private void updateAllyStatuses() {
		if (allyPokemon.getStatuses()==null) {
			allyStatus.setText("NOrmal");
			System.out.println("normal");
			return;
		}
		for (Map.Entry<Status, Integer> entry : allyPokemon.getStatuses().entrySet()) {
			switch(entry.getKey()) {
			case ASLEEP:
				break;
			case NORMAL:
				
				break;
			case PARALYZED:
				
				allyStatus.setText( entry.getKey().toString()+" " + entry.getValue());
				allyStatus.setForeground(Color.pink);
				break;
			case POISONED:
				break;
				
				
			default:
				allyStatus.setText("NOrmal");
				break;
			
			}
			
			System.out.println(entry.getKey() +" "+ entry.getValue());

		}
		repaint();
		eventQueue.pop().run();
	}
}
