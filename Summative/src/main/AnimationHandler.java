package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import pokemon.Pokemon;

public class AnimationHandler {

	private BattleFrame bf;
	private boolean stop = false;
	public Timer hpAnimation;
	public Timer textAnimation;
	int panelX;

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
						bf.eventQueue.pop().run();
						
					}
				}
				bf.repaint();
			}
		});
		textAnimation.start();
	}

	public void hpAnimation(Pokemon pokemon) {
		hpAnimation = new Timer(5, new ActionListener() {
			int value = pokemon.getInfoPanel().hpBar.getValue();
			int targetValue = pokemon.getCurrentHp();

			public void actionPerformed(ActionEvent e) {
				
				if (value <= 0 || value > pokemon.getMaxHp() || value == targetValue) {

					((Timer) e.getSource()).stop();
					if (bf.eventQueue.peek() != null) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						bf.eventQueue.pop().run();
					}

				} else {
					if (value > targetValue) {

						value -= 1;
					} else {
						value += 1;
					}

				}
				pokemon.getInfoPanel().hpBar.setValue(value);
				pokemon.getInfoPanel().hpValue1 = value;
				bf.repaint();

			}
		});
		hpAnimation.start();
	}

	
	public void entryAnimation(Pokemon p) {
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 panelX -= 2; // Adjust the movement speed here

                if (panelX <= bf.getWidth() - p.getInfoPanel().getWidth()) {
                    panelX = bf.getWidth() - p.getInfoPanel().getWidth();
                    ((Timer) e.getSource()).stop(); // Stop the animation when the panel is in position
                }

                
                bf.repaint();
            }
        });

        timer.start();
    
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
		bf.eventQueue.add(() -> hpAnimation(pokemon));
	}

	public void addTextAnimation(String s) {
		bf.eventQueue.add(() -> textAnimation(s));
	}

}
