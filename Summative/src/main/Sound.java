package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	
	
	Clip clip;
	URL[] clips = new URL[10];
	
	
	public Sound() {
	
		clips[0] = getClass().getResource("/sound/battle.wav");
		clips[1] = getClass().getResource("/sound/button.wav");
		clips[2] = getClass().getResource("/sound/heal.wav");
		clips[3] = getClass().getResource("/sound/Lick.wav");
		clips[4] = getClass().getResource("/sound/hit.wav");
		
	}
	
	public void setFile(int i) {
		AudioInputStream sis;
		try {
			sis = AudioSystem.getAudioInputStream(clips[i]);
			clip = AudioSystem.getClip();
			clip.open(sis);
			
			
		} catch (Exception e) {
		
		}
		
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	
	public void playFile(int i, float f) {
		setFile(i);
		setVolume(f);
		play();
		
	}
	
	public void setVolume(float f) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);    
		float range = gainControl.getMaximum() - gainControl.getMinimum();
		float gain = (range * f) + gainControl.getMinimum();
		gainControl.setValue(gain);
	}

	
	public long getLength() {
		
		return clip.getMicrosecondLength();
	}
}
