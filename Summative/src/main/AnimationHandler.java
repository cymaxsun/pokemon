package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import pokemon.Pokemon;

public class AnimationHandler {

	private BattlePanel bf;
	public Timer hpAnimation;
	public Timer textAnimation;
	public Timer fadeToBlack;
	

	public AnimationHandler(BattlePanel bf) {
		this.bf = bf;
	}

	public void textAnimation(String string) {
		bf.textBox.setText("");
		textAnimation = new Timer(25, new ActionListener() {
			int index = 0;

			public void actionPerformed(ActionEvent e) {
				
				if (index <= string.length()) {
					bf.textBox.setText(string.substring(0, index));
					index++;

				} else {
					((Timer) e.getSource()).stop();
					if (ApplicationData.eventQueue.peek() != null) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ApplicationData.eventQueue.pop().run();
						
					}
				}
				bf.repaint();
			}
		});
		textAnimation.start();
	}

	public void hpAnimation(Pokemon pokemon) {
		//ApplicationData.sfx.playFile(4, 1.0f);
		hpAnimation = new Timer(5, new ActionListener() {
			int value = pokemon.getStatusPanel().hpBar.getValue();
			int targetValue = pokemon.getCurrentHp();

			public void actionPerformed(ActionEvent e) {
				
				if (value <= 0 || value > pokemon.getMaxHp() || value == targetValue) {

					((Timer) e.getSource()).stop();
					if (pokemon.getSpritePanel().damageTaken != null) {
						pokemon.getSpritePanel().damageTaken.stop();
						pokemon.getSpritePanel().tick = 0;
						pokemon.getSpritePanel().repaint();
					}
					
					if (ApplicationData.eventQueue.peek() != null) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ApplicationData.eventQueue.pop().run();
					}

				} else {
					if (value > targetValue) {

						value -= 1;
					} else {
						value += 1;
					}

				}
				pokemon.getStatusPanel().hpBar.setValue(value);
				pokemon.getStatusPanel().hpValue1 = value;
				bf.repaint();

			}
		});
		hpAnimation.start();
	}

	
	public void fadeToBlack() {
		fadeToBlack = new Timer(20, new ActionListener() {
			int sat = 0;
			public void actionPerformed(ActionEvent e) {
				if (sat <= 250) {
					sat +=5;
				} else {
					((Timer) e.getSource()).stop();
					ApplicationData.eventQueue.pop().run();
				}
				bf.fadeSat = sat;
				bf.repaint();

			}
		});
		fadeToBlack.start();
	}
	
	public void fadeFromBlack() {
		fadeToBlack = new Timer(20, new ActionListener() {
			int sat = 255;
			public void actionPerformed(ActionEvent e) {
				if (sat >= 5) {
					sat -=5;
				} else {
					((Timer) e.getSource()).stop();
					ApplicationData.eventQueue.pop().run();
				}
				bf.fadeSat = sat;
				bf.repaint();

			}
		});
		fadeToBlack.start();
	}
	
	public void stopAnimation() {
		if (textAnimation != null) {
			textAnimation.stop();
		}
		if (hpAnimation != null) {
			hpAnimation.stop();
		}
	}

	public void addHpAnimation(Pokemon pokemon) {
		ApplicationData.eventQueue.add(() -> hpAnimation(pokemon));
	}

	public void addTextAnimation(String s) {
		ApplicationData.eventQueue.add(() -> textAnimation(s));
	}
	
	public void addFadeAnimation() {
		ApplicationData.eventQueue.add(() -> fadeToBlack());
	}

	
}
