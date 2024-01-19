package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import moves.PokemonMove;
import net.miginfocom.swing.MigLayout;
import pokemon.Pokemon;

public class MoveInfoPanel extends JPanel {

	private JLabel moveType;
	private JLabel movePP;
	private PokemonMove selectedMove;
	private JPanel movePPPanel;
	private Pokemon p;

	public MoveInfoPanel(Pokemon pokemon) {

		this.p = pokemon;
		selectedMove = p.getMove1();
		setLayout(new MigLayout("insets 15 40 15 40, gapx 0", "[100%,grow]", "[50%,grow]10[50%,grow]"));
		setBackground(Color.WHITE);
		moveType = new JLabel(selectedMove.getTypeName());
		moveType.setFont(ApplicationData.font.deriveFont(Font.PLAIN, 40));

		moveType.setForeground(Color.WHITE);
		moveType.setHorizontalAlignment(SwingConstants.CENTER);
		moveType.setOpaque(true);
		add(moveType, "cell 0 0, grow");

		

		movePPPanel = new JPanel();
		movePPPanel.setBackground(Color.white);
		movePPPanel.setLayout(new GridLayout(0, 1, 0, 0));
		movePP = new JLabel();
		movePP.setBackground(Color.WHITE);
		movePP.setFont(ApplicationData.font.deriveFont(Font.PLAIN, 40));
		movePP.setHorizontalAlignment(SwingConstants.CENTER);
		movePPPanel.add(movePP);

		add(movePPPanel, "cell 0 1,grow");

		updateMove(selectedMove);

	}

	public void updateMove(PokemonMove selectedMove) {
		moveType.setBackground(selectedMove.getTypeColor());
		moveType.setText(selectedMove.getTypeName());
		movePP.setText("PP:" + selectedMove.getCharges() + "/" + selectedMove.getMaxCharges());
		repaint();

	}

	public void updateMove() {
		
		moveType.setBackground(selectedMove.getTypeColor());
		moveType.setText(selectedMove.getTypeName());
		movePP.setText("PP:" + selectedMove.getCharges() + "/" + selectedMove.getMaxCharges());
		repaint();

	}

}
