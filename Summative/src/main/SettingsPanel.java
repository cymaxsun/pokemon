package main;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

public class SettingsPanel extends JPanel {

	private JPanel settingPanel;
	private JPanel headingPanel;
	private CustomText header;
	private JPanel musicVolumePanel;
	private CustomText musicVolumeLabel;
	private JSlider musicSlider;
	private CustomText musicMuteButton;
	private JPanel SFXVolumePanel;
	private CustomText SFXvolumeLabel;
	private CustomText SFXMuteButton;
	private JSlider SFXSlider;
	private Color enabledColor = Color.RED;
	private Color enabledShadowColor = new Color(248,139,132);
	private Color defaultColor = new Color(88, 88, 80);
	private Color defaultShadowColor = new Color(168,184,184);
	private JPanel trackSeletionPanel;
	private JSpinner trackSelector;
	private CustomText trackSelectionLabel;
	public CustomText trackEditor;
	

	public SettingsPanel() {
		setLayout(new BorderLayout(0, 0));
		headingPanel = new JPanel();
		headingPanel.setBackground(new Color(235, 241, 255));
		headingPanel.setBorder(new CompoundBorder(new LineBorder(new Color(207, 217, 244), 20, false),
				new MatteBorder(25, 5, 25, 5, headingPanel.getBackground())));
		add(headingPanel, BorderLayout.NORTH);

		
		headingPanel.setLayout(new MigLayout("insets 0", "[grow]", "[grow]"));
		header = new CustomText("Options");
		header.setFont(ApplicationData.font.deriveFont( 50f));
		headingPanel.add(header, "cell 0 0,grow");

		settingPanel = new JPanel();
		settingPanel.setBackground(new Color(235, 241, 255));
		settingPanel.setBorder(new CompoundBorder(new LineBorder(new Color(207, 217, 244), 20),
				new MatteBorder(5, 5, 5, 5, (Color) new Color(235, 241, 255))));

		add(settingPanel, BorderLayout.CENTER);
		settingPanel.setLayout(new MigLayout("insets 30 20 30 20, gapy 20", "[grow]", "[][][][grow]"));

	
		
		musicVolumePanel = new JPanel();
		musicVolumePanel.setOpaque(false);
		musicVolumePanel.setLayout(new MigLayout("insets 0", "[:40%:40%,grow][][grow]", "[grow]"));
		settingPanel.add(musicVolumePanel, "cell 0 0,grow");

		musicVolumeLabel = new CustomText("Music Volume");

		musicVolumeLabel.setForeground(new Color(192, 120, 0));
		musicVolumeLabel.setBackground(Color.ORANGE);
		musicVolumeLabel.setFont(ApplicationData.font.deriveFont(Font.PLAIN, 50f));
		musicVolumePanel.add(musicVolumeLabel, "cell 0 0,alignx left,growy");

		musicMuteButton = new CustomText("Mute");
		musicMuteButton.setFont(ApplicationData.font.deriveFont(Font.PLAIN, 50f));
		musicMuteButton.setForeground(defaultColor);
		musicMuteButton.setBackground(defaultShadowColor);
		musicVolumePanel.add(musicMuteButton, "cell 1 0,aligny center");
		musicMuteButton.addMouseListener(new MouseAdapter() {
			

			@Override
			public void mousePressed(MouseEvent e) {
				
				if (ApplicationData.soundtrack.muted) {
					musicMuteButton.setForeground(defaultColor);
					musicMuteButton.setBackground(defaultShadowColor);
				} else {
					musicMuteButton.setForeground(enabledColor);
					musicMuteButton.setBackground(enabledShadowColor);
				}
				ApplicationData.soundtrack.muteSFX();

			}
		});

		musicSlider = new JSlider();
		musicVolumePanel.add(musicSlider, "cell 2 0,growx,aligny center");
		musicSlider.setValue((int) ApplicationData.soundtrackVolume);
		musicSlider.setMaximum(6);
		musicSlider.setMinimum(-60);
		musicSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ApplicationData.soundtrack.setVolume(musicSlider.getValue());

			}
		});
		musicSlider.setOpaque(false);

		SFXVolumePanel = new JPanel();
		SFXVolumePanel.setOpaque(false);
		SFXVolumePanel.setLayout(new MigLayout("insets 0", "[:40%:40%,grow][][grow]", "[grow]"));
		settingPanel.add(SFXVolumePanel, "cell 0 1,grow");

		SFXvolumeLabel = new CustomText("SFX Volume");
		SFXvolumeLabel.setForeground(new Color(192, 120, 0));
		SFXvolumeLabel.setBackground(Color.ORANGE);
		SFXvolumeLabel.setFont(ApplicationData.font.deriveFont(Font.PLAIN, 50f));
		SFXVolumePanel.add(SFXvolumeLabel, "alignx left,growy");

		SFXMuteButton = new CustomText("Mute");
		SFXMuteButton.setFont(ApplicationData.font.deriveFont(Font.PLAIN, 50f));
		SFXMuteButton.setForeground(defaultColor);
		SFXMuteButton.setBackground(defaultShadowColor);
		SFXMuteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (ApplicationData.sfx.muted) {
					SFXMuteButton.setForeground(defaultColor);
					SFXMuteButton.setBackground(defaultShadowColor);
				} else {
					SFXMuteButton.setForeground(enabledColor);
					SFXMuteButton.setBackground(enabledShadowColor);
				}
				ApplicationData.sfx.muteSFX();
			}
		});
		SFXVolumePanel.add(SFXMuteButton, "aligny center");

		SFXSlider = new JSlider();
		SFXSlider.setValue((int) ApplicationData.SFXVolume);
		SFXSlider.setOpaque(false);
		SFXSlider.setMinimum(-60);
		SFXSlider.setMaximum(6);
		SFXSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ApplicationData.sfx.setVolume(SFXSlider.getValue());
			}
		});
		SFXVolumePanel.add(SFXSlider, "growx,aligny center");
		
		trackSeletionPanel = new JPanel();
		trackSeletionPanel.setOpaque(false);
		settingPanel.add(trackSeletionPanel, "cell 0 2,grow");
		trackSeletionPanel.setLayout(new MigLayout("insets 0", "[:40%:40%,grow][:60%:60%,grow]", "[grow]"));
		
		trackSelectionLabel = new CustomText("Music");
		trackSelectionLabel.setFont(ApplicationData.font.deriveFont(50f));
		trackSelectionLabel.setForeground(new Color(192, 120, 0));
		trackSelectionLabel.setBackground(Color.ORANGE);
		trackSeletionPanel.add(trackSelectionLabel, "cell 0 0,alignx left,aligny center");
		
		trackSelector = new JSpinner();
		trackSelector.setValue(ApplicationData.track);
		trackSelector.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int track = (int) trackSelector.getValue();
				if (track < 0) {
					track = ApplicationData.numOfTracks-1;
				} else if (track >= ApplicationData.numOfTracks ) {
					track = 0;
				}
				ApplicationData.track = track;
				ApplicationData.soundtrack.clipEnded = false;
				ApplicationData.soundtrack.stop();
				ApplicationData.soundtrack.playTrack(ApplicationData.track);
				updateTrack();
				
			}
		});
		
		
		trackEditor = new CustomText(Sound.getTrackName(ApplicationData.track));
		trackEditor.setFont(ApplicationData.font.deriveFont(45f));
		trackSelector.setEditor(trackEditor);
		trackSeletionPanel.add(trackSelector, "cell 1 0,growx,aligny center");

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				if (c == KeyEvent.VK_ESCAPE) {
					ApplicationData.sfx.playFile(1);
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
		g2.drawLine(20, headingPanel.getY()+20,	 20, headingPanel.getHeight()-20);
		g2.drawLine(20, headingPanel.getY()+20, this.getWidth()-20, headingPanel.getY()+20);
		g2.setColor(new Color(192, 120, 0));
		g2.setFont(ApplicationData.font.deriveFont(50f));
	}

	public void updateTrack() {
		trackSelector.setValue(ApplicationData.track);
		trackEditor.setText(Sound.getTrackName(ApplicationData.track));
	}
}

