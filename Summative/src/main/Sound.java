package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	FloatControl gainControl;
	float range;
	Clip clip;
	URL[] clips = new URL[10];
	float volume;
	final float mutedVol = -80f;
	float prevVol;
	boolean muted = false;
	private long clipPos;

	public Sound() {

		clips[0] = getClass().getResource("/sound/battle.wav");
		clips[1] = getClass().getResource("/sound/button.wav");
		clips[2] = getClass().getResource("/sound/hit.wav");
		clips[3] = getClass().getResource("/sound/backgroundTrack.wav");
		clips[4] = getClass().getResource("/sound/victory.wav");
		clips[5] = getClass().getResource("/sound/fainted.wav");
		clips[6] = getClass().getResource("/sound/lowHP.wav");
		clips[7] = getClass().getResource("/sound/heal.wav");
		clips[8] = getClass().getResource("/sound/backgroundTrack2.wav");

	}

	public void setFile(int i) {
		AudioInputStream sis;
		try {
			sis = AudioSystem.getAudioInputStream(clips[i]);
			clip = AudioSystem.getClip();
			clip.open(sis);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

		} catch (Exception e) {

		}

	}

	public void setFile(URL path) {
		AudioInputStream sis;
		try {
			sis = AudioSystem.getAudioInputStream(path);
			this.clip = AudioSystem.getClip();
			this.clip.open(sis);

			gainControl = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (Exception e) {

		}

	}

	public void play() {
		gainControl.setValue(volume);
		clip.start();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}

	public void playFile(int i) {
		setFile(i);
		play();

	}

	public void playFile(URL path) {
		setFile(path);
		play();

	}

	public void setVolume(float f) {
		this.volume = f;
		if (clip != null && !muted) {
			gainControl.setValue(volume);
		} 
	}

	public long getLength() {

		return clip.getMicrosecondLength();
	}
	
	public void muteClip() { 
		if (!muted) {
			this.prevVol = volume;
			setVolume(mutedVol);
			muted = true;
		} else {
			muted = false;
			setVolume(prevVol);
		}
	}
	
	public void muteTrack() {
		if (!muted) {
			clipPos = clip.getMicrosecondPosition();
			clip.stop();
			muted = true;
		} else {
			clip.setMicrosecondPosition(clipPos);
			play();
			loop();
			muted = false;
		
		
	}
	}
}
