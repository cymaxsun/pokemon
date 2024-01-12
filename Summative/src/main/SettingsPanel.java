package main;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

public class SettingsPanel extends JPanel {

	private JPanel settingPanel;
	private JPanel headingPanel;
	private JLabel header;
	private JPanel scrollSpeedPanel;
	private JSpinner spinner;
	private JLabel scrollSpeedLabel;
	private JPanel musicVolumePanel;
	private JLabel lblNewLabel;
	private JLabel musicVolumeLabel;
	private JSlider musicSlider;
	private JToggleButton musicMuteButton;
	private JPanel SFXVolumePanel;
	private JLabel SFXvolumeLabel;
	private JToggleButton SFXMuteButton;
	private JSlider SFXSlider;

	public SettingsPanel() {
		setLayout(new BorderLayout(0, 0));
		headingPanel = new JPanel();
		headingPanel.setBackground(new Color(235, 241, 255));
		headingPanel.setBorder(new CompoundBorder(new LineBorder(new Color(207, 217, 244), 20, false),
				new MatteBorder(25, 5, 25, 5, headingPanel.getBackground())));
		add(headingPanel, BorderLayout.NORTH);

		header = new JLabel("Options") {
			@Override
			public void paintComponent(Graphics g) {

				drawShadow(g, new Color(168, 184, 184), this);
				super.paintComponent(g);
			}
		};
		header.setForeground(new Color(88, 88, 80));
		headingPanel.setLayout(new GridLayout(0, 1, 0, 0));
		header.setFont(ApplicationData.font.deriveFont(Font.BOLD, 50f));
		headingPanel.add(header);

		settingPanel = new JPanel();
		settingPanel.setBackground(new Color(235, 241, 255));
		settingPanel.setBorder(new CompoundBorder(new LineBorder(new Color(207, 217, 244), 20),
				new MatteBorder(5, 5, 5, 5, (Color) new Color(235, 241, 255))));

		add(settingPanel, BorderLayout.CENTER);
		settingPanel.setLayout(new MigLayout("insets 30 20 30 20, gapy 20", "[grow]", "[][][][grow]"));

		scrollSpeedPanel = new JPanel();
		scrollSpeedPanel.setOpaque(false);
		settingPanel.add(scrollSpeedPanel, "cell 0 0,grow");
		scrollSpeedPanel.setLayout(new MigLayout("insets 0", "[:40%:40%,grow][grow]", "[grow]"));

		scrollSpeedLabel = new JLabel("Scroll Speed") {
			@Override
			public void paintComponent(Graphics g) {

				drawShadow(g, Color.orange, this);
				super.paintComponent(g);
			}

		};
		scrollSpeedLabel.setBorder(null);
		scrollSpeedLabel.setRequestFocusEnabled(false);
		scrollSpeedLabel.setForeground(new Color(192, 120, 0));
		scrollSpeedLabel.setFont(ApplicationData.font.deriveFont(Font.BOLD, 50f));
		scrollSpeedPanel.add(scrollSpeedLabel, "cell 0 0,alignx left,aligny center");

		spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

			}
		});
		spinner.setPreferredSize(new Dimension(50, 50));

		scrollSpeedPanel.add(spinner, "cell 1 0,alignx left,aligny center");

	
		
		musicVolumePanel = new JPanel();
		musicVolumePanel.setOpaque(false);
		musicVolumePanel.setLayout(new MigLayout("insets 0", "[:40%:40%,grow][][grow]", "[grow]"));
		settingPanel.add(musicVolumePanel, "cell 0 1,grow");

		musicVolumeLabel = new JLabel("Music Volume") {
			@Override
			public void paintComponent(Graphics g) {
				drawShadow(g, Color.orange, this);
				super.paintComponent(g);

			}
		};

		musicVolumeLabel.setForeground(new Color(192, 120, 0));
		musicVolumeLabel.setFont(ApplicationData.font.deriveFont(Font.BOLD, 50f));
		musicVolumePanel.add(musicVolumeLabel, "cell 0 0,alignx left,growy");

		musicMuteButton = new JToggleButton("mute");
		musicVolumePanel.add(musicMuteButton, "cell 1 0,aligny center");
		musicMuteButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ApplicationData.soundtrack.muteTrack();
			}
		});

		musicSlider = new JSlider();
		musicVolumePanel.add(musicSlider, "cell 2 0,growx,aligny center");
		musicSlider.setValue((int) ApplicationData.soundtrackVolume);
		musicSlider.setMaximum(6);
		musicSlider.setMinimum(-80);
		musicSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ApplicationData.soundtrack.setVolume(musicSlider.getValue());

			}
		});
		musicSlider.setOpaque(false);

		SFXVolumePanel = new JPanel();
		SFXVolumePanel.setOpaque(false);
		SFXVolumePanel.setLayout(new MigLayout("insets 0", "[:40%:40%,grow][][grow]", "[grow]"));
		settingPanel.add(SFXVolumePanel, "cell 0 2,grow");

		SFXvolumeLabel = new JLabel("SFX Volume") {
			public void paintComponent(Graphics g) {
				drawShadow(g, Color.orange, this);
				super.paintComponent(g);
			}
		};
		SFXvolumeLabel.setForeground(new Color(192, 120, 0));
		SFXvolumeLabel.setFont(ApplicationData.font.deriveFont(Font.BOLD, 50f));
		SFXVolumePanel.add(SFXvolumeLabel);

		SFXMuteButton = new JToggleButton("mute");
		SFXMuteButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ApplicationData.sfx.muteClip();
			}
		});
		SFXVolumePanel.add(SFXMuteButton, "aligny center");

		SFXSlider = new JSlider();
		SFXSlider.setValue((int) ApplicationData.SFXVolume);
		SFXSlider.setOpaque(false);
		SFXSlider.setMinimum(-80);
		SFXSlider.setMaximum(6);
		SFXSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ApplicationData.sfx.setVolume(SFXSlider.getValue());
			}
		});
		SFXVolumePanel.add(SFXSlider, "growx,aligny center");

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ESCAPE) {
					ApplicationData.switchPanel(ApplicationData.settings, ApplicationData.titlePanel);

				}
			}
		});

		addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e) {
				ApplicationData.settings.requestFocus();
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
		Stroke defStroke = g2.getStroke();
		BasicStroke borderStroke = new BasicStroke(6f);
		BasicStroke highlightStroke = new BasicStroke(3f);
		g2.setStroke(borderStroke);
		g2.setColor(Color.BLACK);
		g2.drawRect(0, 0, headingPanel.getWidth(), headingPanel.getHeight());
		g2.drawRect(0, 0, settingPanel.getWidth(), settingPanel.getWidth());
		g2.setStroke(highlightStroke);
		g2.setColor(new Color(80, 98, 153));
		g2.drawLine(20, settingPanel.getY()+20, 20, this.getHeight()-20);
		g2.drawLine(20, settingPanel.getY()+20, this.getWidth()-20, settingPanel.getY()+20);
		g2.drawLine(20, headingPanel.getY()+20, 20, headingPanel.getHeight()-20);
		g2.drawLine(20, headingPanel.getY()+20, this.getWidth()-20, headingPanel.getY()+20);
		g2.setColor(new Color(192, 120, 0));
		g2.setFont(ApplicationData.font.deriveFont(50f));
	}

	public void drawShadow(Graphics g, Color shadowColor, JLabel text) {
		g.setColor(shadowColor);
		g.setFont(text.getFont().deriveFont(text.getFont().getSize() + 15));
		g.drawString(text.getText(), 3, text.getHeight() - 13);
	}
}