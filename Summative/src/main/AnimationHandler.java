package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import pokemon.Pokemon;

public class AnimationHandler {

	private BattleFrame bf;
	private boolean stop = false;
	private Timer hpAnimation;
	private Timer textAnimation;

	public AnimationHandler(BattleFrame bf) {
		this.bf = bf;
	}

	public void textAnimation(String string) {
		bf.textBox.setText("");
		textAnimation = new Timer(50, new ActionListener() {
			int index = 0;

			public void actionPerformed(ActionEvent e) {
				if (index <= string.length()) {
					bf.textBox.setText(string.substring(0, index));
					index++;

				} else {
					((Timer) e.getSource()).stop();
					if (bf.eventQueue.peek() != null) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (!stop) {
							bf.eventQueue.pop().run();
						}
					}
				}
				bf.repaint();
			}
		});
		textAnimation.start();
	}

	public void hpAnimation(Pokemon pokemon) {
		hpAnimation = new Timer(25, new ActionListener() {
			int barValue = pokemon.getInfoPanel().hpBar.getValue();
			int intValue = (int) ((0.0 + barValue) / 100 * pokemon.getMaxHp());
			int targetBarValue = (int) ((0.0 + pokemon.getCurrentHp()) / pokemon.getMaxHp() * 100);

			public void actionPerformed(ActionEvent e) {
				if (barValue <= 0 || barValue > 100 || barValue == targetBarValue) {

					((Timer) e.getSource()).stop();
					if (bf.eventQueue.peek() != null) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (!stop) {
							bf.eventQueue.pop().run();
						}
					}

				} else {
					if (barValue > targetBarValue) {

						barValue -= 1;
						intValue -= pokemon.getMaxHp() / 100;
					} else {
						barValue += 1;
						intValue += pokemon.getMaxHp() / 100;
					}

				}
				pokemon.getInfoPanel().hpBar.setValue(barValue);
				pokemon.getInfoPanel().hpValue.setText(intValue + "/" + pokemon.getMaxHp());
				bf.repaint();

			}
		});
		hpAnimation.start();
	}

	public void pauseAnimation() {
		stop = true;
	}

	public void addHpAnimation(Pokemon pokemon) {
		bf.eventQueue.add(() -> hpAnimation( pokemon));
	}

	public void addTextAnimation(String s) {
		bf.eventQueue.add(() -> textAnimation(s));
	}

}
