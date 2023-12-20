package main;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class UIHandler{

	Graphics2D g2;
	private BattleFrame bf;
	
	
	public UIHandler (BattleFrame frame) {
		super();
		this.bf = frame;
	}
	
	public void drawDialogueWindow() {
		bf.bottomPanel.removeAll();
		bf.bottomPanel.setLayout(new MigLayout("", "[100%,grow]", "[100%,grow]"));
		bf.textPanel.remove(bf.abilityInfo);
		bf.textPanel.add(bf.textAreaPanel, "cell 0 0, span, grow");
		bf.bottomPanel.add(bf.textPanel, "grow");
		
	}

	
	public void drawInfoPanels() {
		
		
	}
	
	public void drawAbilityInfoPanel() {
		
	}

	
}
