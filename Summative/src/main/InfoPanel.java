package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;
import pokemon.Pokemon;

public class InfoPanel extends JPanel{
	
	public JLabel name;
	public JLabel lvl;
	public JLabel statuses;
	public JPanel hpPanel;
	public JLabel hpLbl;
	public JProgressBar hpBar;
	public JLabel hpValue;
	public Pokemon p;
	
	public InfoPanel(Pokemon p) {
		this.p = p;
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(
				new MigLayout("insets 15, gapy 0", "[80px][][70%,grow]", "[15%,grow][5%]5[grow]"));

		name = new JLabel(p.getName());
		name.setPreferredSize(new Dimension(24, 40));
		name.setFont(ApplicationData.font.deriveFont(Font.PLAIN, 30));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		add(name, "cell 0 0 2 1,growx,aligny bottom");

		lvl = new JLabel("Lv. " + p.getLvl());
		lvl.setPreferredSize(new Dimension(30, 25));
		lvl.setFont(ApplicationData.font.deriveFont(Font.PLAIN, 15));
		lvl.setHorizontalAlignment(SwingConstants.CENTER);
		add(lvl, "cell 2 0,alignx right,aligny bottom");
		
		statuses = new JLabel("NORM");
		add(statuses, "cell 0 1,alignx center");

		hpPanel = new JPanel();
		hpPanel.setBackground(new Color(94, 94, 104));
		add(hpPanel, "cell 1 1 2 1,grow");
		hpPanel.setLayout(new MigLayout("insets 0, gapx 0", "[:1%:1%,grow][][:1%:1%,grow][18px,grow][::1%,grow]",
				"[::1%,grow][98%,grow][::1%,grow]"));

		hpLbl = new JLabel("HP");
		hpLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		hpLbl.setForeground(Color.ORANGE);
		hpPanel.add(hpLbl, "cell 1 0 1 3,alignx center,growy");
		hpLbl.setFont(new Font("Tahoma", Font.BOLD, 16));

		hpBar = new JProgressBar();
		hpBar.setForeground(Color.GREEN);
		hpBar.setBackground(new Color(94, 94, 104));
		hpBar.setValue(p.getCurrentHp() / p.getMaxHp() * 100);
		hpBar.setBorderPainted(false);
		hpPanel.add(hpBar, "cell 3 1,grow");
		
		hpValue = new JLabel(p.getCurrentHp() + "/" + p.getMaxHp());
		hpValue.setFont(ApplicationData.font.deriveFont(Font.BOLD, 15));
		hpValue.setPreferredSize(new Dimension(30, 25));
		add(hpValue, "cell 2 2,alignx center,aligny top");
		
		hpBar.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (hpBar.getValue() <= 0) {

				} else if (hpBar.getValue() <= 25) {
					hpBar.setForeground(Color.red);
				} else if (hpBar.getValue() <= 50) {
					hpBar.setForeground(Color.yellow);
				}
			}
		});

	
	}
	
}
