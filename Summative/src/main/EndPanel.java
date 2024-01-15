package main;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class EndPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public EndPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Play Again");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationData.resetGame();
				ApplicationData.switchPanel(ApplicationData.endPanel, ApplicationData.titlePanel);
				
				}
		});
		add(btnNewButton, BorderLayout.CENTER);
		
		JButton btnNewButton_1 = new JButton("QUIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationData.window.dispatchEvent(new WindowEvent(ApplicationData.window, WindowEvent.WINDOW_CLOSING));
			}
		});
		add(btnNewButton_1, BorderLayout.SOUTH);

	}

}
